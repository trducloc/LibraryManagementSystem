package service;

import logic.BookLogic;
import entity.Book;
import list.ListBook;
import list.ListBook.NodeBook;
import java.util.InputMismatchException;
import java.util.Scanner;

import static service.Menu.memberMenu;


public class BookManagement {
    private BookLogic bookLogic = new BookLogic();

    public void menu() {
        do {
            System.out.println();
            System.out.println("----------------------------------------");
            System.out.println("|             QUẢN LÝ SÁCH              |");
            System.out.println("-------------------***-------------------");
            System.out.println("|   1. Thêm sách.                       |");
            System.out.println("|   2. Sửa thông tin sách.              |");
            System.out.println("|   3. Xóa sách.                        |");
            System.out.println("|   4. Xem danh sách.                   |");
            System.out.println("|   5. Tìm kiếm sách.                   |");
            System.out.println("|   6. Sắp xếp danh sách theo tên sách. |");
            System.out.println("|   7. Quay lại menu quản lý chính.     |");
            System.out.println("|   0. Thoát chương trình.              |");
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
                    adminSearch();
                    break;
                case 6:
                    sortByName();
                    break;
                case 7:
                    Menu.adminMenu();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
            System.out.println("\n---------------------KẾT THÚC MENU QUẢN LÝ SÁCH ------------------\n");
        } while (true);
    }

    public void add() {
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("|             THÊM SÁCH MỚI             |");
        System.out.println("-------------------***-------------------");
        String idBook, bookName, author, categoryBook, quantity, status;

        System.out.print("Có bao nhiêu sách muốn thêm mới: ");
        int bookNumber;
        do {
            try {
                bookNumber = new Scanner(System.in).nextInt();
                if (bookNumber > 0) {
                    break;
                }
                System.out.println("Số lượng sách muốn thêm phải là một số nguyên dương, vui lòng nhập lại: ");
            } catch (InputMismatchException e) {
                System.out.println("Số lượng sách muốn thêm phải là một số nguyên dương, vui lòng nhập lại: ");
            }
        } while (true);
        for (int i = 0; i < bookNumber; i++) {
            System.out.println("Nhập thông tin cho sách thứ : " + (i + 1));

            boolean isExisting = false;
            do {
                System.out.print("Nhập vào mã sách: ");
                idBook = new Scanner(System.in).nextLine();
                isExisting = bookLogic.check(idBook);

                if (isExisting) {
                    System.out.println("Đã tồn tại mã: " + idBook);
                } else if (!idBook.matches("\\d{3}")) {
                    System.out.println("Mã sách phải là một số nguyên có 3 chữ số.");
                    isExisting = true; // Sử dụng để tiếp tục vòng lặp do-while
                }
            } while (isExisting || idBook.equals(""));

            do {
                System.out.print("Nhập tên sách: ");
                bookName = new Scanner(System.in).nextLine();
            } while (bookName.equals(""));
            do {
                System.out.print("Nhập tên tác giả: ");
                author = new Scanner(System.in).nextLine();
            } while (author.equals(""));

            do {
                System.out.print("Nhập thể loại: ");
                categoryBook = new Scanner(System.in).nextLine();
            } while (categoryBook.equals(""));

            do {
                try {
                    System.out.print("Nhập số lượng: ");
                    quantity = new Scanner(System.in).nextLine();
                    int parsedQuantity = Integer.parseInt(quantity);
                    if (parsedQuantity <= 0) {
                        System.out.println("Số lượng phải là một số nguyên dương. Vui lòng nhập lại.");
                        quantity = "";
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Số lượng phải là một số nguyên dương. Vui lòng nhập lại.");
                    quantity = "";
                }
            } while (quantity.equals(""));


            do {
                System.out.print("Nhập trạng thái: ");
                status = new Scanner(System.in).nextLine();
            } while (status.equals(""));

            Book book = new Book(idBook, bookName, author, categoryBook, quantity, status);
            bookLogic.add(book);
        }

        System.out.println("\n-----------------------------------------------------------------------------\n");
    }


    public void fix() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("|           SỬA THÔNG TIN SÁCH          |");
        System.out.println("-------------------***-------------------");

        String idBook, bookName, categoryBook, author, quantity, status;

        do {
            System.out.print("Nhập vào mã sách cần sửa: ");
            idBook = new Scanner(System.in).nextLine();
        } while (idBook.isEmpty());

        if (bookLogic.check(idBook)) {  // Kiểm tra sách có tồn tại trong danh sách hay không
            System.out.println("NHẬP VÀO THÔNG TIN MỚI CHO SÁCH MANG MÃ " + idBook);
            System.out.print("Nhập tên sách: ");
            bookName = scanner.nextLine();

            System.out.print("Nhập thể loại: ");
            categoryBook = scanner.nextLine();

            System.out.print("Nhập tên tác giả: ");
            author = scanner.nextLine();

            System.out.print("Nhập số lượng: ");
            quantity = scanner.nextLine();

            System.out.print("Nhập trạng thái: ");
            status = scanner.nextLine();
            Book book = new Book(idBook, bookName, categoryBook, author, quantity, status);
            bookLogic.fix(book);
        } else {
            System.out.println("Không tồn tại đầu sách có mã: " + idBook);
            fix();
        }
    }

