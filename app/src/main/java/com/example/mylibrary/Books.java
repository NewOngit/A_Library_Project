package com.example.mylibrary;

public class Books {
    private int id;
    private String bookName;
    private String author;
    private int pages;
    private String imageUrl;
    private String sortDesc;
    private String longDesc;
    boolean isExpanded;

    public Books(int id, String bookName, String author, int pages, String imageUrl, String sortDesc, String longDesc, boolean isExpanded) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.pages = pages;
        this.imageUrl = imageUrl;
        this.sortDesc = sortDesc;
        this.longDesc = longDesc;
        this.isExpanded = isExpanded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSortDesc() {
        return sortDesc;
    }

    public void setSortDesc(String sortDesc) {
        this.sortDesc = sortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }


}
