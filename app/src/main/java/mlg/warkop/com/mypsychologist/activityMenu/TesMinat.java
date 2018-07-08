package mlg.warkop.com.mypsychologist.activityMenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import mlg.warkop.com.mypsychologist.R;

public class TesMinat extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tesminat);
    }

    public void pindah(View view) {
        startActivity(new Intent(TesMinat.this, Tes.class));
    }
}
