package com.example.cs496week1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class ContactDetailActivity extends AppCompatActivity {

    // creating variables for our image view and text view and string.
    private Integer contactId;
    private String contactName, contactNumb, contactUnivSid;
    private TextView contactTV, nameTV, univSidTV;
    private ImageView contactIV, callIV, messageIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        // on below line we are getting data which
        // we passed in our adapter class with intent.
        contactName = getIntent().getStringExtra("name");
        contactNumb = getIntent().getStringExtra("numb");
        contactId = getIntent().getIntExtra("id", 1);
        contactUnivSid = getIntent().getStringExtra("univSid");

        // initializing our views.
        contactTV = findViewById(R.id.idTVPhone);
        nameTV = findViewById(R.id.idTVName);
        contactIV = findViewById(R.id.idIVContact);
        callIV = findViewById(R.id.idIVCall);
        messageIV = findViewById(R.id.idIVMessage);
        univSidTV = findViewById(R.id.idTVUnivSid);

        contactTV.setText(contactNumb);
        nameTV.setText(contactName);
        univSidTV.setText(contactUnivSid);

        Resources resources = this.getResources();
        String mDrawableName = "photo" + contactId;
        int imageID = resources.getIdentifier(mDrawableName , "drawable", this.getPackageName());
        Drawable drawable = resources.getDrawable(imageID);

        contactIV.setImageDrawable(drawable);

        // on below line adding click listener for our calling image view.
        callIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to make a call.
                makeCall(contactNumb);
            }
        });

        // on below line adding on click listener for our message image view.
        messageIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to send message
                sendMessage(contactNumb);
            }
        });
    }

    private void sendMessage(String contactNumber) {
        // in this method we are calling an intent to send sms.
        // on below line we are passing our contact number.
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + contactNumber));
        intent.putExtra("sms_body", "Enter your message");
        startActivity(intent);
    }

    private void makeCall(String contactNumber) {
        // this method is called for making a call.
        // on below line we are calling an intent to make a call.
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        // on below line we are setting data to it.
        callIntent.setData(Uri.parse("tel:" + contactNumber));
        // on below line we are checking if the calling permissions are granted not.
        if (ActivityCompat.checkSelfPermission(ContactDetailActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        // at last we are starting activity.
        startActivity(callIntent);
    }
}

