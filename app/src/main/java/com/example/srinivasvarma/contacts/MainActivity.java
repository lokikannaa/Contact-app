package com.example.srinivasvarma.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
/*
Name: Umasankar Srinivas Varma Pusapati(800960736), Lokesh Kannan(800941529)
Group 19
File Name: HW2_Groups19.zip
 */

public class MainActivity extends AppCompatActivity {
    final static int ADD_CONTACT_CODE = 100;
    final static int EDIT_CONTACT_CODE = 101;
    final static String ACTION_KEY = "ACTION";
    final static String CONTACT_KEY = "result";
    final static String CONTACT_ITEM = "CONTACT";
    final static String ITEM_ID = "ITEM";
    public static List<Contact> contactList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnCreateNew).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateNewContact.class);
                intent.putExtra(ACTION_KEY, "ADD");
                startActivityForResult(intent, ADD_CONTACT_CODE);
            }
        });

        findViewById(R.id.btnDisplayContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactList.class);
                intent.putExtra(ACTION_KEY, "DISPLAY");
                startActivity(intent);
            }
        });

        findViewById(R.id.btnEditContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactList.class);
                intent.putExtra(ACTION_KEY, "EDIT");
                startActivityForResult(intent, EDIT_CONTACT_CODE);
            }
        });

        findViewById(R.id.btnDeleteContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactList.class);
                intent.putExtra(ACTION_KEY, "DELETE");
                startActivity(intent);
            }
        });

        findViewById(R.id.btnFinish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CONTACT_CODE) {
            if (resultCode == RESULT_OK && data.getExtras().containsKey(CONTACT_KEY)) {
                contactList.add((Contact) data.getExtras().getSerializable(CONTACT_KEY));
                Toast.makeText(this, "Contact added successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No Contacts added", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == EDIT_CONTACT_CODE) {
            if (resultCode == RESULT_OK && data.getExtras().containsKey(CONTACT_KEY)) {
                int id = data.getExtras().getInt(ITEM_ID);
                contactList.remove(id);
                contactList.add((Contact) data.getExtras().getSerializable(CONTACT_KEY));
                Toast.makeText(this, "Contact edited successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No Contacts edited", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
