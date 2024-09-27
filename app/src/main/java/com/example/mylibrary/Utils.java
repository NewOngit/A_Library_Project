package com.example.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {
    private static String ALL_BOOK_KEY="Books",CURRENTLY_READING_BOOKS="currentlyReading",WANT_TO_READ="wantToRead",ALREADY_READ="alreadyRead",FAVOURITE_BOOK="favouriteBook";
    private SharedPreferences sharedPreferences;
    public static Utils instance;

    private static ArrayList<Books>allBooks;
    private static ArrayList<Books>currentlyReadingBooks;
    private static ArrayList<Books>alreadyReadBooks;
    private static ArrayList<Books>wantToReadBooks;
    private static ArrayList<Books>favouriteBooks;

    private Utils(Context context){
        sharedPreferences=context.getSharedPreferences("mydb",context.MODE_PRIVATE);
        if(null==allBooks) {
            allBooks=new ArrayList<>();
            initData();
        }

        if(null==getCurrentlyReadingBooks())
        {
            SharedPreferences.Editor editor=sharedPreferences.edit();
            Gson gson=new Gson();
            editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(new ArrayList<Books>()));
            editor.commit();

        }
        if(null==getAlreadyReadBooks())
        {
            SharedPreferences.Editor editor=sharedPreferences.edit();
            Gson gson=new Gson();
            editor.putString(ALREADY_READ,gson.toJson(new ArrayList<Books>()));
            editor.commit();

        }
        if(null==getWantToReadBooks())
        {
            SharedPreferences.Editor editor=sharedPreferences.edit();
            Gson gson=new Gson();
            editor.putString(WANT_TO_READ,gson.toJson(new ArrayList<Books>()));
            editor.commit();
        }
        if(null==getFavouriteBooks())
        {
            SharedPreferences.Editor editor=sharedPreferences.edit();
            Gson gson=new Gson();
            editor.putString(FAVOURITE_BOOK,gson.toJson(new ArrayList<Books>()));
            editor.commit();

        }
    }

    private void initData() {

        allBooks.add(new Books(5,"Neer Ka Nirman Phir Phir", "Harivansh Rai Bacchan",456,"r","jal Ke baare mein","",false));
        allBooks.add(new Books(10,"Godan", "Munshi Premchand",345,"r","Ek Kahani", "",false));
        allBooks.add(new Books(15,"PrithviRaj Raso","ChandraBaradai",765,"r","swatnatrat Sangram","",false));
        allBooks.add(new Books(20,"Bhartendu", "Bhartendu HarishChandra",635,"r","ek Natak","",false));
        allBooks.add(new Books(25,"Kucch Kam Karo Kuchh Kaam Karo Jag Mein Rahkar Kuchh Naam Karo", "not ",983,"r","prenana Kavita","",false));
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        editor.putString(ALL_BOOK_KEY,gson.toJson(allBooks));
        editor.commit();
    }

    public static Utils  getInstance(Context context){
        if(null==instance){

            instance= new Utils(context);
            return instance;
        }

        else return instance;
    }

    public  ArrayList<Books> getAllBooks() {
        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Books>>(){}.getType();
        allBooks=gson.fromJson(sharedPreferences.getString(ALL_BOOK_KEY,null),type);
        return allBooks;
    }

    public static void setAllBooks(ArrayList<Books> allBooks) {
        Utils.allBooks = allBooks;
    }

    public  ArrayList<Books> getCurrentlyReadingBooks() {
        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Books>>(){}.getType();
        currentlyReadingBooks=gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS,null),type);
        return currentlyReadingBooks;
    }

    public static void setCurrentlyReadingBooks(ArrayList<Books> currentlyReadingBooks) {
        Utils.currentlyReadingBooks = currentlyReadingBooks;
    }

    public  ArrayList<Books> getAlreadyReadBooks() {
        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Books>>(){}.getType();
        alreadyReadBooks=gson.fromJson(sharedPreferences.getString(ALREADY_READ,null),type);
        return alreadyReadBooks;
    }

    public static void setAlreadyReadBooks(ArrayList<Books> alreadyReadBooks) {
        Utils.alreadyReadBooks = alreadyReadBooks;
    }

    public  ArrayList<Books> getWantToReadBooks() {
        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Books>>(){}.getType();
        wantToReadBooks=gson.fromJson(sharedPreferences.getString(WANT_TO_READ,null),type);
        return wantToReadBooks;
    }

    public static void setWantToReadBooks(ArrayList<Books> wantToReadBooks) {
        Utils.wantToReadBooks = wantToReadBooks;
    }

    public  ArrayList<Books> getFavouriteBooks() {
        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Books>>(){}.getType();
        favouriteBooks=gson.fromJson(sharedPreferences.getString(FAVOURITE_BOOK,null),type);
        return favouriteBooks;
    }

    public static void setFavouriteBooks(ArrayList<Books> favouriteBooks) {
        Utils.favouriteBooks = favouriteBooks;
    }

    public  Books getBookById(int bookId){
        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Books>>(){}.getType();
        ArrayList<Books>allbook=gson.fromJson(sharedPreferences.getString(ALL_BOOK_KEY,null),type);
        for(Books book: allbook)
            if(book.getId()==bookId)
                return book;
        return null;
    }

    public   boolean addAlreadyReadBooks(Books book){
        ArrayList<Books> alreadyRead=getAlreadyReadBooks();
        if(alreadyRead.add(book)){
            SharedPreferences.Editor editor=sharedPreferences.edit();
            Gson gson=new Gson();
            editor.remove(ALREADY_READ);
            editor.putString(ALREADY_READ,gson.toJson(alreadyRead));
            editor.commit();
            return true;
        }
        return false;

    }

    public  boolean addCurrentlyReadingBooks(Books book){

        ArrayList<Books> currentlyRead=getCurrentlyReadingBooks();
        if(currentlyRead.add(book)){
            SharedPreferences.Editor editor=sharedPreferences.edit();
            Gson gson=new Gson();
            editor.remove(CURRENTLY_READING_BOOKS);
            editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(currentlyRead));
            editor.commit();
            return true;
        }
        return false;

    }

    public  boolean addFavouriteBooks(Books book){

        ArrayList<Books> favouriteBooks=getFavouriteBooks();
        if(favouriteBooks.add(book)){
            SharedPreferences.Editor editor=sharedPreferences.edit();
            Gson gson=new Gson();
            editor.remove(FAVOURITE_BOOK);
            editor.putString(FAVOURITE_BOOK,gson.toJson(favouriteBooks));
            editor.commit();
            return true;
        }
        return false;

    }
    public  boolean addWantToReadBooks(Books book){

        ArrayList<Books> wantToReadBook=getWantToReadBooks();
        if(wantToReadBook.add(book)){
            SharedPreferences.Editor editor=sharedPreferences.edit();
            Gson gson=new Gson();
            editor.remove(WANT_TO_READ);
            editor.putString(WANT_TO_READ,gson.toJson(wantToReadBook));
            editor.commit();
            return true;
        }
        return false;
    }
    public   boolean removeAlreadyReadBooks(Books book){
        ArrayList<Books> alreadyRead=getAlreadyReadBooks();
        if(null!=alreadyRead){
            for(Books b:alreadyRead){
                if(b.getId()==book.getId()){
                    if(alreadyRead.remove(b)){
                        SharedPreferences.Editor editor=sharedPreferences.edit();
            Gson gson=new Gson();
            editor.remove(ALREADY_READ);
            editor.putString(ALREADY_READ,gson.toJson(alreadyRead));
            editor.commit();
            return true;
                    }
                }
            }

        }
        //
//        if(alreadyRead.remove(book)){
//
//            SharedPreferences.Editor editor=sharedPreferences.edit();
//            Gson gson=new Gson();
//            editor.remove(ALREADY_READ);
//            editor.putString(ALREADY_READ,gson.toJson(alreadyRead));
//            editor.commit();
//            return true;
//        }
        return false;
    }

    public  boolean removeCurrentlyReadingBooks(Books book){

        ArrayList<Books> currentlyRead=getCurrentlyReadingBooks();
        if(currentlyRead.remove(book)){
            System.out.println("Cursor has been enterd in currently read");
            SharedPreferences.Editor editor=sharedPreferences.edit();
            Gson gson=new Gson();
            editor.remove(CURRENTLY_READING_BOOKS);
            editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(currentlyRead));
            editor.commit();
            return true;
        }
        return false;
    }

    public  boolean removeFavouriteBooks(Books book){

        ArrayList<Books> favouriteBooks=getFavouriteBooks();
        if(favouriteBooks.remove(book)){
            SharedPreferences.Editor editor=sharedPreferences.edit();
            Gson gson=new Gson();
            editor.remove(FAVOURITE_BOOK);
            editor.putString(FAVOURITE_BOOK,gson.toJson(favouriteBooks));
            editor.commit();
            return true;
        }
        return false;

    }
    public  boolean removeWantToReadBooks(Books book){

        ArrayList<Books> wantToReadBook=getWantToReadBooks();
        if(wantToReadBook.remove(book)){
            SharedPreferences.Editor editor=sharedPreferences.edit();
            Gson gson=new Gson();
            editor.remove(WANT_TO_READ);
            editor.putString(WANT_TO_READ,gson.toJson(wantToReadBook));
            editor.commit();
            return true;
        }
        return false;
    }


}
