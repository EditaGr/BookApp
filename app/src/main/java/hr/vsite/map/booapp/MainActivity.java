package hr.vsite.map.booapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_btn;
    ArrayList<Book> bookArrayList;
    BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);

        });

        bookArrayList = getBooks();
        bookAdapter = new BookAdapter(MainActivity.this,this,bookArrayList);
        recyclerView.setAdapter(bookAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    // return all books from database
    public ArrayList<Book> getBooks(){ //dohvacanje
        ArrayList<Book> books = new ArrayList<>();
        DatabaseHelper db = new DatabaseHelper(this);
        books = db.getBooks();
        return books;
    }

    // return selected book
    public void getBook(int id){ //dohvacanje
        Book book = new Book();
        DatabaseHelper db = new DatabaseHelper(this);
        book = db.infoBook(id);
    }

}