package com.example.simpleloginapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPassword extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // Inisialisasi DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Menghubungkan komponen
        EditText etForgotUsername = findViewById(R.id.etForgotUsername);
        EditText etForgotNewPassword = findViewById(R.id.etForgotNewPassword);
        Button btnSubmitForgotPassword = findViewById(R.id.btnSubmitForgotPassword);
        TextView tvForgotResult = findViewById(R.id.tvForgotResult);

        // Logika untuk tombol Submit
        btnSubmitForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etForgotUsername.getText().toString().trim();
                String newPassword = etForgotNewPassword.getText().toString().trim();

                // Validasi input
                if (username.isEmpty() || newPassword.isEmpty()) {
                    tvForgotResult.setText("Username dan Password baru tidak boleh kosong!");
                    tvForgotResult.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                    return;
                }

                // Periksa apakah username terdaftar
                if (databaseHelper.isUsernameExist(username)) {
                    // Update password untuk username tersebut
                    boolean isUpdated = databaseHelper.updatePassword(username, newPassword);
                    if (isUpdated) {
                        tvForgotResult.setText("Password berhasil diubah!");
                        tvForgotResult.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                    } else {
                        tvForgotResult.setText("Terjadi kesalahan saat mengubah password.");
                        tvForgotResult.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                    }
                } else {
                    tvForgotResult.setText("Username tidak ditemukan!");
                    tvForgotResult.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                }
            }
        });
    }
}
