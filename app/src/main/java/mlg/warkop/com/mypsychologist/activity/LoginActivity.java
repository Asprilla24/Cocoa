package mlg.warkop.com.mypsychologist.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import mlg.warkop.com.mypsychologist.R;
import mlg.warkop.com.mypsychologist.helper.UserRealmHelper;
import mlg.warkop.com.mypsychologist.manager.AppData;
import mlg.warkop.com.mypsychologist.manager.AppPreferences;
import mlg.warkop.com.mypsychologist.model.User;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.txtName)
    EditText txtName;
    @BindView(R.id.txtEducation)
    EditText txtEducation;
    @BindView(R.id.txtMajors)
    EditText txtMajors;
    @BindView(R.id.spnSemester)
    Spinner spnSemester;
    @BindView(R.id.spnLevel)
    Spinner spnLevel;
    @BindView(R.id.txtAge)
    EditText txtAge;
    @BindView(R.id.txtAddress)
    EditText txtAddress;
    @BindView(R.id.txtPhone)
    EditText txtPhone;
    @BindView(R.id.imgPhotos)
    ImageView imgPhotos;
    @BindView(R.id.btnSave)
    Button btnSave;

    private User user;
    private AppPreferences appPreferences;
    private DatabaseReference mDatabase;
    private FirebaseAuth mFirebaseAuth;

    private UserRealmHelper userHelper;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        appPreferences = new AppPreferences(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();
        userHelper = new UserRealmHelper(this, realm);

        FirebaseUser firebaseUser = GetUserDatafromBundle();

//        if(IsEmailOnFirebase(user.getEmail())){
//            appPreferences.setSession(user.getId(), user.getUserName());
//            startActivity(new Intent(this, HomeActivity.class));
//            finish();
//        }

        ShowDatatoUI(firebaseUser);
    }

    @OnClick(R.id.btnSave)
    public void onViewClicked() {
        SaveDataLogin(GetDatafromUI());
    }

    private FirebaseUser GetUserDatafromBundle(){
        FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();

//        Bundle extras = getIntent().getExtras();
//
//        String id = extras.getString("userId");
//        String email = extras.getString("email");
//        String userName = extras.getString("username");
//        String phone = extras.getString("phone");
//        String photos = extras.getString("photos");

        user = new User();
        user.setId(firebaseUser.getUid());
        user.setEmail(firebaseUser.getEmail());
        user.setUserName(firebaseUser.getDisplayName());
        user.setPhone(firebaseUser.getPhoneNumber());
        //user.setPhotos(firebaseUser.getPhotoUrl());

        return firebaseUser;
    }

    private void ShowDatatoUI(FirebaseUser firebaseUser){
        txtName.setText(user.getUserName());
        txtPhone.setText(user.getPhone());
        Glide.with(
                LoginActivity.this)
                .load(firebaseUser.getPhotoUrl())
                .into(imgPhotos);
    }

    private User GetDatafromUI(){
        String education = txtEducation.getText().toString();
        String majors = txtMajors.getText().toString();
        int semester = Integer.parseInt(spnSemester.getSelectedItem().toString());
        int level = Integer.parseInt(spnLevel.getSelectedItem().toString());
        int age = Integer.parseInt(txtAge.getText().toString());
        String address = txtAddress.getText().toString();

        user.setEducation(education);
        user.setMajors(majors);
        user.setSemester(semester);
        user.setLevel(level);
        user.setAge(age);
        user.setAddress(address);

        return user;
    }

    private void SaveDataLogin(User user){
        appPreferences.setSession(user.getId(), user.getUserName());
        Log.d("User",user.getUserName());
        Log.d("User Table",AppData.FIREBASE_USER_DATA);

        DatabaseReference mUsers = mDatabase.child(AppData.FIREBASE_USER_DATA).push();
        mUsers.setValue(user);

        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
    }

    private boolean IsEmailOnFirebase(final String email) {
        mDatabase.child(AppData.FIREBASE_USER_DATA).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot != null){
                    User user = dataSnapshot.getValue(User.class);
                    userHelper.addUser(user);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return userHelper.isUserAvailable(email);
    }
}
