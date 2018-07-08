package mlg.warkop.com.mypsychologist._sliders;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mlg.warkop.com.mypsychologist.R;

public class FragmentSlider extends Fragment {

    private static final String ARG_PARAM1 = "params";
    @BindView(R.id.img)
    ImageView img;

    private Unbinder unbinder;
    private String imageUrls;

    public static FragmentSlider newInstance(String params) {
        FragmentSlider fragment = new FragmentSlider();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, params);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        imageUrls = getArguments().getString(ARG_PARAM1);
        View view = inflater.inflate(R.layout.fragment_slider_item, container, false);
        unbinder = ButterKnife.bind(this, view);

        Glide.with(
                 getActivity())
                .load(imageUrls)
                .into(img);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}