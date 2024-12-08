package com.example.pawfect_purrchase;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

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

        ProductModel equipment1 =new ProductModel(R.drawable.equipmentbutton,"equipment1","Rp 2000","desc1");
        equipmentList.add(equipment1);

        ContentAdapter adapter = new ContentAdapter(getApplicationContext(),equipmentList);
        listDataEquipment.setAdapter(adapter);

        btnBack = findViewById(R.id.btnBackEquip);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
