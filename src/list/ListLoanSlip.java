package list;

import entity.LoanSlip;

public class ListLoanSlip {
    private NodeLoanSlip head, tail;

    public ListLoanSlip() {
        this.head = null;
        this.tail = null;
    }

    public NodeLoanSlip getHead() {
        return head;
    }


    public void insertTail(LoanSlip loanSlip) {
        NodeLoanSlip newNode = new NodeLoanSlip(loanSlip);
        if (head == null) head = tail = newNode;
        else {
            tail.setNext(newNode);      // Gán newNode vào cuối danh sách.
            tail = newNode;               // Cập nhật lại vị trí cuối của danh sách.
        }
    }

    public void removeNode(NodeLoanSlip nodeLoanSlip) {
        NodeLoanSlip tg = head;
        NodeLoanSlip t = tg;
        while (tg != null) {
            if (tg == nodeLoanSlip) {
                break;
            } else {
                t = tg;
                tg = tg.getNext();
            }
        }
        if (tg == head) head = head.getNext(); // Nếu node ở đầu danh sách.
        else {
            if (tg.getNext() == null) {
                t = null; // Nếu node ở cuối danh sách.
            } else {
                t.setNext(tg.getNext());
            }
        }
    }

    public void sortById() {
        LoanSlip tmp;
        String type1;
        String type2;
        if (head == null) return;
        NodeLoanSlip tg = head, p;
        while (tg.getNext() != null) {
            p = tg.getNext();
            type1 = tg.getInfo().getIdLoanSlip().split(" ")[tg.getInfo().getIdLoanSlip().split(" ").length - 1];
            while (p != null) {
                type2 = p.getInfo().getIdLoanSlip().split(" ")[p.getInfo().getIdLoanSlip().split(" ").length - 1];
                if (type1.compareToIgnoreCase(type2) > 0) {
                    type1 = type2;
                    tmp = tg.getInfo();
                    tg.setInfo(p.getInfo());
                    p.setInfo(tmp);
                }
                p = p.getNext();
            }
            tg = tg.getNext();
        }
    }

    public void sortByQuantityBorrowed() {
        LoanSlip tmp;
        if (head == null) return;
        NodeLoanSlip tg = head, p;
        while (tg.getNext() != null) {
            p = tg.getNext();
            while (p != null) {
                if (tg.getInfo().getQuantityBorrowed().compareToIgnoreCase(p.getInfo().getQuantityBorrowed()) > 0) {
                    tmp = tg.getInfo();
                    tg.setInfo(p.getInfo());
                    p.setInfo(tmp);
                }
                p = p.getNext();
            }
            tg = tg.getNext();
        }
    }

    public class NodeLoanSlip {
        private LoanSlip info;
        private NodeLoanSlip next;

        public NodeLoanSlip(LoanSlip info) {
            this.info = info;
        }

        public LoanSlip getInfo() {
            return info;
        }

        public void setInfo(LoanSlip info) {
            this.info = info;
        }

        public NodeLoanSlip getNext() {
            return next;
        }

        public void setNext(NodeLoanSlip next) {
            this.next = next;
        }
    }
}
