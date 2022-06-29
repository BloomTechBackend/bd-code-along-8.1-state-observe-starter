import controllers.LibraryController;
import controllers.WaitListController;
import enums.BookState;
import models.Book;
import models.User;
import observer.BookStateObserver;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class Step2Test {

    @Test
    public void testObserversOnCheckout() {
        LibraryController library = new LibraryController();
        TestObserver observer = new TestObserver();
        User user = new User("test user");
        Book book = library.getBooks()[0];

        library.addObserver(observer);
        library.checkoutBook(book, user);

        assertEquals(1, user.getBooks().size());
        assertEquals(1, observer.count);
        assertEquals(BookState.CHECKED_OUT, book.getState());
    }

    @Test
    public void testObserversOnCheckIn() {
        LibraryController library = new LibraryController();
        TestObserver observer = new TestObserver();
        User user = new User("test user");
        Book book = library.getBooks()[0];

        library.addObserver(observer);
        library.checkoutBook(book, user);
        library.returnBook(book, user);

        assertEquals(0, user.getBooks().size());
        assertEquals(2, observer.count);
        assertEquals(BookState.CHECKED_IN, book.getState());

        library.processCheckedInBooks();

        assertEquals(BookState.AVAILABLE, book.getState());
    }

    @Test
    public void testWaitListObserver() {
        LibraryController library = new LibraryController();
        WaitListController waitList = new WaitListController();
        User user = new User("test user");
        User nextUser = new User("next user");
        Book book = library.getBooks()[0];

        library.addObserver(waitList);
        library.checkoutBook(book, user);
        waitList.addToWaitList(book, nextUser);
        library.returnBook(book, user);

        assertEquals(0, user.getBooks().size());
        assertEquals(0, nextUser.getBooks().size());
        assertEquals(BookState.ON_HOLD, book.getState());
    }

    private class TestObserver implements BookStateObserver {

        int count;

        @Override
        public void onBookStatusChanged(Book book) {
            count++;
        }
    }

}
