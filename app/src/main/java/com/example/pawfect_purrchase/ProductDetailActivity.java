package com.example.pawfect_purrchase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    ImageView productImage;
    TextView productName, productPrice, productDescription;
    ImageView btnBack;
    ImageView addToCartBtn;
    DatabaseHelper databaseHelper;
    int quantity = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);

        // Inisialisasi view
        productImage = findViewById(R.id.productDetailImg);
        productName = findViewById(R.id.detailProductTitle);
        productPrice = findViewById(R.id.productDetailPrice);
        productDescription = findViewById(R.id.productDetailDesc);

        // Ambil data yang dikirimkan dari activity sebelumnya
        Intent intent = getIntent();
        int image = intent.getIntExtra("image", 0);
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        String description = intent.getStringExtra("description");

        if (image == 0 || name == null || price == null || description == null) {
            Log.e("ProductDetailActivity", "Received invalid data");
        }

        // Set data ke views
        productImage.setImageResource(image);
        productName.setText(name);
        productPrice.setText(price);
        productDescription.setText(description);

        // Inisialisasi DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        addToCartBtn = findViewById(R.id.productDetailAddToCartBtn);
        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Menambah produk ke keranjang, menambahkan quantity secara lokal
                boolean isInserted = databaseHelper.insertDataCart(image, name, price, description);

                if (isInserted) {
                    Toast.makeText(ProductDetailActivity.this, "Product added to cart", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProductDetailActivity.this, "Failed to add product to cart", Toast.LENGTH_SHORT).show();
                }

                // Kembali ke halaman sebelumnya setelah menambah produk ke keranjang
                finish();
            }
        });

        btnBack = findViewById(R.id.detailProductBackBtn);
        btnBack.setOnClickListener(v -> {
            finish();
        });
    }
}
