package com.example.pawfect_purrchase;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.tabs.TabLayout;

public class ShoppingCartTabLayout extends AppCompatActivity {
    @SuppressLint("ResourceType")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart_tab_layout);

        TabLayout tabLayout = findViewById(R.id.shoppingCartTabLayout);

        // Set background buat tab Cart
        tabLayout.getTabAt(0).view.setBackgroundResource(R.drawable.tab_cart_background);

        // Set background buat tab History
        tabLayout.getTabAt(1).view.setBackgroundResource(R.drawable.tab_history_background);

    }


}
