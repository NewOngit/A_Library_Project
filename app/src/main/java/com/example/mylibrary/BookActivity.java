package com.example.mylibrary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
     public static String BOOK_ID_KEY="Book_Id";
            private Button btnCurrentlyReading,btnAlreadyRead, btnWantToRead, btnAddToFavourites;
            private TextView txtBookName, txtAuthor, txtPages, txtDesc, txtLongDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book);
        initViews();

Intent intent=getIntent();
if(null!=intent){
    int bookId= intent.getIntExtra(BOOK_ID_KEY,-1);
    if(bookId != -1){
        Books incomingBook=Utils.getInstance(BookActivity.this).getBookById(bookId);
        if (null != incomingBook);
            setData(incomingBook);

            handleAlreadyReadBooks(incomingBook);
            handleWantToReadBooks(incomingBook);
            handleCurrentlyBooks(incomingBook);
            handleFavouriteBooks(incomingBook);

    }
}

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.alreadyReadBooksRly), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void handleFavouriteBooks(Books incomingBook) {
        ArrayList<Books> favourite=Utils.getInstance(BookActivity.this).getFavouriteBooks();
        boolean isFavourite=false;

        for(Books b: favourite){
            if(incomingBook.getId()==b.getId())
                isFavourite=true;
        }
        if(isFavourite){
            btnAddToFavourites.setEnabled(false);
        }
        else {
            btnAddToFavourites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addFavouriteBooks(incomingBook)){
                        Toast.makeText(BookActivity.this, "Added to Favourite Books", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(BookActivity.this,AddToFavoriteActivity.class);
                        startActivity(intent);
                    }
                    else Toast.makeText(BookActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void handleCurrentlyBooks(Books incomingBook) {
        ArrayList<Books> currentlyRead=Utils.getInstance(BookActivity.this).getCurrentlyReadingBooks();
        boolean isCurrentlyRead=false;

        for(Books b: currentlyRead){
            if(incomingBook.getId()==b.getId())
                isCurrentlyRead=true;
        }
        if(isCurrentlyRead){
            btnCurrentlyReading.setEnabled(false);
        }
        else {
            btnCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addCurrentlyReadingBooks(incomingBook)){
                        Toast.makeText(BookActivity.this, "Added to Currently Reading Books", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(BookActivity.this,CurrentlyReadingBooksActivity.class);
                        startActivity(intent);
                    }
                    else Toast.makeText(BookActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void handleWantToReadBooks(Books incomingBook) {

        ArrayList<Books> wantToRead=Utils.getInstance(BookActivity.this).getWantToReadBooks();
        boolean isWantToRead=false;

        for(Books b: wantToRead){
            if(incomingBook.getId()==b.getId())
                isWantToRead=true;
        }
        if(isWantToRead){
            btnWantToRead.setEnabled(false);
        }
        else {
            btnWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addWantToReadBooks(incomingBook)){
                        Toast.makeText(BookActivity.this, "Added to Want To Read Books", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(BookActivity.this,WantToReadBooksActivity.class);
                        startActivity(intent);
                    }
                    else Toast.makeText(BookActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void handleAlreadyReadBooks(Books incomingBook ) {
        ArrayList<Books> alreadyRead=Utils.getInstance(BookActivity.this).getAlreadyReadBooks();
        boolean isAlreadyRead=false;

        for(Books b: alreadyRead){
            if(incomingBook.getId()==b.getId())
                isAlreadyRead=true;
        }
        if(isAlreadyRead){
            btnAlreadyRead.setEnabled(false);
        }
        else {
            btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addAlreadyReadBooks(incomingBook)){
                        Toast.makeText(BookActivity.this, "Added to Already Read Books", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(BookActivity.this,AlreadyReadBooksActivity.class);
                        startActivity(intent);
                    }
                    else Toast.makeText(BookActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void initViews(){
        btnAddToFavourites=findViewById(R.id.btnAddToFavourites);
        btnCurrentlyReading=findViewById(R.id.btnCurrentlyReading);
        btnAlreadyRead=findViewById(R.id.btnAreadyRead);
        btnWantToRead=findViewById(R.id.btnWantToRead);
        txtBookName=findViewById(R.id.txtBookName);
        txtAuthor=findViewById(R.id.txtAuthor);
        txtPages=findViewById(R.id.txtPages);
        txtDesc=findViewById(R.id.txtShortDesc);
        txtLongDesc=findViewById(R.id.txtLongDesc);

    }

    public void setData(Books book){
         //Books book=getIntent();
        txtBookName.setText(book.getBookName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(Integer.toString(book.getPages()));
        txtDesc.setText(book.getSortDesc());
        txtLongDesc.setText(book.getLongDesc());
    }

}