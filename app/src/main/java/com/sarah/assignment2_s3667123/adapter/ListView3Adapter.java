package com.sarah.assignment2_s3667123.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.sarah.assignment2_s3667123.R;
import com.sarah.assignment2_s3667123.controller.DeleteListItemListener;
import com.sarah.assignment2_s3667123.controller.EditListItemListener;
import com.sarah.assignment2_s3667123.model.Event;


public class ListView3Adapter extends ArrayAdapter<Event> {
    // Adapter is created to display the event in tne list.
    private Context context;
    private Map<String, Event> items;

    public ListView3Adapter(Context context, Map<String, Event> items) {
        // Constructor for Adapter
        super(context, 0, items.values().toArray(new Event[items.size()]));

        this.items = items;
        this.context = context;

    }


    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {
        // Create View for the event based on Live view data.
        if (listItemView == null) {
            listItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list_item2, parent, false);
        }
                TextView myTextView2 = listItemView.findViewById(R.id.myTextView2);
                TextView myTextView3 = listItemView.findViewById(R.id.myTextView3);
                TextView myTextView4 = listItemView.findViewById(R.id.myTextView4);
                TextView myTextView5 = listItemView.findViewById(R.id.myTextView5);
                TextView myTextView6 = listItemView.findViewById(R.id.myTextView6);
                TextView myTextView7 = listItemView.findViewById(R.id.myTextView7);
                String itemId = getItem(position).getEventId();
                Event item = items.get(itemId);
                Button myButton1 = listItemView.findViewById(R.id.myButton1);
                myButton1.setOnClickListener(new EditListItemListener(context, itemId));
                Button myButton2 = listItemView.findViewById(R.id.myButton2);
                myButton2.setOnClickListener(new DeleteListItemListener(context, itemId));


                Set<Map.Entry<String, Event>> entries = items.entrySet();
                List<Map.Entry<String, Event>> listOfEntries = new ArrayList<Map.Entry<String, Event>>(entries);
                Collections.sort(listOfEntries, valueComparator);


                myTextView2.setText(item.getEventTiltle());
                if (item.getEventStartDate() != null) {
                    myTextView3.setText(item.getEventStartDate().toString());
                }else{
                    myTextView3.setText("No date is selected");
                }
                 if (item.getEventStartDate() != null) {
                    myTextView4.setText(item.getEventEndDate().toString());
                 }else{
                    myTextView4.setText("No date is selected");
                 }
                myTextView5.setText(item.getEventVenue());
                if (item.EventGetMovieTitle() != null) {
                    myTextView6.setText(item.EventGetMovieTitle().getMovieTitle());
                } else {
                    myTextView6.setText("No Movie Added");
                }
                if(item.getAttendenceCount()==0) {
                    myTextView7.setText("Attendees: " + 0);
                }else{
                    myTextView7.setText("Attendees: " + item.getAttendenceCount());
                }
            return listItemView;
        }
    Comparator<Map.Entry<String, Event>> valueComparator = new Comparator<Map.Entry<String,Event>>() {

        @Override
        public int compare(Map.Entry<String, Event> e1, Map.Entry<String, Event> e2) {
            // Sort based on asc or descending using comparators
            Date v1 = e1.getValue().getEventStartDate();
            Date v2 = e2.getValue().getEventStartDate();
            return v1.compareTo(v2);
        }
    };
}
