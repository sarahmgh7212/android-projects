package com.sarah.assignment2_s3667123.view;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sarah.assignment2_s3667123.R;
import com.sarah.assignment2_s3667123.controller.MovieEditChangeListener;
import com.sarah.assignment2_s3667123.model.FileModel;
import com.sarah.assignment2_s3667123.model.FileModelImplement;
import com.sarah.assignment2_s3667123.model.Movie;

public class MovieEditListItemActivity extends AppCompatActivity{
    // Class to edit the movie list
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list_item);

        Intent intent = getIntent();
        String movieId = (String) intent.getExtras().get(Intent.EXTRA_TEXT);

        FileModel model = FileModelImplement.getSingletonInstance(getApplicationContext());

        Movie movie = model.getMovieById(movieId);
        EditText mySubItemTextBox = findViewById(R.id.mySubItemTextBox);

        mySubItemTextBox.setText(movie.getMovieTitle());
        Button myButton1 = findViewById(R.id.myOkButton);
        myButton1.setOnClickListener(new MovieEditChangeListener<>(MovieEditListItemActivity.this,movieId));

        Toast.makeText(this, "Edit item id: " + movieId, Toast.LENGTH_SHORT).show();
    }
}
