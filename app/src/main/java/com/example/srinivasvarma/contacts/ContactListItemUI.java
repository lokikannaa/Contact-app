package com.example.srinivasvarma.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Lokesh on 16/09/2017.
 */

public class ContactListItemUI extends LinearLayout {
    public ImageView profilePic;
    public TextView firstName, lastName, phone;
    public ContactListItemUI(Context context) {
        super(context);
        inflateXML(context);
    }
    private void inflateXML(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.contactlistitem, this);
        this.profilePic = (ImageView) findViewById(R.id.ivProfilePicture);
        this.firstName = (TextView) findViewById(R.id.tvFirstName);
        this.lastName = (TextView) findViewById(R.id.tvLastName);
        this.phone = (TextView) findViewById(R.id.tvPhoneNumber);
    }
}
