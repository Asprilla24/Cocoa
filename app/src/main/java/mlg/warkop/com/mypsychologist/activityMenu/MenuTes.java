package mlg.warkop.com.mypsychologist.activityMenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import mlg.warkop.com.mypsychologist.R;

public class MenuTes extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menutes);
    }

    public void minat(View view) {
        startActivity(new Intent(MenuTes.this, TesMinat.class));
    }

    public void hasilbakat(View view) {
        startActivity(new Intent(MenuTes.this, HasilSkoring.class));
    }

    public void tesbakat(View view) {
        startActivity(new Intent(MenuTes.this, TesBakat.class));
    }
}
