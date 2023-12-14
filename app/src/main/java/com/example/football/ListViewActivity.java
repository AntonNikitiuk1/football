package com.example.football;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private ListView listView;
    private MatchResultsDataSource dataSource;
    private EditText findField;
    private Button btnFind;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        listView = findViewById(R.id.listView);
        findField = findViewById(R.id.find_field);
        btnFind = findViewById(R.id.btn_find);

        btnFind.setBackgroundTintList(getResources().getColorStateList(R.color.yellow));

        dataSource = new MatchResultsDataSource(this);

        displayAllMatchResults();

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchTerm = findField.getText().toString().trim();

                if (!searchTerm.isEmpty()) {
                    List<Result> searchResults = dataSource.searchByTeam(searchTerm);
                    updateListView(searchResults);
                } else {
                    displayAllMatchResults();
                }
            }
        });
    }

    private void displayAllMatchResults() {
        List<Result> matchResults = dataSource.getListMatch();
        updateListView(matchResults);
    }

    private void updateListView(List<Result> results) {
        List<String> resultList = new ArrayList<>();
        for (Result result : results) {
            String item = result.getDetails();
            resultList.add(item);
        }
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                resultList
        );
        listView.setAdapter(adapter);
    }
}
