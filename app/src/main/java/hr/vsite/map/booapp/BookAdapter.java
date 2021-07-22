package hr.vsite.map.booapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{

    Context context;
    ArrayList<Book> booksArray;
    private Activity activity;


    BookAdapter(Activity activity, Context context, ArrayList<Book> booksArrayList){
           this.activity = activity;
           this.booksArray = booksArrayList;
           this.context = context;
    }


    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull BookAdapter.ViewHolder holder, int position) {

        Book book = booksArray.get(position);
        holder.bookTitle.setText(String.valueOf(book.getBookTitle()));
        holder.bookAuthor.setText(String.valueOf(book.getBookAuthor()));
        holder.bookID.setText(String.valueOf(book.getBookId()));
        holder.bookPages.setText(String.valueOf(book.getBookId()));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateBook.class);
                intent.putExtra("id", String.valueOf(book.getBookId()));
                intent.putExtra("title", String.valueOf(book.getBookTitle()));
                intent.putExtra("author", String.valueOf(book.getBookAuthor()));
                intent.putExtra("pages", String.valueOf(book.getBookPage()));
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return booksArray.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mainLayout;
        TextView bookTitle, bookAuthor,bookPages , bookID;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.tv_bookTitle);
            bookAuthor = itemView.findViewById(R.id.tv_bookAuthor);
            bookID = itemView.findViewById(R.id.tv_bookId);
            bookPages = itemView.findViewById(R.id.tv_bookPages);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
