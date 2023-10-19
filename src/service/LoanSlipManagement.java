package service;

import logic.LoanSlipLogic;
import entity.LoanSlip;
import list.ListBook;
import list.ListBorrower;
import list.ListLoanSlip;
import list.ListBorrower.NodeBorrower;
import list.ListLoanSlip.NodeLoanSlip;
import logic.BookLogic;
import logic.BorrowerLogic;
import entity.Book;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class LoanSlipManagement {
    LoanSlipLogic loanSlipLogic = new LoanSlipLogic();
    BookLogic bookLogic = new BookLogic();
    BorrowerLogic borrowerLogic= new BorrowerLogic();

    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public void menu() {
        do {
            System.out.println();
            System.out.println("-----------------------------------------");
            System.out.println("            QUẢN LÝ PHIẾU MƯỢN          |");
            System.out.println("-------------------***-------------------");
            System.out.println("|   1. Thêm phiếu mượn.                 |");
            System.out.println("|   2. Sửa thông tin.                   |");
            System.out.println("|   3. Xóa phiếu mượn.                  |");
            System.out.println("|   4. Xem danh sách.                   |");
            System.out.println("|   5. Sắp xếp theo số lượng mượn.      |");
            System.out.println("|   6. Tìm kiếm.                        |");
            System.out.println("|   7. Quay lại menu chính.             |");
            System.out.println("|   0. Đăng xuất.                       |");
            System.out.println("-----------------------------------------");
            int functionChoice = chooseFunction1();
            switch (functionChoice) {
                case 1:
                    add();
                    break;
                case 2:
                    fix();
                    break;
                case 3:
                    delete();
                    break;
                case 4:
                    showList();
                    break;
                case 5:
                    sortByQuantityBorrowed();
                    break;
                case 6:
                    search();
                    break;
                case 7:
                    Menu.adminMenu();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }

            System.out.println("\n----KẾT THÚC MENU QUẢN LÝ PHIẾU MƯỢN---\n");
        } while (true);
    }

    public void add() {
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("|              THÊM PHIẾU MƯỢN          |");
        System.out.println("-------------------***-------------------");
        System.out.println("MỜI NHẬP VÀO CÁC THÔNG TIN:");
        String idLoanSlip, id, idBook, borrowedDay, quantityBorrowed;

        System.out.print("Có bao nhiêu phiếu mượn muốn thêm mới: ");
        int loanSlipNumber;
        do {
            try {
                loanSlipNumber = new Scanner(System.in).nextInt();
                if (loanSlipNumber > 0) {
                    break;
                }
                System.out.println("Số lượng phiếu mượn muốn thêm phải là một số nguyên dương, vui lòng nhập lại: ");
            } catch (InputMismatchException e) {
                System.out.println("Số lượng phiếu mượn muốn thêm phải là một số nguyên dương, vui lòng nhập lại: ");
            }
        } while (true);
        for (int i = 0; i < loanSlipNumber; i++) {
            System.out.println("Nhập thông tin cho phiếu mượn thứ : " + (i + 1));
            boolean isExisting = false;

            do {
                System.out.print("Nhập vào mã phiếu mượn: ");
                idLoanSlip = new Scanner(System.in).nextLine();
                isExisting = loanSlipLogic.check(idLoanSlip);

                if (isExisting) {
                    System.out.println("Đã tồn tại mã: " + idLoanSlip);
                } else if (!idLoanSlip.matches("\\d{3}")) {
                    System.out.println("Mã phiếu mượn phải là một số nguyên có 3 chữ số.");
                    isExisting = true; // Sử dụng để tiếp tục vòng lặp do-while
                }
            } while (isExisting || idLoanSlip.equals(""));

            do {
                System.out.print("Mã người mượn: ");
                id = new Scanner(System.in).nextLine();
                if (!borrowerLogic.checkById(id)) {
                    System.out.println("Không tồn tại mã người mượn, vui lòng nhập lại.");
                }
            } while (id.isEmpty() || !borrowerLogic.checkById(id));

            do {
                System.out.print("Ngày mượn (dd/mm/yyyy): ");
                borrowedDay = new Scanner(System.in).nextLine();
               try {
                    LocalDate date = LocalDate.parse(borrowedDay, dateFormat);
                    // kiem tra tu nam 2000 den hien tai
                    if (date.getYear() < 2000 || date.isAfter(currentDate)) {
                        System.out.println("Vui lòng nhập ngày mượn trong khoảng từ năm 2000 đến hiện tại.");
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Định dạng ngày không hợp lệ. Vui lòng thử lại.");
                }
            } while (true);

            do { System.out.print("Mã sách: ");
                idBook = new Scanner(System.in).nextLine();
                if (!bookLogic.check(idBook)) {
                    System.out.println("Không tồn tại mã sách, vui lòng nhập lại.");
                }
            } while (idBook.isEmpty() || !bookLogic.check(idBook));

            do {
                System.out.print("Số lượng sách mượn: ");
                quantityBorrowed = new Scanner(System.in).nextLine();
            } while (quantityBorrowed == "");

            LoanSlip loanSlip = new LoanSlip(idLoanSlip, id, idBook, borrowedDay, quantityBorrowed);
            loanSlipLogic.add(loanSlip);
            }
        System.out.println("-----------------------------------------");
        }


    public void fix() {
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("|        SỬA THÔNG TIN PHIẾU MƯỢN       |");
        System.out.println("-------------------***-------------------");
        String idLoanSlip , id, borrowedDay, quantityBorrowed, idBook;
        boolean isExisting = false;

        do {
            System.out.print("Nhập vào mã phiếu mượn: ");
            idLoanSlip = new Scanner(System.in).nextLine();
        } while (idLoanSlip.isEmpty());

        if (loanSlipLogic.check(idLoanSlip)) {
            System.out.println("NHẬP VÀO THÔNG TIN MỚI CHO PHIẾU MƯỢN: " + idLoanSlip);
            do {
                System.out.print("Nhập vào mã người mượn: ");
                id = new Scanner(System.in).nextLine();
            } while (id.isEmpty());
            do {
                System.out.print("Ngày mượn (dd/mm/yyyy): ");
                borrowedDay = new Scanner(System.in).nextLine();
                try {
                    LocalDate date = LocalDate.parse(borrowedDay, dateFormat);
                    // kiem tra tu nam 2000 den hien tai
                    if (date.getYear() < 2000 || date.isAfter(currentDate)) {
                        System.out.println("Vui lòng nhập ngày mượn trong khoảng từ năm 2000 đến hiện tại.");
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Định dạng ngày không hợp lệ. Vui lòng thử lại.");
                }
            } while (true);

            do { System.out.print("Mã sách: ");
                idBook = new Scanner(System.in).nextLine();
                if (!bookLogic.check(idBook)) {
                    System.out.println("Không tồn tại mã sách, vui lòng nhập lại.");
                }
            } while (idBook.isEmpty() || !bookLogic.check(idBook));

            do {
                System.out.print("Nhập số lượng: ");
                quantityBorrowed = new Scanner(System.in).nextLine();
            } while (quantityBorrowed.isEmpty());

            LoanSlip loanSlip = new LoanSlip(idLoanSlip, id, idBook, borrowedDay, quantityBorrowed);
            loanSlipLogic.fix(loanSlip);
        } else {
            System.out.println("Không tồn tại mã " + idLoanSlip);
            fix();
        }

        System.out.println("\n-----------------------------------------------------------------------------\n");
    }


    public void delete() {
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("|          XÓA THÔNG TIN PHIẾU MƯỢN     |");
        System.out.println("-------------------***-------------------");
        System.out.println("Nhập vào mã của phiếu mượn cần xóa: ");
        String idLoanSlip = new Scanner(System.in).nextLine();
        if (loanSlipLogic.check(idLoanSlip)) {
            loanSlipLogic.delete(idLoanSlip);
            System.out.println("Xóa thành công phiếu mượn có mã " + idLoanSlip);
        } else {
            System.out.println("Không tồn tại phiếu mượn có mã " + idLoanSlip);
            delete();
        }

        System.out.println("\n-----------------------------------------------------------------------------\n");
    }

    public void showList() {
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("|          DANH SÁCH PHIẾU MƯỢN         |");
        System.out.println("-------------------***-------------------");
        ListLoanSlip list = loanSlipLogic.getList();
        list.sortById();
        NodeLoanSlip target = list.getHead();
        System.out.printf("%-20s | %-20s | %-20s | %-20s| %-20s\n",
                "Mã phiếu mượn ",
                "Mã người mượn ",
                "Mã sách",
                "Ngày mượn",
                "Số lượng mượn");
        System.out.println("----------------------------------------------------------------------------------------------------------");

        while (target != null) {
            target.getInfo().display();
            target = target.getNext();
        }

        System.out.println("-------------------------------------------------------------------------------------------------\n");
    }

    public void sortByQuantityBorrowed() {
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("| SẮP XẾP DANH SÁCH PHIẾU MƯỢN THEO TÊN |");
        System.out.println("-------------------***-------------------");
        ListLoanSlip list = loanSlipLogic.getList();
        list.sortByQuantityBorrowed();
        NodeLoanSlip target = list.getHead();
        System.out.printf("%-20s | %-20s | %-20s | %-20s| %-20s\n",
                "Mã phiếu mượn ",
                "Mã người mượn ",
                "Mã sách",
                "Ngày mượn",
                "Số lượng mượn");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        while (target != null) {
            target.getInfo().display();
            target = target.getNext();
        }

        System.out.println("-----------------------------------------------------------------------------------------------------------\n");
    }

    public void search() {
        do {
            System.out.println();
            System.out.println("-----------------------------------------");
            System.out.println("|            TÌM KIẾM PHIẾU MƯỢN        |");
            System.out.println("-------------------***-------------------");
            System.out.println("|   1. Tìm theo ngày mượn.              |");
            System.out.println("|   2. Tìm theo mã người mượn.          |");
            System.out.println("|   3. Quay lại menu quản lý.           |");
            System.out.println("|   0. Đăng xuất.                       |");
            System.out.println("-----------------------------------------");
            int functionChoice = chooseFunction2();
            switch (functionChoice) {
                case 1:
                    searchByBorrowedDay();
                    break;
                case 2:
                    searchById();
                    break;
                case 3:
                    menu();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }

            System.out.println("\n---KẾT THÚC MENU TÌM KIẾM PHIẾU MƯỢN---\n");
        } while (true);
    }


    public void searchByBorrowedDay() {
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("|      TÌM KIẾM THEO NGÀY MƯỢN          |");
        System.out.println("-------------------***-------------------");
        System.out.print("  Nhập vào ngày mượn cần tìm kiếm: ");
        String borrowedDay = new Scanner(System.in).nextLine();
        ListLoanSlip list = loanSlipLogic.findByBorrowedDay(borrowedDay);
        NodeLoanSlip target = list.getHead();

        System.out.println();
        if (target != null) {
            System.out.printf("%-20s | %-20s | %-20s | %-20s| %-20s\n",
                    "Mã phiếu mượn ",
                    "Mã người mượn ",
                    "Mã sách",
                    "Ngày mượn",
                    "Số lượng mượn");
            System.out.println("----------------------------------------------------------------------------------------");
            while (target != null) {
                target.getInfo().display();
                target = target.getNext();
            }
        } else {
            System.out.println("Không có kết quả nào phù hợp.");
        }
        System.out.println("-------------------------------------------------------------------------------------------\n");

    }
    public void searchById() {
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("|      TÌM KIẾM THEO MÃ NGƯỜI MƯỢN      |");
        System.out.println("-------------------***-------------------");
        System.out.print("Nhập vào mã cần tìm kiếm: ");
        String id = new Scanner(System.in).nextLine();
        ListLoanSlip list = loanSlipLogic.findById(id);
        NodeLoanSlip target = list.getHead();

        System.out.println();
        if (target != null) {
            System.out.printf("%-20s | %-20s | %-20s | %-20s| %-20s\n",
                    "Mã phiếu mượn ",
                    "Mã người mượn ",
                    "Mã sách",
                    "Ngày mượn",
                    "Số lượng mượn");
            System.out.println("------------------------------------------------------------------------------------------");
            while (target != null) {
                target.getInfo().display();
                target = target.getNext();
            }
        }
        else {
            System.out.println("Không có kết quả nào phù hợp.");
        }
        System.out.println("----------------------------------------------------------------------------------------------\n");
    }
    private static int chooseFunction1() {
        System.out.print("Xin mời nhập lựa chọn: ");
        int functionChoice = 0;
        do {
            try {
                functionChoice = new Scanner(System.in).nextInt();
                if (functionChoice >= 0 && functionChoice <= 7) {
                    break;
                }
                System.out.print("Chức năng không hợp lệ, vui lòng lựa chọn (từ 0-7): ");
            } catch (InputMismatchException e) {
                System.out.print("Chức năng không hợp lệ, vui lòng lựa chọn (từ 0-7): ");
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


}
