package mlg.warkop.com.mypsychologist.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import mlg.warkop.com.mypsychologist.R;
import mlg.warkop.com.mypsychologist.fragment.ChatListFragment;

public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.container)
    FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        ShowChatList();
    }

    private void ShowChatList(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ChatListFragment(), "ChatList")
                .commit();
    }
}
