package com.example.mylibrary;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {
    private RecyclerView rcl;
    private AllBooksRecyclerView allBooksRecyclerView;
    private ArrayList<Books> books;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_books);
        initView();
        books.add(new Books(05,"Neer Ka Nirman Phir Phir", "Harivansh Rai Bacchan",456,"r","jal Ke baare mein","",false));
        books.add(new Books(10,"Godan", "Munshi Premchand",345,"r","Ek Kahani", "",false));
        books.add(new Books(15,"PrithviRaj Raso","ChandraBaradai",765,"r","swatnatrat Sangram","",false));
        books.add(new Books(20,"Bhartendu", "Bhartendu HarishChandra",635,"r","ek Natak","",false));
        books.add(new Books(25,"Kucch Kam Karo Kuchh Kaam Karo Jag Mein Rahkar Kuchh Naam Karo", "not ",983,"r","prenana Kavita","",false));
        allBooksRecyclerView.setBooksArrayList(Utils.getInstance(AllBooksActivity.this).getAllBooks());
        rcl.setAdapter(allBooksRecyclerView);
        //rcl.setLayoutManager(new LinearLayoutManager(this));
        rcl.setLayoutManager(new LinearLayoutManager(this));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.alreadyReadBooksRly), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void initView (){
    rcl=findViewById(R.id.rcl);
    allBooksRecyclerView=new AllBooksRecyclerView(this,"AllBooksActivity");
    books=new ArrayList<>();
    }
}