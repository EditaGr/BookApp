package hr.vsite.map.booapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public Context context;
    public static final String DATABES_NAME= "BookApp.db"; //naziv
    public static final int DATABES_VERSION= 1; //verzija

    public static final String TABLE_NAME="books"; //naziv tablice i stupaca unutar tablice
    public static final String COLUMN_ID="id";
    public static final String COLUMN_TITLE= "book_title";
    public static final String COLUMN_AUTHOR= "book_author";
    public static final String COLUMN_PAGES= "book_pages";


    public DatabaseHelper(@Nullable Context context) {

        super(context, DATABES_NAME, null, DATABES_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) { // metoda za kreiranje tablice
        String query= "CREATE TABLE " + TABLE_NAME +
                        "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_AUTHOR + " TEXT, " +
                        COLUMN_PAGES + " INTEGER);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //ako postoji brisi tablicu
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    void addBook(String title, String author, int pages, Context context){ //metoda za INSERT naredbu
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_PAGES, pages);
         long result = db.insert(TABLE_NAME, null , cv);

         if(result == -1){
             Toast.makeText(context, "The content values are not inserted",
                     Toast.LENGTH_SHORT).show();
         }else{
             Toast.makeText(context, "The content values are inserted",
                     Toast.LENGTH_SHORT).show();

         }
    }

    public ArrayList<Book> getBooks(){  //metoda za citanje

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor booksCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<Book> booksArray = new ArrayList<>();

        if (booksCursor.moveToFirst()) {
            do {
                booksArray.add(new Book(booksCursor.getString(1),
                        booksCursor.getString(2),
                        booksCursor.getInt(3),
                        booksCursor.getInt(0)));
            } while (booksCursor.moveToNext());
        }
        booksCursor.close();
        return booksArray;

    }

    public Book infoBook(int id){  //metoda za citanje

        SQLiteDatabase db = this.getReadableDatabase();

        String stringID = String.valueOf(id);
        Cursor booksCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE ID = " + id, null);

        booksCursor.moveToFirst();


        Book book = new Book(booksCursor.getString(1), booksCursor.getString(2), booksCursor.getInt(3), booksCursor.getInt(0));

        booksCursor.close();
        return book;

    }


    public void deleteOneRow(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id=?", new String[]{id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateBook(String row_id, String title, String author, String pages){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_PAGES, pages);

        long result = db.update(TABLE_NAME, cv, "id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }


}
