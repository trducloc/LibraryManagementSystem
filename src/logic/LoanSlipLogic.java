package logic;

import data.LoanSlipDat;
import entity.LoanSlip;
import list.ListLoanSlip;
import list.ListLoanSlip.NodeLoanSlip;

public class LoanSlipLogic {

    private LoanSlipDat loanSlipDat = new LoanSlipDat();

    public ListLoanSlip getList() {
        return loanSlipDat.getList();
    }

    public void add(LoanSlip loanSlip) {
        loanSlipDat.saveLoanSlip(loanSlip);
    }

    public void fix(LoanSlip loanSlip) {
        ListLoanSlip list = loanSlipDat.getList();
        NodeLoanSlip tg = list.getHead();
        while (tg != null) {
            if (tg.getInfo().getIdLoanSlip().equalsIgnoreCase(loanSlip.getIdLoanSlip())
                    && tg.getInfo().getIdLoanSlip().equalsIgnoreCase(loanSlip.getIdLoanSlip())) {
                tg.getInfo().update(loanSlip);
            }
            tg = tg.getNext();
        }
        loanSlipDat.saveList(list);
    }

    public void delete(String idLoanSlip) {
        ListLoanSlip list = loanSlipDat.getList();
        NodeLoanSlip tg = list.getHead();
        while (tg != null) {
            if (tg.getInfo().getId().equalsIgnoreCase(idLoanSlip)) {
                list.removeNode(tg);
            }
            tg = tg.getNext();
        }
        loanSlipDat.saveList(list);
    }

    public boolean check(String idLoanSlip) {
        ListLoanSlip list = loanSlipDat.getList();
        ListLoanSlip.NodeLoanSlip target = list.getHead();
        while (target != null) {
            String[] tmp = target.getInfo().getIdLoanSlip().split(",");
            if (tmp.length >= 1 && tmp[0].equalsIgnoreCase(idLoanSlip)) {
                return true;
            }
            target = target.getNext();
        }
        return false;
    }
    public ListLoanSlip findByBorrowedDay(String borrowedDay) {
        ListLoanSlip result = new ListLoanSlip();
        ListLoanSlip list = loanSlipDat.getList();
        NodeLoanSlip tg = list.getHead();
        while (tg != null) {
            if (tg.getInfo().getBorrowedDay().contains(borrowedDay))
                result.insertTail(tg.getInfo());
            tg = tg.getNext();
        }
        return result;
    }
    public ListLoanSlip findById(String id) {
        ListLoanSlip result = new ListLoanSlip();
        ListLoanSlip list = loanSlipDat.getList();
        NodeLoanSlip tg = list.getHead();
        while (tg != null) {
            if (tg.getInfo().getId().contains(id))
                result.insertTail(tg.getInfo());
            tg = tg.getNext();
        }
        return result;
    }

    public int totalLoanSlip() {
        int count = 0;
        NodeLoanSlip tg = loanSlipDat.getList().getHead();
        while (tg != null) {
            count++;
            tg = tg.getNext();
        }
        return count;
    }
}


