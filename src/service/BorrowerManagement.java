package service;

import java.time.LocalDate;
import logic.BookLogic;
import logic.BorrowerLogic;
import entity.Book;
import entity.Borrower;
import list.ListBorrower;
import list.ListBorrower.NodeBorrower;
import java.util.InputMismatchException;
import java.util.Scanner;


public class BorrowerManagement {
    private BookLogic bookLogic = new BookLogic();
    private BorrowerLogic borrowerLogic = new BorrowerLogic();

    public void menu() {
        do {
            System.out.println();
            System.out.println("-----------------------------------------");
            System.out.println("            QUẢN LÝ NGƯỜI MƯỢN          |");
            System.out.println("-------------------***-------------------");
            System.out.println("|   1. Thêm người mượn.                 |");
            System.out.println("|   2. Sửa thông tin.                   |");
            System.out.println("|   3. Xóa người mượn.                  |");
            System.out.println("|   4. Xem danh sách.                   |");
            System.out.println("|   5. Sắp xếp theo tên.                |");
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
                    sortByName();
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

            System.out.println("\n---KẾT THÚC MENU QUẢN LÝ NGƯỜI MƯỢN----\n");
        } while (true);
    }

    public void add() {
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("|              THÊM NGƯỜI MƯỢN          |");
        System.out.println("-------------------***-------------------");

        String id, name, address, email, phone;

        System.out.print("Có bao nhiêu người mượn muốn thêm mới: ");
        int borrowerNumber;
        do {
            try {
                borrowerNumber = new Scanner(System.in).nextInt();
                if (borrowerNumber > 0) {
                    break;
                }
                System.out.println("Số lượng người mượn muốn thêm phải là một số nguyên dương, vui lòng nhập lại: ");
            } catch (InputMismatchException e) {
                System.out.println("Số lượng người mượn muốn thêm phải là một số nguyên dương, vui lòng nhập lại: ");
            }
        } while (true);
        for (int i = 0; i < borrowerNumber; i++) {
            System.out.println("Nhập thông tin cho người mượn thứ : " + (i + 1));
            boolean isExisting = false;
            do {
                System.out.print("Nhập vào mã người mượn: ");
                id = new Scanner(System.in).nextLine();
                isExisting = borrowerLogic.checkById(id);

                if (isExisting) {
                    System.out.println("Đã tồn tại mã: " + id);
                } else if (!id.matches("\\d{3}")) {
                    System.out.println("Mã người mượn phải là một số nguyên có 3 chữ số.");
                    isExisting = true; // Sử dụng để tiếp tục vòng lặp do-while
                }
            } while (isExisting || id.equals(""));

            do {
                System.out.print("Nhập tên: ");
                name = new Scanner(System.in).nextLine();
            } while (name.equals(""));

            do {
                System.out.print("Nhập địa chỉ: ");
                address = new Scanner(System.in).nextLine();
            } while (address.equals(""));

            boolean validInputEmail = false;
            do {
                try {
                    System.out.print("Nhập email: ");
                    email = new Scanner(System.in).nextLine();
                    if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                        validInputEmail = true;
                    } else {
                        System.out.println("Email không hợp lệ. Vui lòng nhập lại.");
                    }
                } catch (Exception e) {
                    System.out.println("Đã xảy ra lỗi khi nhập email. Vui lòng nhập lại.");
                    email = new Scanner(System.in).nextLine();
                }
            } while (!validInputEmail);

            boolean validInputPhone = false;
            do {
                try {
                    System.out.print("Nhập số điện thoại: ");
                    phone = new Scanner(System.in).nextLine();
                    if (phone.matches("0[0-9\\s.-]{9}")) {
                        validInputPhone = true;
                    } else {
                        System.out.println("Số điện thoại không hợp lệ. Vui lòng nhập lại.");
                    }
                } catch (Exception e) {
                    System.out.println("Đã xảy ra lỗi khi nhập số điện thoại. Vui lòng nhập lại.");
                    phone = new Scanner(System.in).nextLine();
                }
            } while (!validInputPhone);

            Borrower borrower = new Borrower(id, name, address, email, phone);
            borrowerLogic.add(borrower);
        }

