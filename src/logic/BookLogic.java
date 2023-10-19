package logic;

import data.BookDat;
import entity.Book;
import list.ListBook;
import list.ListBook.NodeBook;

public class BookLogic {
    private BookDat bookDat = new BookDat();

    public ListBook getList() {
        return bookDat.getList();
    }
    public void add(Book book) {
        bookDat.saveBook(book);
    }

    public void fix(Book book) {
        ListBook list = bookDat.getList();
        NodeBook target = list.getHead();
        while (target != null) {
            if (target.getInfo().getIdBook().equalsIgnoreCase(book.getIdBook())) {
                target.getInfo().updateBook(book);
            }
            target = target.getNext();
        }
        bookDat.saveList(list);
    }

    public void delete(String idBook) {
        ListBook list = bookDat.getList();
        NodeBook target = list.getHead();
        while (target != null) {
            if (target.getInfo().getIdBook().equalsIgnoreCase(idBook)) {
                list.removeNode(target);
            }
            target = target.getNext();
        }
        bookDat.saveList(list);
    }

    public boolean check(String idBook) {
        ListBook list = bookDat.getList();
        NodeBook target = list.getHead();
        while (target != null) {
            String[] tmp = target.getInfo().getIdBook().split(",");
            if (tmp.length >= 1 && tmp[0].equalsIgnoreCase(idBook)) {
                return true;
            }
            target = target.getNext();
        }
        return false;
    }

    public ListBook findByName(String bookName) {
        ListBook result = new ListBook();
        ListBook list = bookDat.getList();
        NodeBook target = list.getHead();
        while (target != null) {
            if (target.getInfo().getBookName().toLowerCase().contains(bookName.toLowerCase()))
                result.insertTail(target.getInfo());
            target = target.getNext();
        }
        return result;
    }
    public ListBook.NodeBook findById(String idBook) {
        ListBook list = bookDat.getList();
        ListBook.NodeBook target = list.getHead();
        while (target != null) {
            if (target.getInfo().getIdBook().equalsIgnoreCase(idBook))
                return target;
            target = target.getNext();
        }
        return null;
    }
    public int totalBook() {
        int count = 0;
        NodeBook target = bookDat.getList().getHead();
        while (target != null) {
            count++;
            target = target.getNext();
        }
        return count;
    }
//    public int totalBookRemain(){
//        int count=0;
//        NodeBook target = bookDat.getList().getHead();
//        while(target!=null){
//            if (target.getInfo().getStatus().equalsIgnoreCase("Available"))
//                count++;
//            target=target.getNext();
//        }
//        return count;
//    }

}
