package com.example.pawfect_purrchase;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

public class ShoppingCartTabLayout extends AppCompatActivity {

    ImageView backBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart_tab_layout);

        TabLayout tabLayout = findViewById(R.id.shoppingCartTabLayout);

        // Set background buat tab Cart
        tabLayout.getTabAt(0).view.setBackgroundResource(R.drawable.tab_cart_background);

        // Set background buat tab History
        tabLayout.getTabAt(1).view.setBackgroundResource(R.drawable.tab_history_background);

        backBtn = findViewById(R.id.shoppingCartBackBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Muat fragment default (fragment_cart)
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentView, new fragment_cart());
        fragmentTransaction.commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                if (tab.getPosition() == 0) {
                    // Load fragment_cart ke FrameLayout
                    fragmentTransaction.replace(R.id.fragmentView, new fragment_cart());
                } else if (tab.getPosition() == 1) {
                    // Load fragment lain jika diperlukan (misalnya fragment_history)
                    fragmentTransaction.replace(R.id.fragmentView, new fragment_history());
                }

                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Tidak perlu melakukan apa-apa di sini
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Tidak perlu melakukan apa-apa di sini
            }
        });
    }


}
