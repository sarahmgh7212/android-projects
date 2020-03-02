package com.sarah.assignment2_s3667123;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sarah.assignment2_s3667123.adapter.RecyclerAdapter;
import com.sarah.assignment2_s3667123.model.Contacts;
import com.sarah.assignment2_s3667123.model.Movie;
import com.sarah.assignment2_s3667123.view.EditListItemActivity;

import java.util.ArrayList;
import java.util.List;

public class ContactPicker extends AppCompatActivity {
// Class for contact picker.
    ArrayList<Contacts> selectedUsers;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    RecyclerAdapter adapter;
    private Button btnSelect;
    Cursor phones;
    private String eventId="";
    protected String movieId = null;
    protected Movie movie;
    protected String itemId;
    protected String startDateValue="";
    protected String endDateValue ="";
    protected String timeValue="";
    protected String endTime ="";
    protected String eventTitle = "";
    protected String venue = "";
    protected String location = "";

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Display the contct Details
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_picker);
        Intent intent = getIntent();
        eventId = intent.getExtras().getString("eventId");
        movieId =intent.getExtras().getString("movieId");
        timeValue = intent.getExtras().getString("timeValue");
        startDateValue = intent.getExtras().getString("startDateValue");
        endDateValue = intent.getExtras().getString("endDateValue");
        endTime = intent.getExtras().getString("endTime");
        eventTitle = intent.getExtras().getString("eventTitle");
        venue = intent.getExtras().getString("venue");
        location = intent.getExtras().getString("location");
        btnSelect = (Button) findViewById(R.id.btnShow);


        recyclerView = (RecyclerView) findViewById(R.id.contacts_list);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(layoutManager);
        selectedUsers = new ArrayList<Contacts>();
        showContacts();

        btnSelect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String data = "";
                List<Contacts> stList = ((RecyclerAdapter) adapter).getStudentist();
                for (int i = 0; i < stList.size(); i++) {
                    Contacts contactNames = stList.get(i);
                    if (contactNames.isSelected() == true) {

                        data = data + contactNames.getName().toString()+"\n";
                    }
                }
                Context context = ContactPicker.this;
                Intent editItemIntent = new Intent(ContactPicker.this,EditListItemActivity.class);
                editItemIntent.putExtra("Contacts",data);
                editItemIntent.putExtra(Intent.EXTRA_TEXT, eventId);
                editItemIntent.putExtra("eventTitle",eventTitle);
                editItemIntent.putExtra("startDateValue",startDateValue);
                editItemIntent.putExtra("timeValue",timeValue);
                editItemIntent.putExtra("endDateValue",endDateValue);
                editItemIntent.putExtra("endTime",endTime);
                editItemIntent.putExtra("movieId",movieId);
                editItemIntent.putExtra("location",location);
                editItemIntent.putExtra("venue",venue);

                editItemIntent.setType("text/plain");
                if(editItemIntent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(editItemIntent);
                } else {

                }
                Toast.makeText(ContactPicker.this,
                        "Selected contacts: " + data, Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    private void showContacts() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);

        } else {

            phones = getApplicationContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
            LoadContact loadContact = new LoadContact();
            loadContact.execute();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                showContacts();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class LoadContact extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... voids) {


            if (phones != null) {
                Log.e("count", "" + phones.getCount());
                if (phones.getCount() == 0) {

                }

                while (phones.moveToNext()) {
                    Bitmap bit_thumb = null;
                    String id = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                    String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));


                    Contacts selectUser = new Contacts();
                    selectUser.setName(name);
                    selectUser.setPhone(phoneNumber);
                    selectedUsers.add(selectUser);


                }
            } else {
                Log.e("Cursor is closed", "!!!");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            int count = selectedUsers.size();
            ArrayList<Contacts> removed = new ArrayList<>();
            ArrayList<Contacts> contacts = new ArrayList<>();
            for (int i = 0; i < selectedUsers.size(); i++) {
                Contacts inviteFriendsProjo = selectedUsers.get(i);

                if (inviteFriendsProjo.getName().matches("\\d+(?:\\.\\d+)?") || inviteFriendsProjo.getName().trim().length() == 0) {
                    removed.add(inviteFriendsProjo);
                    Log.d("Removed Contact", new Gson().toJson(inviteFriendsProjo));
                } else {
                    contacts.add(inviteFriendsProjo);
                }
            }
            contacts.addAll(removed);
            selectedUsers = contacts;
            adapter = new RecyclerAdapter(inflater, selectedUsers);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerView.setAdapter(adapter);

        }
    }

}