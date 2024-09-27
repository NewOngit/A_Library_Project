package com.example.mylibrary;

import static androidx.core.content.ContextCompat.startActivity;

import static com.example.mylibrary.BookActivity.BOOK_ID_KEY;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AllBooksRecyclerView extends  RecyclerView.Adapter<AllBooksRecyclerView.ViewH> {
   ArrayList<Books> booksArrayList;
   Context context;
   private int adapterPosition;
   private String parentActivity;

    public AllBooksRecyclerView(Context context,String parentActivity) {
        this.context = context;
        this.parentActivity=parentActivity;
    }

    @NonNull
    @Override
    public ViewH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_books,parent,false);
        ViewH holder=new ViewH(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewH holder, int position) {

        //adapterPosition=position;

        holder.bookName.setText(holder.bookName.getText().toString()+" : "+booksArrayList.get(holder.getAdapterPosition()).getBookName());
        holder.author.setText(holder.author.getText().toString()+" : "+booksArrayList.get(holder.getAdapterPosition()).getAuthor() );
        holder.bookId.setText(holder.bookId.getText().toString()+" : "+booksArrayList.get(holder.getAdapterPosition()).getId());
        holder.desc.setText(holder.desc.getText().toString()+" : "+booksArrayList.get(holder.getAdapterPosition()).getSortDesc());
        Glide.with(context)
                .asBitmap()
                .load(booksArrayList.get(holder.getAdapterPosition()).getImageUrl())
                .into(holder.image);


        holder.downArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!booksArrayList.get(holder.getAdapterPosition()).isExpanded()){
        setDownArrow(holder);
                }
            }
        });

        holder.upArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(booksArrayList.get(holder.getAdapterPosition()).isExpanded){
                setUpArrow(holder);
                }
            }
        });


        holder.rly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, booksArrayList.get(holder.getAdapterPosition()).getBookName()+" Selected", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,BookActivity.class);
                for(Books book: booksArrayList)
                    book.setExpanded(false);
                intent.putExtra(BOOK_ID_KEY,booksArrayList.get(holder.getAdapterPosition()).getId());
                context.startActivity(intent);
            }
        });

        if(parentActivity!="AllBooksActivity")
holder.delete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

    }
});

    }
    public  void setBooksArrayList(ArrayList<Books> booksArrayList){
        this.booksArrayList=booksArrayList;
    }
    @Override
    public int getItemCount() {
        return booksArrayList.size();
    }


    public class ViewH extends RecyclerView.ViewHolder{
        private RelativeLayout rly;
        private ImageView image;
        private TextView bookName;
        private RelativeLayout enableRly;
        private TextView bookId, author, desc, delete;
        private ImageView upArrow, downArrow;
        public ViewH(@NonNull View itemView) {
            super(itemView);
            rly=itemView.findViewById(R.id.rly);
            image=itemView.findViewById(R.id.image);
            bookName=itemView.findViewById(R.id.bookName);
            enableRly=itemView.findViewById(R.id.enableRly);
            bookId=itemView.findViewById(R.id.bookId);
            desc=itemView.findViewById(R.id.desc);
            author=itemView.findViewById(R.id.author);
            upArrow=itemView.findViewById(R.id.upArrow);
            downArrow=itemView.findViewById(R.id.downArrow);
            delete=itemView.findViewById(R.id.delete);
        }
    }
    public void setDownArrow(ViewH holder){
        booksArrayList.get(holder.getAdapterPosition()).setExpanded(true);
        holder.downArrow.setVisibility(View.GONE);
        holder.enableRly.setVisibility(View.VISIBLE);
        if(parentActivity.equals("AllBooksActivity"))
            holder.delete.setVisibility(View.GONE);
        else if(parentActivity.equals("CurrentlyReadingBooksActivity")){
            holder.delete.setVisibility(View.VISIBLE);
            holder.delete.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    builder.setMessage("Are you sure want to delete "+booksArrayList.get(holder.getAdapterPosition()).getBookName());
                    builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (Utils.getInstance(context).removeCurrentlyReadingBooks(booksArrayList.get(holder.getAdapterPosition()))) {
                                Toast.makeText(context, "This Book has been removed", Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                            } else
                                Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
        builder.create().show();
                }
            });
        }
        else if(parentActivity.equals("AddToFavouriteActivity")){
            holder.delete.setVisibility(View.VISIBLE);
            holder.delete.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    builder.setMessage("Are you sure want to delete "+booksArrayList.get(holder.getAdapterPosition()).getBookName());
                    builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            if (Utils.getInstance(context).removeFavouriteBooks(booksArrayList.get(holder.getAdapterPosition()))) {
// :::::                         for Check Purpose
//                            if(booksArrayList.contains(booksArrayList.get(holder.getAdapterPosition()))) {
                                Toast.makeText(context, "This Book has been removed", Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();

                            } else {
                                Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                                Log.d("shared_", "Entered");
                            }
                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.create().show();
                }
            });
        }
        else if(parentActivity.equals("WantToReadBooksActivity")){
            holder.delete.setVisibility(View.VISIBLE);
            holder.delete.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    builder.setMessage("Are you sure want to delete "+booksArrayList.get(holder.getAdapterPosition()).getBookName());
                    builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (Utils.getInstance(context).removeWantToReadBooks(booksArrayList.get(holder.getAdapterPosition()))) {
                                Toast.makeText(context, "This Book has been removed", Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                            } else {
                                Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                                System.out.println("Entered in termi");
                            }
                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.create().show();
                }
            });
        }
        else if(parentActivity.equals("AlreadyReadBooksActivity")){
            holder.delete.setVisibility(View.VISIBLE);
            holder.delete.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    builder.setMessage("Are you sure want to delete "+booksArrayList.get(holder.getAdapterPosition()).getBookName());
                    builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (Utils.getInstance(context).removeAlreadyReadBooks(booksArrayList.get(holder.getAdapterPosition()))) {
                                Toast.makeText(context, "This Book has been removed", Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                            } else
                                Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.create().show();
                }
            });
        }


    }
    public void setUpArrow(ViewH holder){
        booksArrayList.get(holder.getAdapterPosition()).setExpanded(false);
        holder.downArrow.setVisibility(View.VISIBLE);
        holder.enableRly.setVisibility(View.GONE);
    }
}
