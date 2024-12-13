package com.example.pawfect_purrchase;

public class TransactionModel {
    private String transactionCount;
    private String totalSpent;

    public TransactionModel(String transactionCount, String totalSpent) {
        this.transactionCount = transactionCount;
        this.totalSpent = totalSpent;
    }

    public String getTransactionCount() {
        return transactionCount;
    }

    public String getTotalSpent() {
        return totalSpent;
    }
}

