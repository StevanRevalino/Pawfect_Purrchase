package com.example.pawfect_purrchase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pawfect_purrchase.R;

public class MainActivity extends AppCompatActivity {
    ImageView equipmentBtn, foodBtn, toysBtn;
    ImageView homeInfo;
    ImageView userProfile;
    ImageView btnShoppingCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        equipmentBtn = findViewById(R.id.btnEquipment);
        foodBtn = findViewById(R.id.btnFood);
        toysBtn = findViewById(R.id.btnToys);
        homeInfo = findViewById(R.id.btnHomeInfo);

        equipmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EquipmentList.class);
                startActivity(intent);
            }
        });

        foodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FoodList.class);
                startActivity(intent);
            }
        });

        toysBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ToysList.class);
                startActivity(intent);
            }
        });

        userProfile = findViewById(R.id.imageUserProfile);
        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("userEmail", getIntent().getStringExtra("userEmail")); // Teruskan email
                startActivity(intent);
            }
        });

        homeInfo = findViewById(R.id.btnHomeInfo);
        homeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });

        btnShoppingCart = findViewById(R.id.btnHomeShop);
        btnShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShoppingCartTabLayout.class);
                startActivity(intent);
            }
        });
    }
}