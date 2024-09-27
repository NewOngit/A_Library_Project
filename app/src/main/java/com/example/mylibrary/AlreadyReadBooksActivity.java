package com.example.mylibrary;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AlreadyReadBooksActivity extends AppCompatActivity {
private AllBooksRecyclerView recView;
private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_already_read_books);
        initViews();

        recView=new AllBooksRecyclerView(this,"AlreadyReadBooksActivity");
        recView.setBooksArrayList(Utils.getInstance(AlreadyReadBooksActivity.this   ).getAlreadyReadBooks());
        recyclerView.setAdapter(recView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.alreadyReadBooksRly), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void initViews(){
        recyclerView=findViewById(R.id.alreadyReadBooksRcl);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(AlreadyReadBooksActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}