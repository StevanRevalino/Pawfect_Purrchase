package com.example.pawfect_purrchase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pawfect_purrchase.utils.ContentAdapter;
import com.example.pawfect_purrchase.models.ProductModel;
import com.example.pawfect_purrchase.R;

import java.util.ArrayList;
import java.util.List;

public class ToysList extends AppCompatActivity {
    List<ProductModel> toyList;
    ListView listDataToy;
    Button btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_view_toys);

        listDataToy = findViewById(R.id.listViewToysProduct);
        toyList = new ArrayList<>();

        ProductModel toy1 =new ProductModel(R.drawable.toysbutton,"toy1","Rp 2000","desc1",5);
        toyList.add(toy1);

        ContentAdapter adapter = new ContentAdapter(getApplicationContext(),toyList);
        listDataToy.setAdapter(adapter);

        btnBack = findViewById(R.id.btnBackToys);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listDataToy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ProductModel selectedProduct = toyList.get(i);

                // Kirim data produk ke activity ProductDetailActivity
                Intent intent = new Intent(ToysList.this, ProductDetailActivity.class);
                intent.putExtra("image", selectedProduct.getImage());
                intent.putExtra("name", selectedProduct.getName());
                intent.putExtra("price", selectedProduct.getPrice());
                intent.putExtra("description", selectedProduct.getDescription());
                intent.putExtra("rating", selectedProduct.getRating());

                startActivity(intent);
            }
        });

    }
}
