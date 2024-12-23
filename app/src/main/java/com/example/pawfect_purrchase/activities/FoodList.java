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

        ProductModel food1 =new ProductModel(R.drawable.pedigree,"Pedigree Dog Food 3kg","Rp 120000","Made with natural ingredients, this pack has all the nutritional needs of your pet!",randomRating);
        foodList.add(food1);

        ProductModel food2 =new ProductModel(R.drawable.kibblesnbits,"Kibbles n Bits Dog Food 20kg","Rp 320000","Made with natural ingredients, this pack has all the nutritional needs of your pet!",randomRating);
        foodList.add(food2);

        ProductModel food3 =new ProductModel(R.drawable.meocannedfood,"Me-O Canned Wet Cat Food 400g","Rp 25000","Made with natural ingredients, this pack has all the nutritional needs of your pet!",randomRating);
        foodList.add(food3);

        ProductModel food4 =new ProductModel(R.drawable.pyramidhill,"Pyramid Hill Canned Dog Food 400g","Rp 32000","Made with natural ingredients, this pack has all the nutritional needs of your pet!",randomRating);
        foodList.add(food4);

        ProductModel food5 =new ProductModel(R.drawable.swissenergy,"Swiss Energy Pets Pet Food 5kg","Rp 40000","Made with natural ingredients, this pack has all the nutritional needs of your pet!",randomRating);
        foodList.add(food5);

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
