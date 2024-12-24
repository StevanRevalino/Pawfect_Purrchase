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

public class EquipmentList extends AppCompatActivity {
    List<ProductModel> equipmentList;
    ListView listDataEquipment;
    Button btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_view_equipment);

        listDataEquipment = findViewById(R.id.listViewEquipProduct);
        equipmentList = new ArrayList<>();

        Random random = new Random();

        double randomRating1 = 4.5 + (5.0 - 4.5) * random.nextDouble();
        double randomRating2 = 4.5 + (5.0 - 4.5) * random.nextDouble();
        double randomRating3 = 4.5 + (5.0 - 4.5) * random.nextDouble();
        double randomRating4 = 4.5 + (5.0 - 4.5) * random.nextDouble();
        double randomRating5 = 4.5 + (5.0 - 4.5) * random.nextDouble();

        ProductModel equipment1 =new ProductModel(R.drawable.pethouse,"Big Fancy Grey Pet House","Rp 600000","Keep your furry friends out of rain with this fancy little shelter. SIZE 100x100cm",randomRating1);
        equipmentList.add(equipment1);

        ProductModel equipment2 =new ProductModel(R.drawable.largecattowerforindoorcats,"Large Cat Tower for Indoor Cats","Rp 1100000","Start building your kitty empire with us! With a unique structure and intricate design, your felines are guaranteed to love their new home. HEIGHT 170CM",randomRating2);
        equipmentList.add(equipment2);

        ProductModel equipment3 =new ProductModel(R.drawable.harnessforyourdogsandcats,"Harness for Your Dogs and Cats","Rp 40000","A walk with these bad boys will guarantee a lot of tugging and pulling. But hey, it’s certainly worth it!",randomRating3);
        equipmentList.add(equipment3);

        ProductModel equipment4 =new ProductModel(R.drawable.petprotectioncone,"Pet Protection Cone","Rp 20000","Keep your pets from harming others and themselves with the pet cone.",randomRating4);
        equipmentList.add(equipment4);

        ProductModel equipment5 =new ProductModel(R.drawable.petgroomingkit,"Pet Grooming Kit","Rp 550000","Ensure your pet’s hygiene with these high quality tools. Pets often reflect their owners!",randomRating5);
        equipmentList.add(equipment5);

        ContentAdapter adapter = new ContentAdapter(getApplicationContext(),equipmentList);
        listDataEquipment.setAdapter(adapter);

        btnBack = findViewById(R.id.btnBackEquip);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listDataEquipment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ProductModel selectedProduct = equipmentList.get(i);

                // Kirim data produk ke activity ProductDetailActivity
                Intent intent = new Intent(EquipmentList.this, ProductDetailActivity.class);
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
