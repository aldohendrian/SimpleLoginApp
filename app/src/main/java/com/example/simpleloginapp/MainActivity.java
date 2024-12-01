package com.example.simpleloginapp;

import android.content.Intent; // Pastikan Intent diimpor
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Menghubungkan komponen
        EditText etUsername = findViewById(R.id.etUsername);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnGoToRegister = findViewById(R.id.btnGoToRegister); // Pastikan ID ini ada di XML
        TextView tvResult = findViewById(R.id.tvResult);
        Button btnForgotPassword = findViewById(R.id.btnForgotPassword); // Tombol Forgot Password

        // Logika tombol login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUsername = etUsername.getText().toString().trim();
                String inputPassword = etPassword.getText().toString().trim();

                if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
                    tvResult.setText("Username atau Password tidak boleh kosong!");
                    tvResult.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                } else if (databaseHelper.checkUser(inputUsername, inputPassword)) {
                    tvResult.setText("Login berhasil!");
                    tvResult.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                } else {
                    tvResult.setText("Username atau Password salah!");
                    tvResult.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                }
            }
        });

        // Navigasi ke RegisterActivity
        btnGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menggunakan nama kelas yang benar untuk RegisterActivity
                Intent intent = new Intent(MainActivity.this, activity_register.class); // Ubah activity_register menjadi RegisterActivity
                startActivity(intent);
            }
        });

        // Navigasi ke ForgotPasswordActivity
        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menavigasi ke ForgotPasswordActivity
                Intent intent = new Intent(MainActivity.this, ForgotPassword.class);
                startActivity(intent);
            }
        });
    }
}
