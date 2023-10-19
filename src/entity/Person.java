package entity;

import java.util.Scanner;

public class Person implements Displayable {
    private String id;
    private String name;
    private String email;
    private String address;
    private String phone;

    public String getAddress() {
        return address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Person() {
        this.id = "";
        this.name = "";
        this.address = "";
        this.email = "";
        this.phone = "";
    }

    public Person(String id, String name,String address, String email, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public void display() {
        System.out.printf("%-15s | %-20S | %-25s | %-40s | %-30s |\n",
                getId(),
                getName(),
                getAddress(),
                getEmail(),
                getPhone());
    }

    @Override
    public String toString() {
        return getId() +","
                +getName()+ ","
                + getAddress()+ ","
                + getEmail()+ ","
                + getPhone();
    }

    public void update(Borrower loanSlip) {
        this.id = loanSlip.getId();
        this.name = loanSlip.getName();
        this.address = loanSlip.getAddress();
        this.email = loanSlip.getEmail();
        this.phone = loanSlip.getPhone();

    }

}



