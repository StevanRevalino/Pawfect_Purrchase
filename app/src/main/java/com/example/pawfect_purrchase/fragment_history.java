package com.example.pawfect_purrchase;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_history#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_history extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_history() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_history.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_history newInstance(String param1, String param2) {
        fragment_history fragment = new fragment_history();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    ListView listView;
    HistoryAdapter historyAdapter;
    List<TransactionModel> historyList;
    DatabaseHelper databaseHelper;
    Button btnDeleteAll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        listView = view.findViewById(R.id.listViewTransaction);
        databaseHelper = new DatabaseHelper(getContext());
        historyList = new ArrayList<>();

        // Ambil semua data history dari database
        Cursor cursor = databaseHelper.getAllDataHistory();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String transaction = cursor.getString(cursor.getColumnIndexOrThrow("TRANSACTIONCOUNT"));
                String totalSpent = cursor.getString(cursor.getColumnIndexOrThrow("TOTAL_SPENT"));
                historyList.add(new TransactionModel(transaction, totalSpent));
            }
            cursor.close();
        }

        // Set adapter untuk ListView
        historyAdapter = new HistoryAdapter(getContext(), historyList);
        listView.setAdapter(historyAdapter);

        // Notify adapter if new data is added
        historyAdapter.notifyDataSetChanged();

        btnDeleteAll = view.findViewById(R.id.btnDeleteAllHistory);
        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteAllHistory();
                historyList.clear();
                historyAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }


}