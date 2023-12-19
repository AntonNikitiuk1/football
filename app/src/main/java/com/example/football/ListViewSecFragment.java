package com.example.football;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListViewSecFragment extends Fragment implements View.OnClickListener
{

    private ListView listView;
    private MatchResultsDataSource dataSource;
    private EditText findField;
    private Button btnFind;
    private ArrayAdapter<String> adapter;

    public ListViewSecFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.list_view_fragment, container, false);
        listView = view.findViewById(R.id.listView);
        findField = view.findViewById(R.id.find_field);
        btnFind = view.findViewById(R.id.btn_find);
        btnFind.setBackgroundTintList(getResources().getColorStateList(R.color.yellow));
        btnFind.setOnClickListener(this);
        dataSource = new MatchResultsDataSource(requireContext());

        displayAllMatchResults();

        return view;
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_find:
                MainActivity mainActivity = (MainActivity)getActivity();
                String searchTerm = findField.getText().toString().trim();

                if (!searchTerm.isEmpty()) {
                    List<Result> searchResults = dataSource.searchByTeam(searchTerm);
                    updateListView(searchResults);
                } else {
                    displayAllMatchResults();
                }
                break;
        }
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
                requireContext(),
                android.R.layout.simple_list_item_1,
                resultList
        );
        listView.setAdapter(adapter);
    }
}