package com.bryanchan.cs360_project;

import com.bryanchan.cs360_project.UsersDB;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class RegisterActivity extends AppCompatActivity {
    private static class UsersDB_m extends UsersDB {
        public UsersDB_m(Context context) {
            super(context);
        }
    }
    Button registerSubmitBtn;
    EditText registerEmailInputField;
    EditText registerPasswordInputField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        registerEmailInputField = findViewById(R.id.registerEmailInputField);
        registerPasswordInputField = findViewById(R.id.registerPasswordInputField);

        registerEmailInputField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //
            }

            @Override
            public void afterTextChanged(Editable s) {
                UsersDB_m.addUser(s.toString());
            }
        });


        registerSubmitBtn = findViewById(R.id.registerSubmitBtn);
        registerSubmitBtn.setOnClickListener(v-> {
            Intent intent = new Intent(RegisterActivity.this, InventoryActivity.class);
            startActivity(intent);
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
