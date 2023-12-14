package com.example.football;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateMatchResultActivity extends AppCompatActivity {

    private MatchResultsDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_match_result);
        dataSource = new MatchResultsDataSource(this);
        dataSource.open();

        final EditText command1EditText = findViewById(R.id.command1);
        final EditText command2EditText = findViewById(R.id.command2);
        final EditText command1ResultEditText = findViewById(R.id.command1_result);
        final EditText command2ResultEditText = findViewById(R.id.command2_result);

        Button createButton = findViewById(R.id.btn_create_form);
        createButton.setBackgroundTintList(getResources().getColorStateList(R.color.yellow));
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String command1 = command1EditText.getText().toString();
                String command2 = command2EditText.getText().toString();
                String command1ResultStr = command1ResultEditText.getText().toString();
                String command2ResultStr = command2ResultEditText.getText().toString();
                String currentDate = getCurrentDate();


                if (TextUtils.isEmpty(command1) || TextUtils.isEmpty(command2) ||
                        TextUtils.isEmpty(command1ResultStr) || TextUtils.isEmpty(command2ResultStr)) {
                    Toast.makeText(CreateMatchResultActivity.this, "Заповніть усі поля", Toast.LENGTH_SHORT).show();
                    return;
                }
                int command1Result, command2Result;

                try {
                    command1Result = Integer.parseInt(command1ResultStr);
                    command2Result = Integer.parseInt(command2ResultStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(CreateMatchResultActivity.this, "Невірний формат числа", Toast.LENGTH_SHORT).show();
                    return;
                }
                dataSource.addMatchResult(command1, command2, command1Result, command2Result, currentDate);
                Toast.makeText(CreateMatchResultActivity.this, "Результати додано до бази даних", Toast.LENGTH_SHORT).show();
                command1EditText.setText("");
                command2EditText.setText("");
                command1ResultEditText.setText("");
                command2ResultEditText.setText("");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataSource.close();
    }

    public String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(new Date());
    }
}