    public void delete() {
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("|           XÓA THÔNG TIN SÁCH          |");
        System.out.println("-------------------***-------------------");
        System.out.print("Nhập vào mã của sách muốn xóa: ");
        String idBook = new Scanner(System.in).nextLine();
        if (bookLogic.check(idBook)) {
            bookLogic.delete(idBook);
            System.out.println("Xóa thành công sách có mã: " + idBook);
        } else {
            System.out.println("Không tồn tại sách có mã: " + idBook);
            delete();
        }
    }

    public void showList() {
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("|             DANH SÁCH SÁCH            |");
        System.out.println("-------------------***-------------------");
        ListBook list = bookLogic.getList();
        NodeBook target = list.getHead();
        System.out.printf("%-15s | %-25s | %-30s | %-15s | %-15s | %-20s |\n",
                "Mã Sách ",
                "Tên Sách",
                "Tác Giả",
                "Thể Loại",
                "Số Lượng",
                "Trạng thái");
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
        System.out.println("|     SẮP XẾP DANH SÁCH SÁCH THEO TÊN   |");
        System.out.println("-------------------***-------------------");
        ListBook list = bookLogic.getList();
        list.sortByName();
        NodeBook target = list.getHead();
        System.out.printf("%-15s | %-25s | %-30s | %-15s | %-15s | %-20s |\n",
                "Mã Đầu Sách ",
                "Tên Sách",
                "Thể Loại",
                "Tác Giả",
                "Số Lượng",
                "Trạng thái");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        while (target != null) {
            target.getInfo().display();
            target = target.getNext();
        }

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------\n");
    }

    public void adminSearch() {
        do {
            System.out.println();
            System.out.println("-----------------------------------------");
            System.out.println("|              TÌM KIẾM SÁCH            |");
            System.out.println("-------------------***-------------------");
            System.out.println("|   1. Tìm theo mã sách.                |");
            System.out.println("|   2. Tìm theo tên sách.               |");
            System.out.println("|   3. Quay lại menu quản lý.           |");
            System.out.println("|   0. Thoát chương trình.              |");
            System.out.println("-----------------------------------------");
            int functionChoice = chooseFunction2();
            switch (functionChoice) {
                case 1:
                    searchById();
                    break;
                case 2:
                    searchByName();
                    break;
                case 3:
                    menu();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }

            System.out.println("\n--------------------KẾT THÚC MENU TÌM KIẾM SÁCH-------------------\n");
        } while (true);
    }

    public void memberSearch() {
        do {
            System.out.println();
            System.out.println("-----------------------------------------");
            System.out.println("|              TÌM KIẾM SÁCH            |");
            System.out.println("-------------------***-------------------");
            System.out.println("|   1. Tìm theo mã sách.                |");
            System.out.println("|   2. Tìm theo tên sách.               |");
            System.out.println("|   3. Quay lại menu chức năng.         |");
            System.out.println("|   0. Thoát chương trình.              |");
            System.out.println("-----------------------------------------");
            int functionChoice = chooseFunction2();
            switch (functionChoice) {
                case 1:
                    searchById();
                    break;
                case 2:
                    searchByName();
                    break;
                case 3:
                    memberMenu();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }

            System.out.println("\n--------------------KẾT THÚC MENU TÌM KIẾM SÁCH-------------------\n");
        } while (true);
    }

    public void searchById() {
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("|         TÌM KIẾM THEO MÃ SÁCH         |");
        System.out.println("-------------------***-------------------");
        System.out.print("Nhập vào mã sách cần tìm kiếm: ");
        String idBook = new Scanner(System.in).nextLine();
        NodeBook target = bookLogic.findById(idBook);
        System.out.println();
        if (target != null) {
            System.out.printf("%-15s | %-25s | %-30s | %-15s | %-15s | %-20s |\n",
                    "Mã sách ",
                    "Tên Sách",
                    "Thể Loại",
                    "Tác Giả",
                    "Số Lượng",
                    "Trạng thái");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
            target.getInfo().display();
        } else {
            System.out.println("Không có kết quả nào phù hợp.");
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------\n");
    }

    public void searchByName() {
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("|         TÌM KIẾM THEO TÊN SÁCH         |");
        System.out.println("-------------------***-------------------");
        System.out.print("Nhập vào tên sách cần tìm kiếm: ");
        String bookName = new Scanner(System.in).nextLine();
        ListBook list = bookLogic.findByName(bookName);
        NodeBook target = list.getHead();

        System.out.println();
        if (target != null) {
            System.out.printf("%-15s | %-25s | %-30s | %-15s | %-15s | %-20s |\n",
                    "Mã Sách ",
                    "Tên Sách",
                    "Thể Loại",
                    "Tác Giả",
                    "Số Lượng",
                    "Trạng thái");

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
