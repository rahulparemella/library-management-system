import java.sql.*;
import java.util.Scanner;

public class main {

    public static void main(String[] args) throws SQLException {

        boolean flag = true;
        while (flag) {
            System.out.println("------------Welcome to Library Management System------------");
            System.out.println("Please select an option:");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();
            sc.nextLine();
            if (option == 1) {
                System.out.println("Please enter your username:");
                String username = sc.nextLine();

                System.out.println("Please enter your password:");
                String password = sc.nextLine();

                Users.loginControl(username, password);

                if (Users.loginControl(username, password) == 0) {
                    System.out.println("Login Successful");
                    displayOptions();
                }
                else {
                    System.out.println("Login Failed");
                }
            }
            else if (option == 2) {
                System.out.println("Please enter your first name:");
                String firstName = sc.nextLine();

                System.out.println("Please enter your last name:");
                String lastName = sc.nextLine();

                System.out.println("Please enter a username:");
                String username = sc.nextLine();

                while (Users.usernameControl(username) == 0) {
                    System.out.println("Username already exists. Please enter a new username:");
                    username = sc.nextLine();
                }

                System.out.println("Please enter a password:");
                String password = sc.nextLine();

                System.out.println("Please enter your address:");
                String address = sc.nextLine();

                System.out.println("Please enter your phone number:");
                String phoneNumber = sc.nextLine();

                Users.registerSQL(firstName, lastName, username, password, address, phoneNumber);
            }
            else if (option == 3) {
                flag = false;
            }
        }

    }

    public static void displayOptions(){
        boolean choose = true;
        while (choose) {
            System.out.println("Enter your choice");
            System.out.println("1. Add Book");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Search Book");
            System.out.println("5. List Available Books");
            System.out.println("6. Exit");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice) {
                case 1: //Add book
                    System.out.println("Enter Book Name");
                    String bookName = sc.nextLine();
                    System.out.println("Enter Book Author");
                    String bookAuthor = sc.nextLine();
                    System.out.println("Enter Book Publisher");
                    String bookPublisher = sc.nextLine();
                    System.out.println("Enter Book Category");
                    String bookCategory = sc.nextLine();
                    Book book = new Book(bookName, bookAuthor, bookPublisher, bookCategory);
                    book.insertBookPostgreSQL(book.getBookName(), book.getBookAuthor(), book.getBookCategory(), book.getBookPublisher());
                    break;
                case 2: //Issue book
                    System.out.println("Enter Book Name");
                    String bookname = sc.nextLine();
                    while (IssueBook.issuedBookControl(bookname) == 0) {
                        System.out.println("Book is not available");
                        System.out.println("Enter Book Name");
                        bookname = sc.nextLine();
                    }
                    System.out.println("Enter Author Name");
                    String authorname = sc.nextLine();
                    System.out.println("Enter Username");
                    String username = sc.nextLine();
                    System.out.println("Enter Issue Date");
                    String issueDate = sc.nextLine();

                    IssueBook issueBook = new IssueBook(bookname, authorname, username, issueDate);
                    issueBook.insertIssuedBook(issueBook.getBookName(), issueBook.getAuthorName(),
                            issueBook.getUserName(), issueBook.getIssueDate());
                    IssueBook.deleteFromBooks(bookname);
                    break;
                case 3: //Return book
                    System.out.println("Enter Book Name");
                    String returnbookname = sc.nextLine();
                    System.out.println("Enter Book Author");
                    String returnbookauthor = sc.nextLine();
                    System.out.println("Enter Book Publisher");
                    String returnbookpublisher = sc.nextLine();
                    System.out.println("Enter Book Category");
                    String returnbookcategory = sc.nextLine();
                    ReturnBook.returnBook(returnbookname, returnbookauthor, returnbookpublisher, returnbookcategory);
                    ReturnBook.deleteFromIssuedBooks(returnbookname);
                    break;
                case 4: //Search book
                    System.out.println("Enter Book ID");
                    int bookID3 = sc.nextInt();
                    searchBook(bookID3);
                    break;
                case 5: //List books
                    listBooks();
                    break;
                case 6: //Exit
                    System.out.println("Thank you for using Library Management System");
                    choose = false;
                    break;
                default:
                    System.out.println("----------Invalid Choice----------");
            }
        }
    }
    public static void listBooks(){
        String jdbcUrl = "jdbc:postgresql://localhost:5432/Library";
        String user = "postgres";
        String password = "hemanth@V444";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery( "SELECT * FROM books;" );
            while ( results.next() ) {
                int id = results.getInt("id");
                String book_name = results.getString("book_name");
                String book_author  = results.getString("book_author");
                String book_publisher = results.getString("book_publisher");
                String book_category = results.getString("book_category");
                System.out.println( "ID = " + id );
                System.out.println( "BOOK NAME = " + book_name );
                System.out.println( "BOOK AUTHOR = " + book_author );
                System.out.println( "BOOK PUBLISHER = " + book_publisher );
                System.out.println( "BOOK CATEGORY = " + book_category );
                System.out.println();
            }
            connection.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    public static void searchBook(int bookID){
        String jdbcUrl = "jdbc:postgresql://localhost:5432/Library";
        String user = "postgres";
        String password = "hemanth@V444";
        try {
            Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM books WHERE id = " + bookID + ";" );
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String book_name = rs.getString("book_name");
                String book_author  = rs.getString("book_author");
                String book_publisher = rs.getString("book_publisher");
                String book_category = rs.getString("book_category");
                System.out.println( "ID = " + id );
                System.out.println( "BOOK NAME = " + book_name );
                System.out.println( "BOOK AUTHOR = " + book_author );
                System.out.println( "BOOK PUBLISHER = " + book_publisher );
                System.out.println( "BOOK CATEGORY = " + book_category );
                System.out.println();
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
