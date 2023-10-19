package logic;

import data.BorrowerDat;
import entity.Borrower;
import list.ListBorrower;
import list.ListBorrower.NodeBorrower;

public class BorrowerLogic {

    private BorrowerDat borrowerDat = new BorrowerDat();

    public ListBorrower getList() {
        return borrowerDat.getList();
    }

    public void add(Borrower borrower) {
        borrowerDat.saveBorrower(borrower);
    }

    public void fix(Borrower borrower) {
        ListBorrower list = borrowerDat.getList();
        NodeBorrower target = list.getHead();
        while (target != null) {
            if (target.getInfo().getId().equalsIgnoreCase(borrower.getId())) {
                target.getInfo().update(borrower);
            }
            target = target.getNext();
        }
        borrowerDat.saveList(list);
    }

    public void delete(String id) {
        ListBorrower list = borrowerDat.getList();
        NodeBorrower target = list.getHead();
        while (target != null) {
            if (target.getInfo().getId().equalsIgnoreCase(id)) {
                list.removeNode(target);
            }
            target = target.getNext();
        }
        borrowerDat.saveList(list);
    }

    public boolean checkById(String id) {
        ListBorrower list = borrowerDat.getList();
        NodeBorrower target = list.getHead();
        while (target != null) {
            String[] tmp = target.getInfo().getId().split(",");
            if (tmp.length >= 1 && tmp[0].equalsIgnoreCase(id)) {
                return true;
            }
            target = target.getNext();
        }
        return false;
    }

    public ListBorrower findByName(String name) {
        ListBorrower result = new ListBorrower();
        ListBorrower list = borrowerDat.getList();
        NodeBorrower tg = list.getHead();
        while (tg != null) {
            if (tg.getInfo().getName().toLowerCase().contains(name.toLowerCase()))
                result.insertTail(tg.getInfo());
            tg = tg.getNext();
        }
        return result;
    }

    public ListBorrower findByAddress(String address) {
        ListBorrower result = new ListBorrower();
        ListBorrower list = borrowerDat.getList();
        NodeBorrower tg = list.getHead();
        while (tg != null) {
            if (tg.getInfo().getAddress().toLowerCase().contains(address.toLowerCase()))
                result.insertTail(tg.getInfo());
            tg = tg.getNext();
        }
        return result;
    }
    public int totalBorrower() {
        int count = 0;
        NodeBorrower tg = borrowerDat.getList().getHead();
        while (tg != null) {
            count++;
            tg = tg.getNext();
        }
        return count;
    }

}
