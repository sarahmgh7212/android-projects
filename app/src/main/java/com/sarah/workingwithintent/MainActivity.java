package com.sarah.workingwithintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
//https://www.youtube.com/watch?v=SK98ayjhk1E (coding with Mitch)
public class MainActivity extends AppCompatActivity {

    private static final String TAG="MainActivity";
    private ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView =(ListView) findViewById(R.id.myList);
        Log.d(TAG, "onCreate Started");

        final ArrayList<String> names= new ArrayList<>();
        names.add("Sarah");
        names.add("Hermes");
        names.add("Nazar Ghobbani");


        adapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1,names);
       listView.setAdapter(adapter);

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Log.d(TAG, "onItem click:name" + names.get(i));
             //  Toast.makeText(MainActivity.this, "You clicked on:" + names.get(i), Toast.LENGTH_LONG).show();

               Intent intent= new Intent(MainActivity.this,SecondScreen.class);

              intent.putExtra("name", names.get(i));
               startActivity(intent);
           }
       });


    }
}
