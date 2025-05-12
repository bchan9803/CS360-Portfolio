package com.bryanchan.cs360_project;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.Manifest;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InventoryActivity extends AppCompatActivity {

    ArrayList<ItemModel> itemModels = new ArrayList<>();
    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;
    Button addItemBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inventory);


        // for recycler view //
        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);
        setupItemModels();
        Item_RecyclerViewAdapter adapter = new Item_RecyclerViewAdapter(this, itemModels);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // check for permissions //
        if (checkSMSPermission(Manifest.permission.SEND_SMS)) {
            // if permissions approved
            sendSMS();
        } else {
            // if permissions NOT approved
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
        }

        // add item btn //
        addItemBtn = findViewById(R.id.addItemBtn);
        addItemBtn.setOnClickListener(v-> {
            Intent intent = new Intent(InventoryActivity.this, AddItemActivity.class);
            startActivity(intent);
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void setupItemModels() {
        // arrays of itemName, itemCount
        String[] itemNames = new String[] {"Chocolate", "Grape juice", "Coke Zero", "Sour candy"};
        int[] itemCount = new int[] {10, 4, 7, 15};

        // append arrays to item model
        for (int i = 0; i < itemNames.length; i++) {
            itemModels.add(new ItemModel(itemNames[i], itemCount[i]));
        }
    }

    private boolean checkSMSPermission(String permission) {
        int checkPermission = ContextCompat.checkSelfPermission(this, permission);
        return (checkPermission == PackageManager.PERMISSION_GRANTED);
    }

    private void sendSMS() {
        String phoneNumber = "5551234567";
        String smsMessage = "FROM TRACK!: Item has run out of stock!";

        if (checkSMSPermission(Manifest.permission.SEND_SMS)) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, smsMessage, null, null);
            Toast.makeText(this, "message sent!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "permission denied!", Toast.LENGTH_SHORT).show();
        }
    }

    private void requestSMSPermissions() {
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
    }
}
