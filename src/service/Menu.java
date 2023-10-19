package service;

import entity.Account;
import logic.BookLogic;
import logic.BorrowerLogic;
import logic.LoanSlipLogic;
import java.util.InputMismatchException;
import java.util.Scanner;
import static entity.Account.readAccountsFromFile;

public class Menu {

    public static void run() {
        Account account = new Account();
        readAccountsFromFile();
        boolean loggedIn = false;
        do {
            systemMenu();
            int functionChoice = chooseFunction1();
            switch (functionChoice) {
                case 1:
                    if (account.registerUser()) {
                        continue;
                    }
                    break;
                case 2:
                    loggedIn = account.login();
                    if (loggedIn) {
                        System.out.println("Đăng nhập thành công!");
                    } else {
                        System.out.println("Đăng nhập không thành công.");
                    }
                    break;
                case 0:
                    System.out.println("Thoát.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        } while (!loggedIn);
    }

    public static void systemMenu(){
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("|      CHƯƠNG TRÌNH QUẢN LÝ THƯ VIỆN    |");
        System.out.println("-------------------***-------------------");
        System.out.println("|   1. Đăng ký tài khoản thành viên.    |");
        System.out.println("|   2. Đăng nhập.                       |");
        System.out.println("|   0. Thoát chương trình.              |");
        System.out.println("-----------------------------------------");
    }

    public static void adminMenu(){
        BorrowerManagement borrowerManagement = new BorrowerManagement();
        BookManagement bookManagement = new BookManagement();
        LoanSlipManagement loanSlipManagement = new LoanSlipManagement();
        do {
            System.out.println();
            System.out.println("----------------------------------------");
            System.out.println("|        CHỌN CHỨC NĂNG QUẢN LÝ        |");
            System.out.println("-------------------***------------------");
            System.out.println("|   1. Quản lý sách.                   |");
            System.out.println("|   2. Quản lý phiếu mượn.             |");
            System.out.println("|   3. Quản lý người mượn.             |");
            System.out.println("|   4. Tìm kiếm.                       |");
            System.out.println("|   5. Thống kê.                       |");
            System.out.println("|   0. Đăng xuất.                      |");
            System.out.println("----------------------------------------");

            int functionChoice = chooseFunction2();
            switch (functionChoice) {
                case 1:
                    bookManagement.menu();
                    break;
                case 2:
                    loanSlipManagement.menu();
                    break;
                case 3:
                    borrowerManagement.menu();
                    break;
                case 4:
                    searchMenu();
                    break;
                case 5:
                    statistical();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
            System.out.println("\n------------KẾT THÚC-----------------\n");
        } while (true);

    }

    public static void memberMenu() {
        BorrowerManagement borrowerManagement = new BorrowerManagement();
        BookManagement bookManagement = new BookManagement();

        do {
            System.out.println();
            System.out.println("----------------------------------------");
            System.out.println("|      CHỌN CHỨC NĂNG MUỐN SỬ DỤNG     |");
            System.out.println("-------------------***------------------");
            System.out.println("|   1. Xem danh sách sách.             |");
            System.out.println("|   2. Tìm kiếm sách.                  |");
            System.out.println("|   3. Mượn sách.                      |");
            System.out.println("|   0. Đăng xuất.                      |");
            System.out.println("----------------------------------------");
            int functionChoice = chooseFunction3();

            switch (functionChoice) {
                case 1:
                    bookManagement.showList();
                    break;
                case 2:
                    bookManagement.memberSearch();
                    break;
                case 3:
                    borrowerManagement.borrowBook();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
            System.out.println("\n--------------KẾT THÚC---------------\n");
        } while (true);

    }

    public static void searchMenu() {
        BorrowerManagement borrowerManagement = new BorrowerManagement();
        BookManagement bookManagement = new BookManagement();
        LoanSlipManagement loanSlipManagement = new LoanSlipManagement();

        do {
            System.out.println("-----------------------------------------");
            System.out.println("|               TÌM KIẾM                |");
            System.out.println("-------------------***-------------------");
            System.out.println("|   1. Tìm kiếm sách.                   |");
            System.out.println("|   2. Tìm kiếm phiếu mượn.             |");
            System.out.println("|   3. Tìm kiếm người mượn.             |");
            System.out.println("|   4. Quay lại menu quản lý.           |");
            System.out.println("|   0. Đăng xuất.                       |");
            System.out.println("-----------------------------------------");
            int functionChoice = chooseFunction4();

            switch (functionChoice) {
                case 1:
                    bookManagement.adminSearch();
                    break;
                case 2:
                    loanSlipManagement.search();
                    break;
                case 3:
                    borrowerManagement.search();
                case 4:
                    adminMenu();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
            System.out.println("\n--------KẾT THÚC MENU TÌM KIẾM---------\n");
        } while (true);
    }

    public static void statistical() {
        System.out.println("-----------------------------------------");
        System.out.println("|            THỐNG KÊ HỆ THỐNG          |");
        System.out.println("-------------------***-------------------");
        BookLogic bookLogic = new BookLogic();
        int totalBook = bookLogic.totalBook();
        BorrowerLogic borrowerLogic = new BorrowerLogic();
        int totalBorrower = borrowerLogic.totalBorrower();
        LoanSlipLogic loanSlipLogic = new LoanSlipLogic();
        int totalLoanSlip = loanSlipLogic.totalLoanSlip();
        System.out.println("|       Tổng người mượn: " + totalBorrower + "                    |");
        System.out.println("|       Tổng phiếu mượn: " + totalLoanSlip + "                    |");
        System.out.println("|       Tổng đầu sách khả dụng: " + totalBook + "             |");
        System.out.println("---------------------------------------------");
        System.out.println("\n----------KẾT THÚC THỐNG KÊ----------------\n");
    }

    private static int chooseFunction1() {
        System.out.print("Xin mời nhập lựa chọn: ");
        int functionChoice = 0;
        do {
            try {
                functionChoice = new Scanner(System.in).nextInt();
                if (functionChoice >= 0 && functionChoice <= 2) {
                    break;
                }
                System.out.print("Chức năng không hợp lệ, vui lòng lựa chọn (từ 0-2): ");
            } catch (InputMismatchException e) {
                System.out.print("Chức năng không hợp lệ, vui lòng lựa chọn (từ 0-2): ");
            }
        } while (true);
        return functionChoice;
    }
    private static int chooseFunction2() {
        System.out.print("Xin mời nhập lựa chọn: ");
        int functionChoice = 0;
        do {
            try {
                functionChoice = new Scanner(System.in).nextInt();
                if (functionChoice >= 0 && functionChoice <= 5) {
                    break;
                }
                System.out.print("Chức năng không hợp lệ, vui lòng lựa chọn (từ 0-5): ");
            } catch (InputMismatchException e) {
                System.out.print("Chức năng không hợp lệ, vui lòng lựa chọn (từ 0-5): ");
            }
        } while (true);
        return functionChoice;
    }
    private static int chooseFunction3() {
        System.out.print("Xin mời nhập lựa chọn: ");
        int functionChoice = 0;
        do {
            try {
                functionChoice = new Scanner(System.in).nextInt();
                if (functionChoice >= 0 && functionChoice <= 3) {
                    break;
                }
                System.out.print("Chức năng không hợp lệ, vui lòng lựa chọn (từ 0-3): ");
            } catch (InputMismatchException e) {
                System.out.print("Chức năng không hợp lệ, vui lòng lựa chọn (từ 0-3): ");
            }
        } while (true);
        return functionChoice;
    }
    private static int chooseFunction4() {
        System.out.print("Xin mời nhập lựa chọn: ");
        int functionChoice = 0;
        do {
            try {
                functionChoice = new Scanner(System.in).nextInt();
                if (functionChoice >= 0 && functionChoice <= 4) {
                    break;
                }
                System.out.print("Chức năng không hợp lệ, vui lòng lựa chọn (từ 0-4): ");
            } catch (InputMismatchException e) {
                System.out.print("Chức năng không hợp lệ, vui lòng lựa chọn (từ 0-4): ");
            }
        } while (true);
        return functionChoice;
    }
}



