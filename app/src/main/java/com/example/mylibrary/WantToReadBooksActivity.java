package com.example.mylibrary;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WantToReadBooksActivity extends AppCompatActivity {
private AllBooksRecyclerView recViews;
private RecyclerView wantToReadLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_want_to_read_books);
        initViews();
        recViews=new AllBooksRecyclerView(this,"WantToReadBooksActivity");

        recViews.setBooksArrayList(Utils.getInstance(WantToReadBooksActivity.this).getWantToReadBooks());
        wantToReadLayout.setAdapter(recViews);
        wantToReadLayout.setLayoutManager(new LinearLayoutManager(this));
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void initViews(){
        wantToReadLayout=findViewById((R.id.wantToReadLayout));
    }
}