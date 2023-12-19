package com.example.football;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment implements View.OnClickListener
{
    private Button[] buttons;

    public MainFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        buttons = new Button[2];
        buttons[0] = view.findViewById(R.id.btn_create);
        buttons[1] = view.findViewById(R.id.btn_detail);
        buttons[0].setBackgroundTintList(getResources().getColorStateList(R.color.blue));
        buttons[1].setBackgroundTintList(getResources().getColorStateList(R.color.blue));

        for (Button btn : buttons)
        {
            btn.setOnClickListener(this);
        }
        return view;
    }

    @Override
    public void onClick(View view)
    {
        MainActivity mainActivity = (MainActivity) getActivity();

        switch (view.getId())
        {
            case R.id.btn_create:
                Log.d("MainActivity", " btn_create PRESSED");
                mainActivity.changeFragmentFind();
                break;
            case R.id.btn_detail:
                Log.d("MainActivity", " btn_create PRESSED");
                mainActivity.changeFragmentCreate();
                break;
        }
    }
}
