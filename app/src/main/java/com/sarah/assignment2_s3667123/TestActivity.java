package com.sarah.assignment2_s3667123;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sarah.assignment2_s3667123.model.Event;
import com.sarah.assignment2_s3667123.model.FileModel;
import com.sarah.assignment2_s3667123.model.FileModelImplement;
import com.sarah.assignment2_s3667123.model.Event;
import com.sarah.assignment2_s3667123.model.FileModel;
import com.sarah.assignment2_s3667123.model.FileModelImplement;


public class TestActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view1);

        FileModel model = FileModelImplement.getSingletonInstance(getApplicationContext());
        Event ev = model.getEventByTitle("Freaky Friday");

        String s = model.EventList();

        String[] items = new String[1];

        for (int i = 0; i < s.length(); i++) {
            items[0] = s;
        }

        ArrayAdapter myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

        ListView myListView1 = findViewById(R.id.myListView1);

        myListView1.setAdapter(myAdapter);

    }
}
