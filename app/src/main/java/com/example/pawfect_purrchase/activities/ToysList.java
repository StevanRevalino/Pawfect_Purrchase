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

        Random random = new Random();
        double randomRating = 4.5 + (5.0 - 4.5) * random.nextDouble();

        ProductModel toy1 =new ProductModel(R.drawable.sausagedog,"Sausage Dog Chew Toy","Rp 25000","Got a needy dog? Look no further, get a chew buddy for your lonely fella!",randomRating);
        toyList.add(toy1);

        ProductModel toy2 =new ProductModel(R.drawable.interactiveelectric,"Interactive Electric Mouse Toy","Rp 90000","This mouse will keep your cat busy while you’re handling other matters. DO NOT USE NEAR WATER!",randomRating);
        toyList.add(toy2);

        ProductModel toy3 =new ProductModel(R.drawable.minimouse,"Mini Fluffy Mouse Toy","Rp 10000","Your cat will never get tired of this one. Keep them busy with a cute mouse! TREAT WITH CARE NEAR FIRE!",randomRating);
        toyList.add(toy3);

        ProductModel toy4 =new ProductModel(R.drawable.catwalk,"Catwalk Wheel","Rp 300000","Keep your cat healthy, fit, and happy with this neverending spin! Your cat might even convince itself as a hamster…",randomRating);
        toyList.add(toy4);

        ProductModel toy5 =new ProductModel(R.drawable.wishbone,"Wishbone Dog Chew Toy","Rp 15000","Keep your dog's jaws on this one and they will surely never let go…",randomRating);
        toyList.add(toy5);

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
