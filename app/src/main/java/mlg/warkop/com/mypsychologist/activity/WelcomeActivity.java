package mlg.warkop.com.mypsychologist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mlg.warkop.com.mypsychologist.R;
import mlg.warkop.com.mypsychologist._sliders.FragmentSlider;
import mlg.warkop.com.mypsychologist._sliders.SliderIndicator;
import mlg.warkop.com.mypsychologist._sliders.SliderPagerAdapter;
import mlg.warkop.com.mypsychologist._sliders.SliderView;

public class WelcomeActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    private static final int SCROLL_DURATION = 800;

    @BindView(R.id.btnMulai)
    Button btnMulai;
    @BindView(R.id.sliderView)
    SliderView sliderView;
    @BindView(R.id.pagesContainer)
    LinearLayout pagesContainer;

    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        mFirebaseAuth = FirebaseAuth.getInstance();

        SetupSlider();
    }

    @OnClick(R.id.btnMulai)
    public void onViewClicked() {
        SignIn();
    }

    private void SetupSlider() {
        sliderView.setDurationScroll(SCROLL_DURATION);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("http://i1378.photobucket.com/albums/ah119/almasamaliaazhar/sliderdua_zpsfjlbgiio.png"));
        fragments.add(FragmentSlider.newInstance("http://i1378.photobucket.com/albums/ah119/almasamaliaazhar/slidersatu_zpsovjm89xi.png"));
        fragments.add(FragmentSlider.newInstance("http://i1378.photobucket.com/albums/ah119/almasamaliaazhar/slidertiga_zpsctebie3x.png"));

        mAdapter = new SliderPagerAdapter(getSupportFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(this, pagesContainer, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }

    private void SignIn(){
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        if (user != null)
        {
            startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
            finish();
        }
        else
        {
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.PhoneBuilder().build(),
                    new AuthUI.IdpConfig.GoogleBuilder().build());

            // Create and launch sign-in intent
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    RC_SIGN_IN);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN){
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                //FirebaseUser user = mFirebaseAuth.getCurrentUser();

                SignInSuccess();
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                //Log.d("Firebase Login", response.getError().getErrorCode());
                // ...
                Toast.makeText(this, "Terdapat kesalahan saat login", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void SignInSuccess(){
        Intent i = new Intent(WelcomeActivity.this, LoginActivity.class);
//        Bundle extras = i.getExtras();
//        extras.putString("userId", user.getUid());
//        extras.putString("email", user.getEmail());
//        extras.putString("username", user.getDisplayName());
//        extras.putString("phone", user.getPhoneNumber());
//        extras.putString("photos", user.getPhotoUrl().toString());

        startActivity(i);
        finish();
    }
}
