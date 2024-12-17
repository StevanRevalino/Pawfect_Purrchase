package com.example.pawfect_purrchase.fragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pawfect_purrchase.R;
import com.example.pawfect_purrchase.models.ProductModel;
import com.example.pawfect_purrchase.utils.CartItemAdapter;
import com.example.pawfect_purrchase.utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_cart#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_cart extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_cart() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_cart.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_cart newInstance(String param1, String param2) {
        fragment_cart fragment = new fragment_cart();
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

    List<ProductModel> productList;
    ListView listView;
    TextView totalPriceTextView;
    DatabaseHelper databaseHelper;
    ImageView btnCheckOut;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        listView = view.findViewById(R.id.listViewCart);
        totalPriceTextView = view.findViewById(R.id.cartTotalPrice);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(getContext());

        // Fetch all cart data from the database
        productList = new ArrayList<>();
        Cursor cursor = databaseHelper.getAllDataCart();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int image = cursor.getInt(cursor.getColumnIndexOrThrow("IMAGE"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("NAME"));
                String price = cursor.getString(cursor.getColumnIndexOrThrow("PRICE"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("DESCRIPTION"));

                productList.add(new ProductModel(image, name, price, description));
            }
            cursor.close();
        }

        // Set the adapter for the ListView
        CartItemAdapter adapter = new CartItemAdapter(requireContext(), productList, totalPriceTextView);
        listView.setAdapter(adapter);

        // Update total price after setting data
        adapter.updateTotalPrice();

        btnCheckOut = view.findViewById(R.id.cartCheckOutBtn);
        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String totalSpent = totalPriceTextView.getText().toString();

                boolean isAdded = databaseHelper.addTransaction(totalSpent);
                if (isAdded) {
                    Toast.makeText(getContext(), "Checkout successful! Transaction saved to history.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Failed to save transaction.", Toast.LENGTH_SHORT).show();
                }

                databaseHelper.deleteAllDataCart();
                productList.clear();
                adapter.notifyDataSetChanged();

                totalPriceTextView.setText("Rp 0");

                Toast.makeText(getContext(), "Checkout successful!.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}