package data;
import entity.Book;
import list.ListBook;

import java.io.*;

public class BookDat {
    private static final String fileName = "Book.txt";

    public ListBook getList() {
        ListBook list = new ListBook();
        Book book;
        String[] tmp;
        String line = null;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                tmp = line.split(",");
                if (tmp.length >= 6) {
                    book = new Book(tmp[0], tmp[1], tmp[2], tmp[3], tmp[4], tmp[5]);
                    list.insertTail(book);
                } else {
                    continue;
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Không thể mở file hoặc không tồn tại file " + fileName);
        } catch (IOException ex) {
            System.err.println("Không thể đọc file " + fileName);
        }
        return list;
    }

    public void saveList(ListBook list) {
        ListBook.NodeBook target = list.getHead();

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            while (target != null) {
                bufferedWriter.write(target.getInfo().toString());
                bufferedWriter.newLine();
                target = target.getNext();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException ex) {
            System.err.println("Không thể ghi vào file " + fileName);
        }
    }
    public void saveBook(Book book) {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(fileName, true));
            pw.println(book.toString());
            pw.close();
        } catch (IOException e) {
            System.err.println("Không thể ghi vào tệp " + fileName);
        }
    }
}
