package com.example.pawfect_purrchase.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pawfect_purrchase.R;
import com.example.pawfect_purrchase.models.TransactionModel;

import java.util.List;

public class HistoryAdapter extends ArrayAdapter<TransactionModel> {

    private final Context context;
    private final List<TransactionModel> historyList;

    public HistoryAdapter(@NonNull Context context, List<TransactionModel> historyList) {
        super(context, R.layout.history_list, historyList);
        this.context = context;
        this.historyList = historyList;
    }

    @Override
    public int getCount() {
        return historyList.size();
    }

    @Override
    public TransactionModel getItem(int position) {
        return historyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.history_list, parent, false);
        }

        TransactionModel transaction = historyList.get(position);

        TextView transactionText = convertView.findViewById(R.id.historyTransaction);
        TextView totalSpent = convertView.findViewById(R.id.historyTotalSpent);

        transactionText.setText(transaction.getTransactionCount());
        totalSpent.setText(transaction.getTotalSpent());

        return convertView;
    }
}
