package com.example.pawfect_purrchase;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<PetFoodModel> petFoodList;
    ListView listdata;

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

        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());

        petFoodList = new ArrayList<>();

        PetFoodModel food1 = new PetFoodModel(R.layout.activity_main,"kacang","Rp 2302032", "makanan gokil");
        PetFoodModel food2 = new PetFoodModel(R.layout.activity_main,"kacang","Rp 2302032", "makanan gokil");
        PetFoodModel food3 = new PetFoodModel(R.layout.activity_main,"kacang","Rp 2302032", "makanan gokil");
        PetFoodModel food4 = new PetFoodModel(R.layout.activity_main,"kacang","Rp 2302032", "makanan gokil");
        PetFoodModel food5 = new PetFoodModel(R.layout.activity_main,"kacang","Rp 2302032", "makanan gokil");

        petFoodList.add(food1);
        petFoodList.add(food2);
        petFoodList.add(food3);
        petFoodList.add(food4);
        petFoodList.add(food5);

    }
}