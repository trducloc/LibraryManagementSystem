package entity;

public class LoanSlip implements Displayable {

    private String idLoanSlip;
    private String id;
    private String idBook;
    private String borrowedDay;
    private String quantityBorrowed;

    public LoanSlip() {
        this.idLoanSlip = "";
        this.id= "";
        this.idBook = "";
        this.borrowedDay = "";
        this.quantityBorrowed ="";
    }

    public LoanSlip(String idLoanSlip,String id, String idBook, String borrowedDay, String quantityBorrowed) {
        this.idLoanSlip = idLoanSlip;
        this.id = id;
        this.idBook = idBook;
        this.borrowedDay = borrowedDay;
        this.quantityBorrowed = quantityBorrowed;
    }

    public String getIdLoanSlip() {
        return idLoanSlip;
    }


    public String getId() {
        return id;
    }


    public String getIdBook() {
        return idBook;
    }


    public String getBorrowedDay() {
        return borrowedDay;
    }


    public String getQuantityBorrowed() {
        return quantityBorrowed;
    }


    public void display() {
        System.out.printf("%-20s | %-20s | %-20s | %-20s| %-20s\n",
                getIdLoanSlip(),
                getId(),
                getIdBook(),
                getBorrowedDay(),
                getQuantityBorrowed());
    }

    @Override
    public String toString() {
        return  getIdLoanSlip()+ "," +
                getId()+ "," +
                getIdBook()+ "," +
                getBorrowedDay()+","+
                getQuantityBorrowed();
    }

    public void update(LoanSlip loanSlip) {
        this.idLoanSlip =loanSlip.getIdLoanSlip();
        this.id = loanSlip.getId();
        this.idBook = loanSlip.getIdBook();
        this.borrowedDay = loanSlip.getBorrowedDay();
        this.quantityBorrowed = loanSlip.getQuantityBorrowed();
    }
}
