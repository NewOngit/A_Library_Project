package com.example.mylibrary;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.util.Util;

public class CurrentlyReadingBooksActivity extends AppCompatActivity {
private  AllBooksRecyclerView recViews;
private RecyclerView currentlyReadingBooksLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_currently_reading_books);

        initViews();
        recViews=new AllBooksRecyclerView(this,"CurrentlyReadingBooksActivity");
        recViews.setBooksArrayList(Utils.getInstance(CurrentlyReadingBooksActivity.this).getCurrentlyReadingBooks());
        currentlyReadingBooksLayout.setAdapter(recViews);
        currentlyReadingBooksLayout.setLayoutManager(new LinearLayoutManager(this));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void initViews(){
        currentlyReadingBooksLayout=findViewById(R.id.currentlyReadingBooksLayout);
    }
}