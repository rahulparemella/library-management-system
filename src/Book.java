import java.sql.*;
public class Book {
    private String bookName;
    private String bookAuthor;
    private String bookPublisher;
    private String bookCategory;

    public Book(String bookName, String bookAuthor, String bookPublisher, String bookCategory) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPublisher = bookPublisher;
        this.bookCategory = bookCategory;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }


    public static void insertBookPostgreSQL(String name, String author, String category, String publisher) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/Library";
        String user = "postgres";
        String password = "hemanth@V444";
        try {
            Connection conn= DriverManager.getConnection(jdbcUrl, user, password);
            Statement stmt = conn.createStatement();
            stmt.execute("insert into books(book_name, book_author, book_category, book_publisher) values(?, ?, ?, ?)");
            System.out.println("Book added successfully");
            conn.close();
        } 
        catch (Exception ex) 
        {
            System.out.println(ex);
        }
    }
    public String toString() {
        return "Book Name: " + bookName + "\nBook Author: " + bookAuthor + "\nBook Category: " + bookCategory + "\nBook Publisher: " + bookPublisher;
    }
}
