package com.example.saiko.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

import androidx.fragment.app.Fragment;

import com.example.saiko.R;

public class HomeFragment extends Fragment {

    int counter = 0;
    View rootView;
    private ImageSwitcher pic_image_switch;
    private Handler pic_image_switch_handler;


    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        pic_image_switch = (ImageSwitcher) rootView.findViewById(R.id.is_motivasi);

        pic_image_switch.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getActivity());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
                return imageView;
            }
        });

        pic_image_switch_handler = new Handler(Looper.getMainLooper());

        pic_image_switch_handler.post(new Runnable() {
            @Override
            public void run() {
                switch (counter) {
                    case 0:
                        pic_image_switch.setImageResource(R.mipmap.quotes1);
                        break;
                    case 1:
                        pic_image_switch.setImageResource(R.mipmap.quotes2);
                        break;
                    case 2:
                        pic_image_switch.setImageResource(R.mipmap.quotes3);
                        break;
                    case 3:
                        pic_image_switch.setImageResource(R.mipmap.quotes4);
                        break;
                    case 4:
                        pic_image_switch.setImageResource(R.mipmap.quotes5);
                        break;
                }
                counter += 1;
                if (counter == 5) {
                    counter = 0;
                }
                pic_image_switch.postDelayed(this, 10000);
            }
        });

        return rootView;
    }

}
