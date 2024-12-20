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
import java.util.Random;

public class FoodList extends AppCompatActivity {
    List<ProductModel> foodList;
    ListView listDataFood;
    Button btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_view_food);

        listDataFood = findViewById(R.id.listViewFoodProduct);
        foodList = new ArrayList<>();

        Random random = new Random();
        double randomRating = 4.5 + (5.0 - 4.5) * random.nextDouble();

        ProductModel food1 =new ProductModel(R.drawable.foodbutton,"food1","Rp 2000","desc1",randomRating);
        foodList.add(food1);

        ContentAdapter adapter = new ContentAdapter(getApplicationContext(),foodList);
        listDataFood.setAdapter(adapter);

        btnBack = findViewById(R.id.btnBackFood);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listDataFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ProductModel selectedProduct = foodList.get(i);

                // Kirim data produk ke activity ProductDetailActivity
                Intent intent = new Intent(FoodList.this, ProductDetailActivity.class);
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
