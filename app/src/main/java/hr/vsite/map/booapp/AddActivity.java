package hr.vsite.map.booapp;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity { //intent za dodavanje knjige

    EditText title_input, author_input, pages_input;
    Button add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        author_input = findViewById(R.id.author_input);
        pages_input = findViewById(R.id.pages_input);
        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(v -> {
                DatabaseHelper myDB = new DatabaseHelper(AddActivity.this);
                myDB.addBook(title_input.getText().toString(),
                        author_input.getText().toString(),
                        Integer.parseInt(pages_input.getText().toString()), AddActivity.this);
        });
    }
}