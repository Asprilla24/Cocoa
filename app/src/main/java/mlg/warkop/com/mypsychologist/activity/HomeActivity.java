package mlg.warkop.com.mypsychologist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mlg.warkop.com.mypsychologist.R;
import mlg.warkop.com.mypsychologist._sliders.FragmentSlider;
import mlg.warkop.com.mypsychologist._sliders.SliderIndicator;
import mlg.warkop.com.mypsychologist._sliders.SliderPagerAdapter;
import mlg.warkop.com.mypsychologist._sliders.SliderView;
import mlg.warkop.com.mypsychologist.activityMenu.MenuTes;
import mlg.warkop.com.mypsychologist.activityMenu.Psikologi;

public class HomeActivity extends AppCompatActivity {

    private static final int SCROLL_DURATION = 800;

    @BindView(R.id.sliderView)
    SliderView sliderView;
    @BindView(R.id.pagesContainer)
    LinearLayout pagesContainer;
    @BindView(R.id.btnTestBm)
    LinearLayout btnTestBm;
    @BindView(R.id.btnChat)
    LinearLayout btnChat;
    @BindView(R.id.btnPsycho)
    LinearLayout btnPsycho;

    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        SetupSlider();
    }

    @OnClick({R.id.btnTestBm, R.id.btnChat, R.id.btnPsycho})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnTestBm:
                startActivity(new Intent(HomeActivity.this, MenuTes.class));

                break;
            case R.id.btnChat:
                startActivity(new Intent(HomeActivity.this, ChatActivity.class));

                break;
            case R.id.btnPsycho:
                startActivity(new Intent(HomeActivity.this, Psikologi.class));

                break;
        }
    }

    private void SetupSlider() {
        sliderView.setDurationScroll(SCROLL_DURATION);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("http://i1378.photobucket.com/albums/ah119/almasamaliaazhar/d_zpsvrhevhyb.jpg"));
        fragments.add(FragmentSlider.newInstance("http://i1378.photobucket.com/albums/ah119/almasamaliaazhar/c_zps6x72nefu.jpg"));
        fragments.add(FragmentSlider.newInstance("http://i1378.photobucket.com/albums/ah119/almasamaliaazhar/b_zpsms2mlnyr.jpg"));

        mAdapter = new SliderPagerAdapter(getSupportFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(this, pagesContainer, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }
}
