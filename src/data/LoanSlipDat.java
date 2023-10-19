package data;


import entity.Borrower;
import entity.LoanSlip;
import list.ListLoanSlip;

import java.io.*;


public class LoanSlipDat {

    private static final String fileName = "LoanSlip.txt";

    public ListLoanSlip getList() {
        ListLoanSlip list = new ListLoanSlip();
        LoanSlip loanSlip;
        String[] tmp;
        String line = null;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                tmp = line.split(",");
                if (tmp.length >= 5) {
                    loanSlip = new LoanSlip(tmp[0], tmp[1],tmp[2],tmp[3],tmp[4]);
                    list.insertTail(loanSlip);
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

    public void saveList(ListLoanSlip list) {
        ListLoanSlip.NodeLoanSlip tg = list.getHead();

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

    public void saveLoanSlip(LoanSlip loanSlip) {

        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(fileName, true));
            pw.println(loanSlip.toString());
            pw.close();
        } catch (IOException e) {
            System.err.println("Không thể ghi vào tệp " + fileName);
        }
    }
}
