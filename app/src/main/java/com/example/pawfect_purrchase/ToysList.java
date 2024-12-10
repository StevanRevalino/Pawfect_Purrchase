package com.example.pawfect_purrchase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ToysList extends AppCompatActivity {
    List<ProductModel> ToyList;
    ListView listDataToy;
    Button btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_view_toys);

        listDataToy = findViewById(R.id.listViewToysProduct);
        ToyList = new ArrayList<>();

        ProductModel toy1 =new ProductModel(R.drawable.toysbutton,"toy1","Rp 2000","desc1");
        ToyList.add(toy1);

        ContentAdapter adapter = new ContentAdapter(getApplicationContext(),ToyList);
        listDataToy.setAdapter(adapter);

        btnBack = findViewById(R.id.btnBackToys);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
