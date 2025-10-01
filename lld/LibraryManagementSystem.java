package lld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryManagementSystem {
    public static class Book{
        public String bookId;
        public String title;
        public String authors;
        public boolean isAvailable;
        Book(String bookId,String authors,String title)
        {
            this.bookId=bookId;
            this.authors=authors;
            this.title=title;
            this.isAvailable=true;
        }

        public void setAvailable(boolean available) {
            isAvailable = available;
        }

        public String getBookId() {
            return bookId;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthors() {
            return authors;
        }

        public boolean isAvailable() {
            return isAvailable;
        }
    }

    public static class Member{
        public String memberId;
        public String name;
        public List<Book> borrowedBooks;
        Member(String memberId,String name)
        {
            this.memberId=memberId;
            this.name=name;
            this.borrowedBooks=new ArrayList<>();
        }

        public String getMemberId() {
            return memberId;
        }

        public String getName() {
            return name;
        }

        public List<Book> getBorrowedBooks() {
            return borrowedBooks;
        }

        public void borrowBook(Book book)
        {
            borrowedBooks.add(book);
        }
        public void returnBook(Book book)
        {
            borrowedBooks.remove(book);
        }
    }
    public static class BookFactor{
        public static Book createBook(String bookId,String title,String authors){
            return new Book(bookId,title,authors);
        }
    }

    public static class MemberFactor{
        public static Member createMember(String memberId,String name)
        {
            return new Member(memberId,name);
        }
    }
    public interface LibraryObserver{
        void notifyObservers(String msg);
    }
    public static class LibraryMembers implements LibraryObserver{

        public Member member;
        LibraryMembers(Member member){
            this.member=member;
        }
        @Override
        public void notifyObservers(String msg) {
            System.out.println("Notify to "+member.getName()+"about "+msg);
        }
    }
    public static class Library{
        public static Library intstance;
        private Library(){};
        public static Library getIntstance(){
            if(intstance==null){
                intstance = new Library();
            }
            return intstance;
        }
        Map<String ,Book> books = new HashMap<>();
        Map<String,Member> members = new HashMap<>();
        Map<String,List<LibraryMembers>> memberObservers = new HashMap<>();

        public void addBook(Book book){
            books.put(book.getBookId(),book);
        }
        public void removeBook(String bookId)
        {
            books.remove(bookId);
        }

        public void addMembers(Member member)
        {
            members.put(member.getMemberId(),member);
        }
        public void removeMembers(String memberId){
            members.remove(memberId);
        }

        public void borrowBook(String memberId,String bookId){
            Member member = members.get(memberId);
            Book book = books.get(bookId);
            if(book==null || member==null) return;

            if(book.isAvailable()){
                book.setAvailable(false);
                member.borrowBook(book);
                System.out.println("Book with id: "+bookId+" borrowed by "+member.getName());
                return;
            }else {
                System.out.println(book.getTitle()+" currently not available!");
                onBraodMemberForNotification(bookId,new LibraryMembers((member)));
                return;
            }
        }

        public void returnBook(String bookId,String memberId){
            Book book = books.get(bookId);
            Member member = members.get(memberId);
            if(book!=null && member!=null)
            {
                book.setAvailable(true);
                member.returnBook(book);
                System.out.println(member.getName() + " return book by id: "+bookId);
                if(memberObservers.containsKey(bookId)){
                    for(LibraryObserver libraryObserver : memberObservers.get(bookId)){
                        libraryObserver.notifyObservers("Book that you are looking for now available "+book.getTitle());
                    }
                    memberObservers.remove(bookId);
                }
            }
        }

        public List<Book> searchByTitle(String title){
            List<Book> bookList = new ArrayList<>();
            for(Book book : books.values())
            {
                if(book.getTitle().equalsIgnoreCase(title))
                {
                    bookList.add(book);
                }
            }
            return bookList;
        }

        private void onBraodMemberForNotification(String bookId, LibraryMembers libraryMembers) {
            memberObservers.computeIfAbsent(bookId,k->new ArrayList<>()).add(libraryMembers);
        }

    }
    public static void main(String[] args) {
        Library library = Library.getIntstance();
        library.addBook(new Book("B123","RD","Java basics"));
        library.addBook(new Book("B125","GH","Python Basic"));

        library.addMembers(new Member("M123","rohit"));
        library.addMembers(new Member("M125","mohit"));

        library.borrowBook("M123","B123");
        library.borrowBook("M125","B123");

        library.returnBook("B123","M123");
    }
}
