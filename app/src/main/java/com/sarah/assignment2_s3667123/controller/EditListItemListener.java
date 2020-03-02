package com.sarah.assignment2_s3667123.controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sarah.assignment2_s3667123.view.EditListItemActivity;

public class EditListItemListener implements View.OnClickListener{
    // Listener to naviage to Edit or create Event Activity.

    private String TAG = getClass().getName();

    private String itemId;
    private Context context;

    public EditListItemListener(Context context, String itemId) {
        this.context = context;
        this.itemId = itemId;
    }

    @Override
    public void onClick(View v) {
        //Naviage to Create or edit Event Activity.
        Intent editItemIntent = new Intent(context, EditListItemActivity.class);
        editItemIntent.putExtra(Intent.EXTRA_TEXT, itemId);
        editItemIntent.putExtra("FromAddEvent", "Edit Event");
        editItemIntent.setType("text/plain");

        if(editItemIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(editItemIntent);
        } else {
            Log.i(TAG, "Cannot open activity for this intent");
        }
    }
}
