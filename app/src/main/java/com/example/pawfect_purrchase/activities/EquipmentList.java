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

        ProductModel equipment1 =new ProductModel(R.drawable.equipmentbutton,"equipment1","Rp 2000","desc1",4.5);
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
