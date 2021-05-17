package com.cookandroid.splash_pleazz;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentPage2 extends Fragment implements View.OnClickListener{


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_page_2, container, false);

        Button btn_postbox = view.findViewById(R.id.btn_postbox);
        Button btn_taxi = view.findViewById(R.id.btn_taxi);
        Button btn_ask = view.findViewById(R.id.btn_ask);

        btn_postbox.setOnClickListener(this);
        btn_taxi.setOnClickListener(this);
        btn_ask.setOnClickListener(this);

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btn_postbox:
                Intent intent = new Intent(getContext(), StreamingActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_taxi:
                intent = new Intent(getContext(), taxi.class);
                startActivity(intent);
                break;

            case R.id.btn_ask:
                intent = new Intent(getContext(), AskActivity.class);
                startActivity(intent);
                break;



        }

    }
}
