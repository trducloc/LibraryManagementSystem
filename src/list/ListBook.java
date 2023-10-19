package list;

import entity.Book;

public class ListBook {

    NodeBook head, tail;

    public ListBook() {
        this.head = null;
        this.tail = null;
    }

    public NodeBook getHead() {
        return head;
    }

//    public NodeBook getTail() {
//        return tail;
//    }
//
//    public void insertHead(Book book) {
//        NodeBook newNode = new NodeBook(book);
//        if (head == null) {
//            head = tail = newNode;
//        } else {
//            newNode.setNext(head); // Gán newNode vào đầu danh sách.
//            head = newNode;           // cập nhật lại vị trí đầu của danh sách.
//        }
//    }

    public void insertTail(Book book) {
        NodeBook newNode = new NodeBook(book);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);      // Gán newNode vào cuối danh sách.
            tail = newNode;               // Cập nhật lại vị trí cuối của danh sách.
        }
    }


    public void removeNode(NodeBook nodeBook) {
        NodeBook tg = head;
        NodeBook t = tg;
        while (tg != null) {
            if (tg == nodeBook) {
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

    public void sortByName() {
        Book tmp;
        String name1;
        String name2;
        if (head == null) return;
        NodeBook tg = head, p;
        while (tg.getNext() != null) {
            p = tg.getNext();
            name1 = tg.getInfo().getBookName().split(" ")[tg.getInfo().getBookName().split(" ").length - 1];
            while (p != null) {
                name2 = p.getInfo().getBookName().split(" ")[p.getInfo().getBookName().split(" ").length - 1];
                if (name1.compareToIgnoreCase(name2) > 0) {
                    name1 = name2;
                    tmp = tg.getInfo();
                    tg.setInfo(p.getInfo());
                    p.setInfo(tmp);
                }
                p = p.getNext();
            }
            tg = tg.getNext();
        }
    }

    public class NodeBook {

        private Book info;
        private NodeBook next;

//        public NodeBook() {
//            this.info = new Book();
//            next = null;
//        }

        public NodeBook(Book info) {
            this.info = info;
        }

        public Book getInfo() {
            return info;
        }

        public void setInfo(Book info) {
            this.info = info;
        }

        public NodeBook getNext() {
            return next;
        }

        public void setNext(NodeBook next) {
            this.next = next;
        }
    }

}
