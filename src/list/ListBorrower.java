package list;

import entity.Borrower;

public class ListBorrower {
    private NodeBorrower head, tail;

    public ListBorrower() {
        this.head = null;
        this.tail = null;
    }

    public NodeBorrower getHead() {
        return head;
    }

//    public NodeBorrower getTail() {
//        return tail;
//    }
//
//    public void insertHead(Borrower borrower) {
//        NodeBorrower newNode = new NodeBorrower(borrower);
//        if (head == null) {
//            head = tail = newNode;
//        } else {
//            newNode.setNext(head); // Gán newNode vào đầu danh sách.
//            head = newNode;           // cập nhật lại vị trí đầu của danh sách.
//        }
//    }

    public void insertTail(Borrower borrower) {
        NodeBorrower newNode = new NodeBorrower(borrower);
        if (head == null) head = tail = newNode;
        else {
            tail.setNext(newNode);      // gan node vao cuoi danh sach
            tail = newNode;             // cap nhat lai vi tri cuoi cua danh sach
        }
    }
//    public void deleteNode(NodeBorrower nodeBorrower) {
//        NodeBorrower tg = head;
//        NodeBorrower t = tg;
//        while (tg != null) {
//            if (tg == nodeBorrower) break;
//            else {
//                t = tg;
//                tg = tg.getNext();
//            }
//        }
//
//        if (tg == null) return; // Không tồn tại nodenguoimuon.
//        if (t != null)          // Tồn tại
//        {
//            t.setNext(tg.getNext());
//            tg = null;
//        } else // nếu là đầu danh sách.
//        {
//            head = tg.getNext();
//        }
//    }

    public void removeNode(NodeBorrower nodeBorrower) {
        NodeBorrower tg = head;
        NodeBorrower t = tg;
        while (tg != null) {
            if (tg == nodeBorrower) {
                break;
            } else {
                t = tg;
                tg = tg.getNext();
            }
        }
        if (tg == head) head = head.getNext(); // neu node o dau danh sach
        else {
            if (tg.getNext() == null) {
                t = null; // neu node o cuoi danh sach
            } else {
                t.setNext(tg.getNext());
            }
        }
    }

    public void sortById() {
        Borrower tmp;
        String type1;
        String type2;
        if (head == null) return;
        NodeBorrower tg = head, p;
        while (tg.getNext() != null) {
            p = tg.getNext();
            type1 = tg.getInfo().getId().split(" ")[tg.getInfo().getId().split(" ").length - 1];
            while (p != null) {
                type2 = p.getInfo().getId().split(" ")[p.getInfo().getId().split(" ").length - 1];
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
    public void sortByName() {
        Borrower tmp;
        String type1;
        String type2;
        if (head == null) return;
        NodeBorrower tg = head, p;
        while (tg.getNext() != null) {
            p = tg.getNext();
            type1 = tg.getInfo().getName().split(" ")[tg.getInfo().getName().split(" ").length - 1];
            while (p != null) {
                type2 = p.getInfo().getName().split(" ")[p.getInfo().getName().split(" ").length - 1];
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

    public class NodeBorrower {
        private Borrower info;
        private NodeBorrower next;


        public NodeBorrower(Borrower info) {
            this.info = info;
        }

        public Borrower getInfo() {
            return info;
        }

        public void setInfo(Borrower info) {
            this.info = info;
        }

        public NodeBorrower getNext() {
            return next;
        }

        public void setNext(NodeBorrower next) {
            this.next = next;
        }
    }
}
