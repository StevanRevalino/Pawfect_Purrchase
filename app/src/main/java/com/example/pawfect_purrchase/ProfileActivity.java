package com.example.pawfect_purrchase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    ImageView buttonBack, buttonLogOut;
    TextView emptyText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        setContentView(R.layout.profile);

        buttonBack = findViewById(R.id.btnBackToHome);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buttonLogOut = findViewById(R.id.btnLogout);
        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        emptyText = findViewById(R.id.emptyText);
        // Ambil email dari Intent
        String email = getIntent().getStringExtra("userEmail");
        if (email != null) {
            // Pisahkan nama dari email
            String name = email.split("@")[0];
            // Tampilkan nama di TextViews
            emptyText.setText(name);
        }
    }
}
