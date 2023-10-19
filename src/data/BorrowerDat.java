
package data;


import entity.Borrower;
import list.ListBorrower;

import java.io.*;


public class BorrowerDat {
    private static final String fileName = "Borrower.txt";


    public ListBorrower getList() {
        ListBorrower list = new ListBorrower();
        Borrower borrower;
        String[] tmp;
        String line = null;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                tmp = line.split(",");
                if (tmp.length >= 3) {
                    borrower = new Borrower(tmp[0], tmp[1], tmp[2], tmp[3], tmp[4]);
                    list.insertTail(borrower);
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

    public void saveList(ListBorrower list) {
        ListBorrower.NodeBorrower tg = list.getHead();

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            while (tg != null) {
                bufferedWriter.write(tg.getInfo().toString());
                bufferedWriter.newLine();
                tg = tg.getNext();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException ex) {
            System.err.println("Không thể ghi vào file " + fileName);
        }
    }

    public void saveBorrower(Borrower borrower) {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(fileName, true));
            pw.println(borrower.toString());
            pw.close();
        } catch (IOException e) {
            System.err.println("Không thể ghi vào tệp " + fileName);
        }
    }
}
