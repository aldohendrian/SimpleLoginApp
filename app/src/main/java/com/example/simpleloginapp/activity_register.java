package com.example.simpleloginapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activity_register extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inisialisasi DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Menghubungkan komponen
        EditText etRegisterUsername = findViewById(R.id.etRegisterUsername); // Pastikan ID sesuai di XML
        EditText etRegisterPassword = findViewById(R.id.etRegisterPassword); // Pastikan ID sesuai di XML
        EditText etRegisterEmail = findViewById(R.id.etRegisterEmail); // Tambahkan jika ada input email
        Button btnRegister = findViewById(R.id.btnRegister);
        TextView tvRegisterResult = findViewById(R.id.tvRegisterResult);

        // Event klik tombol register
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUsername = etRegisterUsername.getText().toString().trim();
                String newPassword = etRegisterPassword.getText().toString().trim();
                String newEmail = etRegisterEmail.getText().toString().trim(); // Tambahkan jika diperlukan

                if (newUsername.isEmpty() || newPassword.isEmpty() || newEmail.isEmpty()) {
                    tvRegisterResult.setText("Semua field harus diisi!");
                    tvRegisterResult.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                } else if (databaseHelper.isUsernameExist(newUsername)) {
                    tvRegisterResult.setText("Username sudah terdaftar!");
                    tvRegisterResult.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                } else {
                    // Simpan ke database
                    boolean isInserted = databaseHelper.addUser(newUsername, newPassword, newEmail); // Sesuaikan dengan metode terbaru
                    if (isInserted) {
                        tvRegisterResult.setText("Registrasi berhasil!");
                        tvRegisterResult.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                        finish(); // Kembali ke halaman login
                    } else {
                        tvRegisterResult.setText("Registrasi gagal. Coba lagi.");
                        tvRegisterResult.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                    }
                }
            }
        });
    }
}