        System.out.println("-----------------------------------------");
    }

    public void fix() {
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("|        SỬA THÔNG TIN NGƯỜI MƯỢN       |");
        System.out.println("-------------------***-------------------");
        String id, name, address, email, phone;

        do {
            System.out.print("Nhập vào mã người mượn: ");
            id = new Scanner(System.in).nextLine();
        } while (id.isEmpty());

        if (borrowerLogic.checkById(id)) {
            System.out.println("NHẬP VÀO THÔNG TIN MỚI CHO NGƯỜI MƯỢN MANG MÃ " + id);
            System.out.print("Nhập tên người mượn: ");
            name = new Scanner(System.in).nextLine();

            System.out.print("Nhập địa chỉ: ");
            address = new Scanner(System.in).nextLine();

            boolean validInputEmail = false;
            do {
                try {
                    System.out.print("Nhập email: ");
                    email = new Scanner(System.in).nextLine();
                    if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                        validInputEmail = true;
                    } else {
                        System.out.println("Email không hợp lệ. Vui lòng nhập lại.");
                    }
                } catch (Exception e) {
                    System.out.println("Đã xảy ra lỗi khi nhập email. Vui lòng nhập lại.");
                    email = new Scanner(System.in).nextLine();
                }
            } while (!validInputEmail);

            boolean validInputPhone = false;
            do {
                try {
                    System.out.print("Nhập số điện thoại: ");
                    phone = new Scanner(System.in).nextLine();
                    if (phone.matches("0[0-9\\s.-]{9}")) {
                        validInputPhone = true;
                    } else {
                        System.out.println("Số điện thoại không hợp lệ. Vui lòng nhập lại.");
                    }
                } catch (Exception e) {
                    System.out.println("Đã xảy ra lỗi khi nhập số điện thoại. Vui lòng nhập lại.");
                    phone = new Scanner(System.in).nextLine();
                }
            } while (!validInputPhone);

            Borrower borrower = new Borrower(id, name, address, email, phone);
            borrowerLogic.fix(borrower);

        } else {
            System.out.println("Không tồn tại mã: " + id);
            fix();
        }
    }

    public void delete() {
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("|        XÓA THÔNG TIN NGƯỜI MƯỢN       |");
        System.out.println("-------------------***-------------------");
        System.out.println("Nhập vào mã của người mượn cần xóa: ");
        String id = new Scanner(System.in).nextLine();
        if (borrowerLogic.checkById(id)) {
            borrowerLogic.delete(id);
            System.out.println("Xóa thành công người mươn có mã: " + id);
        } else {
            System.out.println("Không tồn tại người mượn có mã: " + id);
            delete();
        }
    }

    public void showList() {
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("|        DANH SÁCH NGƯỜI MƯỢN           |");
        System.out.println("-------------------***-------------------");
        ListBorrower list = borrowerLogic.getList();
        NodeBorrower target = list.getHead();
        list.sortById();

        System.out.printf("%-15s | %-20S | %-25s | %-40s | %-30s |\n",
                "Mã Người mượn ",
                "Tên Người mượn",
                "Địa Chỉ",
                "Email",
                "SĐT");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        while (target != null) {
            target.getInfo().display();
            target = target.getNext();
        }

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------\n");
    }

    public void sortByName() {
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("|       DANH SÁCH NGƯỜI MƯỢN THEO TÊN   |");
        System.out.println("-------------------***-------------------");
        ListBorrower list = borrowerLogic.getList();
        list.sortByName();
        NodeBorrower target = list.getHead();

        System.out.printf("%-15s | %-20S | %-25s | %-40s | %-30s |\n",
                "Mã Người mượn ",
                "Tên người mượn",
                "Địa chỉ",
                "Email",
                "SDT");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        while (target != null) {
            target.getInfo().display();
            target = target.getNext();
        }

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------\n");
    }

    public void search() {
        do {
            System.out.println();
            System.out.println("-----------------------------------------");
            System.out.println("|          TÌM KIẾM NGƯỜI MƯỢN          |");
            System.out.println("-------------------***-------------------");
            System.out.println("|   1. Tìm theo tên.                    |");
            System.out.println("|   2. Tìm theo địa chỉ.                |");
            System.out.println("|   3. Quay lại menu quản lý.           |");
            System.out.println("|   0. Đăng xuất.                       |");
            System.out.println("-----------------------------------------");
            int functionChoice = chooseFunction5();
            switch (functionChoice) {
                case 1:
                    searchByName();
                    break;
                case 2:
                    searchByAddress();
                    break;
                case 3:
                    menu();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }

            System.out.println("\n---KẾT THÚC MENU TÌM KIẾM NGƯỜI MƯỢN---\n");
        } while (true);
    }

    public void searchByName() {
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("|           TÌM KIẾM THEO TÊN           |");
        System.out.println("-------------------***-------------------");
        System.out.print("  Nhập vào tên cần tìm kiếm: ");
        String name = new Scanner(System.in).nextLine();
        ListBorrower list = borrowerLogic.findByName(name);
        NodeBorrower target = list.getHead();

        System.out.println();
        if (target != null) {
            System.out.printf("%-15s | %-20S | %-25s | %-40s | %-30s |\n",
                    "Mã Ngươi mượn ",
                    "Tên người mượn",
                    "Địa Chỉ",
                    "Email",
                    "SĐT");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

            while (target != null) {
                target.getInfo().display();
                target = target.getNext();
            }
        } else {
            System.out.println("Không có kết quả nào phù hợp.");
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------\n");

    }
    public void searchByAddress() {
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("|           TÌM KIẾM ĐỊA CHỈ            |");
        System.out.println("-------------------***-------------------");
        System.out.print("Nhập vào địa chỉ cần tìm kiếm: ");
        String address = new Scanner(System.in).nextLine();
        ListBorrower list = borrowerLogic.findByAddress(address);
        NodeBorrower target = list.getHead();

        System.out.println();
        if (target != null) {
            System.out.printf("%-15s | %-20S | %-25s | %-40s | %-30s |\n",
                    "Mã Người mượn ",
                    "Tên người mượn",
                    "Địa Chỉ",
                    "Email",
                    "SĐT");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

            while (target != null) {
                target.getInfo().display();
                target = target.getNext();
            }
        } else {
            System.out.println("Không có kết quả nào phù hợp.");
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------\n");
    }

    public void borrowBook() {
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("|               MƯỢN SÁCH               |");
        System.out.println("-------------------***-------------------");

        String idBook, quantityBorrow;
        do {
            System.out.print("Nhập vào mã sách cần mượn: ");
            idBook = new Scanner(System.in).nextLine();
        } while (idBook.isEmpty());

        if (bookLogic.check(idBook)) {  // kiem tra sach co ton tai trong danh sach hay khong

            System.out.print("Nhập số lượng muốn mượn: ");
            quantityBorrow = new Scanner(System.in).nextLine();

            Book book = new Book(idBook, quantityBorrow);

            LocalDate today = LocalDate.now();
            LocalDate returnDate = today.plusDays(7);
            System.out.println("Hạn phải trả sách: " + returnDate);
            bookLogic.fix(book);
        } else {
            System.out.println("Không tồn tại đầu sách có mã: " + idBook);
            borrowBook();
        }
    }

    private static int chooseFunction5() {
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
}
