package com.example.cs496week1.ui.main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.cs496week1.Commons;
import com.example.cs496week1.ContactDetailActivity;
import com.example.cs496week1.R;

import java.util.ArrayList;

class ContactRVAdapter extends RecyclerView.Adapter<ContactRVAdapter.ViewHolder> {

    // creating variables for context and array list.
    private Context context;

    // creating a constructor
    public ContactRVAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ContactRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // passing our layout file for displaying our card item
        return new ContactRVAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.contacts_rv_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactRVAdapter.ViewHolder holder, int position) {
        // getting data from array list in our modal.
        // on below line we are setting data to our text view.
        holder.contactTV.setText(Commons.peopleArrayList.get(position).getName());


//        // generate random color
//        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
//        int color = generator.getRandomColor();
//        // circular drawable with random color and first text
//        TextDrawable drawable = new TextDrawable.Builder()
//                .setShape(TextDrawable.SHAPE_ROUND)
//                .setText(Commons.peopleArrayList.get(position).getName().substring(0, 1))
//                .setColor(color)
//                .build();

        Resources resources = context.getResources();
        String mDrawableName = "photo" + Commons.peopleArrayList.get(position).getId();
        int imageID = resources.getIdentifier(mDrawableName , "drawable", context.getPackageName());
        Drawable drawable = resources.getDrawable(imageID);

        // setting image to our image view on below line.
        holder.contactIV.setImageDrawable(drawable);
        // on below line we are adding on click listener to our item of recycler view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are opening a new activity and passing data to it.
                Intent i = new Intent(context, ContactDetailActivity.class);
                i.putExtra("name", Commons.peopleArrayList.get(holder.getAdapterPosition()).getName());
                i.putExtra("numb", Commons.peopleArrayList.get(holder.getAdapterPosition()).getNumb());
                i.putExtra("id", Commons.peopleArrayList.get(holder.getAdapterPosition()).getId());
                i.putExtra("univSid", Commons.peopleArrayList.get(
                        holder.getAdapterPosition()).getUniv() + " " +
                        Commons.peopleArrayList.get(holder.getAdapterPosition()).getSid());
                // on below line we are starting a new activity,
                context.startActivity(i);
            }
        });

        String contactNumb = Commons.peopleArrayList.get(position).getNumb();

        holder.callIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to make a call.
                makeCall(contactNumb);
            }
        });

        holder.messageIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to send message
                sendMessage(contactNumb);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Commons.peopleArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // on below line creating a variable
        // for our image view and text view.
        private ImageView contactIV, callIV, messageIV;
        private TextView contactTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our image view and text view.
            contactIV = itemView.findViewById(R.id.idIVContact);
            contactTV = itemView.findViewById(R.id.idTVContactName);
            callIV = itemView.findViewById(R.id.idIVContactsCall);
            messageIV = itemView.findViewById(R.id.idIVContactsMessage);
        }
    }

    private void sendMessage(String contactNumber) {
        // in this method we are calling an intent to send sms.
        // on below line we are passing our contact number.
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + contactNumber));
        intent.putExtra("sms_body", "Enter your message");
        context.startActivity(intent);
    }

    private void makeCall(String contactNumber) {
        // this method is called for making a call.
        // on below line we are calling an intent to make a call.
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        // on below line we are setting data to it.
        callIntent.setData(Uri.parse("tel:" + contactNumber));
        // on below line we are checking if the calling permissions are granted not.
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        // at last we are starting activity.
        context.startActivity(callIntent);
    }
}

