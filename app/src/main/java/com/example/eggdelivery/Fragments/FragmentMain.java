package com.example.eggdelivery.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.eggdelivery.Activities.MainActivity;
import com.example.eggdelivery.R;

public class FragmentMain extends Fragment {
    private ImageView order;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mainfrag, container, false);

        order = (ImageView) view.findViewById(R.id.eggsy);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setViewPager(1);
            }
        });


        return view;
    }
}
