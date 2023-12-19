package com.example.football;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateMatchResultFragment extends Fragment implements View.OnClickListener {
    private MatchResultsDataSource dataSource;
    private EditText command1EditText;
    private EditText command2EditText;
    private EditText command1ResultEditText;
    private EditText command2ResultEditText;
    public CreateMatchResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataSource = new MatchResultsDataSource(requireContext());
        dataSource.open();
        View view = inflater.inflate(R.layout.create_match_result_fragment, container, false);
        command1EditText = view.findViewById(R.id.command1);
        command2EditText = view.findViewById(R.id.command2);
        command1ResultEditText = view.findViewById(R.id.command1_result);
        command2ResultEditText = view.findViewById(R.id.command2_result);
        Button createButton = view.findViewById(R.id.btn_create_form);
        createButton.setBackgroundTintList(getResources().getColorStateList(R.color.yellow));
        createButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_create_form:
                String command1 = command1EditText.getText().toString();
                String command2 = command2EditText.getText().toString();
                String command1ResultStr = command1ResultEditText.getText().toString();
                String command2ResultStr = command2ResultEditText.getText().toString();
                String currentDate = getCurrentDate();

                if (TextUtils.isEmpty(command1) || TextUtils.isEmpty(command2) ||
                        TextUtils.isEmpty(command1ResultStr) || TextUtils.isEmpty(command2ResultStr)) {
                    Toast.makeText(requireContext(), "Заповніть усі поля", Toast.LENGTH_SHORT).show();
                    return;
                }
                int command1Result, command2Result;

                try {
                    command1Result = Integer.parseInt(command1ResultStr);
                    command2Result = Integer.parseInt(command2ResultStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(requireContext(), "Невірний формат числа", Toast.LENGTH_SHORT).show();
                    return;
                }

                dataSource.addMatchResult(command1, command2, command1Result, command2Result, currentDate);
                Toast.makeText(requireContext(), "Результати додано до бази даних", Toast.LENGTH_SHORT).show();
                command1EditText.setText("");
                command2EditText.setText("");
                command1ResultEditText.setText("");
                command2ResultEditText.setText("");
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dataSource.close();
    }

    public String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(new Date());
    }
}