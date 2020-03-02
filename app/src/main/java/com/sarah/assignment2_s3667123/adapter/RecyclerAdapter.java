package com.sarah.assignment2_s3667123.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.sarah.assignment2_s3667123.R;
import com.sarah.assignment2_s3667123.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    // Adapter is created to display the Contact in tne list.
    private LayoutInflater layoutInflater;
    public List<Contacts> cont;
    Contacts list;
    private ArrayList<Contacts> arraylist;
    boolean checked = false;
    View vv;


    public RecyclerAdapter(LayoutInflater inflater, List<Contacts> items) {
        // Constructor for Adapter
        this.layoutInflater = inflater;
        this.cont = items;
        this.arraylist = new ArrayList<Contacts>();
        this.arraylist.addAll(cont);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create View holder for the contact based on Live view data.
        View v = layoutInflater.inflate(R.layout.contact_row_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //Bind View holder for the contact based on Live view data.
        list = cont.get(position);

        String name = (list.getName());

        holder.title.setText(name);
        holder.phone.setText(list.getPhone());

        holder.chkSelected.setOnCheckedChangeListener(null);
        holder.chkSelected.setChecked(cont.get(position).isSelected());
        holder.chkSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cont.get(holder.getAdapterPosition()).setSelected(isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cont.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // Holder to show title, phone numbar and checkbox selection.
        public TextView title;
        public TextView phone;
        public CheckBox chkSelected;

        public ViewHolder(View itemView) {
            super(itemView);
            this.setIsRecyclable(false);
            title = (TextView) itemView.findViewById(R.id.name);
            phone = (TextView) itemView.findViewById(R.id.no);
            chkSelected = (CheckBox) itemView
                    .findViewById(R.id.chkSelected);

        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public List<Contacts> getStudentist() {
        return cont;
    }

}