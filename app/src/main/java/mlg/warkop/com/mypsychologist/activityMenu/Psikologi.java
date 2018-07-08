package mlg.warkop.com.mypsychologist.activityMenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import mlg.warkop.com.mypsychologist.R;
import mlg.warkop.com.mypsychologist.activity.ChatActivity;


public class Psikologi extends AppCompatActivity {

    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> dataSet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.psikologi);
    }

    public void viewprofil(View view) {
        startActivity(new Intent(Psikologi.this, ViewProfil.class));
    }

    public void chatpsikolog(View view) {
        startActivity(new Intent(Psikologi.this, ChatActivity.class));
    }
}
