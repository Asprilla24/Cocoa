package mlg.warkop.com.mypsychologist;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mlg.warkop.com.mypsychologist.activity.HomeActivity;
import mlg.warkop.com.mypsychologist.activity.WelcomeActivity;
import mlg.warkop.com.mypsychologist.manager.AppPreferences;

public class SplashScreenActivity extends AppCompatActivity {

    private static int TIME = 200;

    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        appPreferences = new AppPreferences(this);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(appPreferences.getUserName().equals(""))
                    startActivity(new Intent(SplashScreenActivity.this, WelcomeActivity.class));
                else
                    startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));

                finish();
            }
        }, TIME);
    }
}
