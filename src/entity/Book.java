package entity;



public class Book implements Displayable {

    private String idBook;
    private String bookName;
    private String author;
    private String categoryBook;
    private String status;
    private String quantity;
    private String quantityBorrow;
    public Book(){
        this.idBook ="";
        this.bookName = "";
        this.author = "";
        this.categoryBook = "";
        this.status = "";
        this.quantity = "";
    }
    public Book(Book book) {
        this.idBook = book.getIdBook();
        this.bookName = book.getBookName();
        this.author = book.getAuthor();
        this.categoryBook = book.getCategoryBook();
        this.status = book.getStatus();
        this.quantity = book.getQuantity();

    }


    public Book(String idBook, String bookName, String author, String categoryBook, String status, String quantity) {
        this.idBook = idBook;
        this.bookName = bookName;
        this.author = author;
        this.categoryBook = categoryBook;
        this.status = status;
        this.quantity = quantity;
    }

    public Book(String idBook, String quantityBorrow) {
        this.idBook = idBook;
        this.quantityBorrow = quantityBorrow;
    }


    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
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

    public String getCategoryBook() {
        return categoryBook;
    }



    public String getStatus() {
        return status;
    }


    public String getQuantity() {
        return quantity;
    }


    @Override
    public String toString() {
        return getIdBook() +","
                +getBookName()+ ","
                + getAuthor()+ ","
                + getCategoryBook()+ ","
                + getStatus() +","
                + getQuantity();
    }

    public void display() {
        System.out.printf("%-15s | %-25s | %-30s | %-15s | %-15s | %-20s |\n",
                getIdBook(),
                getBookName(),
                getAuthor(),
                getCategoryBook(),
                getStatus(),
                getQuantity());
    }

    public void updateBook(Book Book) {
        this.idBook = Book.getIdBook();
        this.bookName = Book.getBookName();
        this.author = Book.getAuthor();
        this.categoryBook = Book.getCategoryBook();
        this.status = Book.getStatus();
        this.quantity = Book.getQuantity();
    }

}
