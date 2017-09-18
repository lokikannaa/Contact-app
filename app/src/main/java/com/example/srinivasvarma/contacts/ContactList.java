package com.example.srinivasvarma.contacts;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class ContactList extends AppCompatActivity {

    int count = 0, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        this.setTitle("Contacts List");
        ScrollView scrollView = (ScrollView) findViewById(R.id.svContactList);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        for (final Contact contact : MainActivity.contactList) {
            ContactListItemUI item = new ContactListItemUI(this);
            final View itemView = (View) item;
            itemView.setId(1000 + count);
            Bitmap profilePic = CreateNewContact.decodeBase64(contact.getProfilePic());
            item.profilePic.setImageBitmap(profilePic);
            item.firstName.setText(contact.getFirstName());
            item.lastName.setText(contact.getLastName());
            item.phone.setText(contact.getPhone());
            linearLayout.addView(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = getIntent();
                    String key = intent.getStringExtra(MainActivity.ACTION_KEY);
                    id = itemView.getId() - 1000;
                    if (key.equals("DISPLAY")) {
                        Intent i = new Intent(ContactList.this, CreateNewContact.class);
                        i.putExtra(MainActivity.ACTION_KEY, "DISPLAY");
                        i.putExtra(MainActivity.CONTACT_ITEM, contact);
                        i.putExtra(MainActivity.ITEM_ID, id);
                        startActivity(i);
                    } else if (key.equals("EDIT")) {
                        Intent i = new Intent(ContactList.this, CreateNewContact.class);
                        i.putExtra(MainActivity.ACTION_KEY, "EDIT");
                        i.putExtra(MainActivity.CONTACT_ITEM, contact);
                        i.putExtra(MainActivity.ITEM_ID, id);
                        startActivityForResult(i, MainActivity.EDIT_CONTACT_CODE);
                    } else if (key.equals("DELETE")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ContactList.this);
                        builder.setTitle("Delete Contact")
                                .setMessage("Do you really want to delete this?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        MainActivity.contactList.remove(id);
                                        Toast.makeText(ContactList.this, "Contact deleted successfully", Toast.LENGTH_SHORT).show();
                                        finish();
                                        startActivity(getIntent());
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Log.d("Delete", "User clicked no");
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }
            });
            count++;
        }
        scrollView.addView(linearLayout);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data.getExtras().containsKey(MainActivity.CONTACT_KEY)) {
            Intent intent = new Intent();
            intent.putExtra(MainActivity.CONTACT_KEY, (Contact) data.getExtras().getSerializable(MainActivity.CONTACT_KEY));
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(this, "No changes made", Toast.LENGTH_SHORT).show();
        }
    }

}
