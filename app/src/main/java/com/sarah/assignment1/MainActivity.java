package com.sarah.assignment1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> events=new ArrayList<>();
    static ArrayAdapter arrayAdapter;

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        if (item.getItemId() ==R.id.addEvent){


            //String content = edtEditText.getText().toString();
            //adding data from EventsCLass and passing it to EventEdiorClass
            EventsClass eventsClassObj = new EventsClass("John", "01/02/2018","01/02/2018","01/02/2018",
                    "01/02/2018","01/02/2018","01/02/2018");
            Intent  intent =  new Intent(MainActivity.this, EventEditorActivity.class);
            intent.putExtra("events",eventsClassObj );
            startActivity(intent);
            return  true;
        }
        return  false;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView=(ListView)findViewById(R.id.listView);

        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("com.sarah.assingment1", Context.MODE_PRIVATE);
        HashSet set=(HashSet<String>)sharedPreferences.getStringSet("notes",null);
        events.add("Example note");

        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,events);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent=new Intent(getApplicationContext(),EventEditorActivity.class);
                intent.putExtra("noteId", i);
               startActivity(intent);


            }

        });
        //pop up message when user wants to delete an event
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
                final int itemToDelete=i;
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this event?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                events.remove(itemToDelete);
                                arrayAdapter.notifyDataSetChanged();
                                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("com.sarah.assignment1", Context.MODE_PRIVATE);
                                HashSet<String> set=new HashSet(MainActivity.events);
                                sharedPreferences.edit().putStringSet("notes",set).apply();

                            }
                        })
                        .setNegativeButton("No",null)
                        .show();
                return true;




            }
        });
    }


}


