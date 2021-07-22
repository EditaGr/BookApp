package hr.vsite.map.booapp;

public class Book {
    private String bookTitle;
    private String bookAuthor;
    private int bookPage;
    private int bookId;


    public int getBookId(){
        return bookId;
    }

    public void setBookId(int bookId){
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getBookPage() {
        return bookPage;
    }

    public void setBookPage(int bookPage) {
        this.bookPage = bookPage;
    }

    public Book(String bookTitle, String bookAuthor, int bookPage, int bookId) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPage = bookPage;
        this.bookId = bookId;
    }

    public Book(){

    }
}
