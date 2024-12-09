package com.example.pawfect_purrchase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class FoodList extends AppCompatActivity {
    List<ProductModel> FoodList;
    ListView listDataFood;
    Button btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_view_food);

        listDataFood = findViewById(R.id.listViewFoodProduct);
        FoodList = new ArrayList<>();

        ProductModel food1 =new ProductModel(R.drawable.foodbutton,"food1","Rp 2000","desc1");
        FoodList.add(food1);

        ContentAdapter adapter = new ContentAdapter(getApplicationContext(),FoodList);
        listDataFood.setAdapter(adapter);

        btnBack = findViewById(R.id.btnBackFood);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
