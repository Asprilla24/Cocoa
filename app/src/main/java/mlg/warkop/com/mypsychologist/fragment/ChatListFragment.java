package mlg.warkop.com.mypsychologist.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.realm.Realm;
import mlg.warkop.com.mypsychologist.R;
import mlg.warkop.com.mypsychologist.adapter.MessageAdapter;
import mlg.warkop.com.mypsychologist.helper.MessageRealmHelper;
import mlg.warkop.com.mypsychologist.manager.AppData;
import mlg.warkop.com.mypsychologist.manager.AppPreferences;
import mlg.warkop.com.mypsychologist.model.Message;

public class ChatListFragment extends Fragment {

    @BindView(R.id.recyclerMessage)
    RecyclerView recyclerMessage;
    @BindView(R.id.txtMessage)
    EditText txtMessage;
    @BindView(R.id.btnSend)
    Button btnSend;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;

    private Unbinder unbinder;
    private AppPreferences appPreferences;

    private MessageAdapter mAdapter;
    private List<Message> messageList;
    private MessageRealmHelper messageHelper;
    private Realm realm;
    //firebase
    private DatabaseReference mFirebaseRef;

    public ChatListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Realm.init(getActivity());
        realm = Realm.getDefaultInstance();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        appPreferences = new AppPreferences(getActivity());
        messageHelper = new MessageRealmHelper(getActivity(), realm);

        String username = appPreferences.getUserName();

        InitFirebase(username);
        InitAdapter(username);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void InitAdapter(String username) {
        messageList = messageHelper.getListMessage(username);
        mAdapter = new MessageAdapter(getActivity(), messageList, username);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerMessage.setLayoutManager(mLayoutManager);
        recyclerMessage.setItemAnimator(new DefaultItemAnimator());
        recyclerMessage.scrollToPosition(messageList.size() - 1);
        recyclerMessage.setNestedScrollingEnabled(true);
        recyclerMessage.setVerticalScrollBarEnabled(false);
        recyclerMessage.setAdapter(mAdapter);
    }

    private void InitFirebase(String username) {
        final String userName = username;
        mFirebaseRef = FirebaseDatabase.getInstance().getReference();

        mFirebaseRef.child(AppData.FIREBASE_MESSAGE_DATA).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot != null) {
                    Message message = dataSnapshot.getValue(Message.class);
                    message.setId(dataSnapshot.getKey());
                    messageHelper.addMessage(message);

                    refreshView(userName);
                    Log.d("ChildAdded", dataSnapshot.toString());
                    /*if(!message.getMessageNama().equals(userName)){
                        Toast.makeText(getApplicationContext(), "Anda dapat pesan baru", Toast.LENGTH_LONG).show();
                    }*/
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void refreshView(String username) {
        messageList.clear();
        messageList.addAll(messageHelper.getListMessage(username));
        mAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btnSend)
    public void onViewClicked() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, HH:mm");
        Date dateNow = Calendar.getInstance().getTime();
        String messageText = txtMessage.getText().toString().trim();
        if(!TextUtils.isEmpty(messageText))
        {
            String encryptedName = appPreferences.getUserName();
            String encryptedMessage = messageText;
            DatabaseReference ref = mFirebaseRef.child(AppData.FIREBASE_MESSAGE_DATA).push();
            Message message = new Message();
            message.setMessageFrom(encryptedName);
            message.setMessageText(encryptedMessage);
            message.setMessageTime(simpleDateFormat.format(dateNow));
            message.setMessageTo("Psycholog");
            ref.setValue(message);
            txtMessage.setText(null);
            recyclerMessage.smoothScrollToPosition(messageList.size());
        }
    }
}
