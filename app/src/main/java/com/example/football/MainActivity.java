package com.example.football;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private MatchResultsDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createButton = findViewById(R.id.btn_create);
        dataSource = new MatchResultsDataSource(this);
        Button detailButton = findViewById(R.id.btn_detail);
        createButton.setBackgroundTintList(getResources().getColorStateList(R.color.yellow));
        detailButton.setBackgroundTintList(getResources().getColorStateList(R.color.yellow));

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateMatchResultActivity.class);
                startActivity(intent);
            }
        });

        detailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(intent);
            }
        });
    }
}
