import java.sql.*;

public class ReturnBook {
    private String bookName;
    private String authorName;
    private String userName;
    private String returnDate;

    public ReturnBook(String bookName, String authorName,String userName, String returnDate) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.userName = userName;
        this.returnDate = returnDate;
    }

    public static void deleteFromIssuedBooks(String bookName) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/Library";
        String user = "postgres";
        String password = "hemanth@V444";
        try{
            Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
            Statement stmt=conn.createStatement();
            stmt.execute("DELETE FROM issuedbooks where bookname=?");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void returnBook(String name, String author, String category, String publisher) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/Library";
        String user = "postgres";
        String password = "hemanth@V444";
        try {
            Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
            Statement stmt=conn.createStatement();
            stmt.execute("INSERT INTO books (book_name, book_author, book_publisher, book_category)VALUES (?, ?, ?, ?)");
            System.out.println("Book returned successfully");
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }


    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
