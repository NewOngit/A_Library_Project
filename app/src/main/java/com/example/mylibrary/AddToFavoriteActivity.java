package com.example.mylibrary;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AddToFavoriteActivity extends AppCompatActivity {
private AllBooksRecyclerView recViews;
private RecyclerView addToFavouriteLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_to_favorite);
        initViews();
        recViews=new AllBooksRecyclerView(this,"AddToFavouriteActivity");
        recViews.setBooksArrayList(Utils.getInstance(AddToFavoriteActivity.this).getFavouriteBooks());
        addToFavouriteLayout.setAdapter(recViews);
        addToFavouriteLayout.setLayoutManager(new LinearLayoutManager(this));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void initViews(){
        addToFavouriteLayout=findViewById(R.id.addToFavouritesLayout);
    }
}