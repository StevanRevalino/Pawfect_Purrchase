package com.example.pawfect_purrchase;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    EditText edtRegistEmail, edtRegistPassword, edtRegistConfirmPassword;
    Button btnRegister;

    boolean isPasswordVisible = false;
    boolean isConfirmPasswordVisible = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        dbHelper = new DatabaseHelper(this);

        edtRegistEmail = findViewById(R.id.edtRegistEmail);
        edtRegistPassword = findViewById(R.id.edtRegistPassword);
        edtRegistConfirmPassword = findViewById(R.id.edtConfirmPassword);
        ImageView btnTogglePasswordRegister = findViewById(R.id.btnTogglePasswordRegister);
        ImageView btnToggleConfirmPassword = findViewById(R.id.btnToggleConfirmPassword);

        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtRegistEmail.getText().toString().trim();
                String password = edtRegistPassword.getText().toString().trim();
                String confirmPassword = edtRegistConfirmPassword.getText().toString().trim();

                // Validasi apakah email sudah sesuai format
                if (!isValidEmail(email)) {
                    Toast.makeText(RegisterActivity.this, "Email must contains @ and ends with .com", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (dbHelper.checkEmail(email)) {
                    Toast.makeText(RegisterActivity.this, "Email already exists", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isInserted = dbHelper.insertDataPerson(email, password);
                    if (isInserted) {
                        Toast.makeText(RegisterActivity.this, "Account Registered Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Toggle Password Visibility
        btnTogglePasswordRegister.setOnClickListener(v -> {
            if (isPasswordVisible) {
                edtRegistPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                btnTogglePasswordRegister.setImageResource(R.drawable.hide); // Ganti ke ikon "hide"
            } else {
                edtRegistPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                btnTogglePasswordRegister.setImageResource(R.drawable.show); // Ganti ke ikon "show"
            }
            isPasswordVisible = !isPasswordVisible;
            edtRegistPassword.setSelection(edtRegistPassword.length());
        });

        // Toggle Confirm Password Visibility
        btnToggleConfirmPassword.setOnClickListener(v -> {
            if (isConfirmPasswordVisible) {
                edtRegistConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                btnToggleConfirmPassword.setImageResource(R.drawable.hide); // Ganti ke ikon "hide"
            } else {
                edtRegistConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                btnToggleConfirmPassword.setImageResource(R.drawable.show); // Ganti ke ikon "show"
            }
            isConfirmPasswordVisible = !isConfirmPasswordVisible;
            edtRegistConfirmPassword.setSelection(edtRegistConfirmPassword.length());
        });
    }

    // Fungsi untuk memvalidasi format email
    private boolean isValidEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.com$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
