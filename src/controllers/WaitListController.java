package controllers;

import enums.BookState;
import exceptions.MaximumBookCheckedOutException;
import models.Book;
import models.User;
import observer.BookStateObserver;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class WaitListController implements BookStateObserver {

    private Map<Book, Queue<User>> waitList = new HashMap<>();

    public void addToWaitList(Book book, User user) {
        Queue<User> queue = waitList.get(book);
        if (queue == null) {
            queue = new PriorityQueue<>();
            waitList.put(book, queue);
        }
        queue.add(user);
    }

    public void removeFromWaitList(Book book, User user) {
        Queue<User> queue = waitList.get(book);
        if (queue == null) {
            return;
        }
        queue.remove(user);
    }

    private User getNextOnWaitListIfAvailable(Book book) {
        Queue<User> queue = waitList.get(book);
        if (queue == null) {
            return null;
        }
        User user = queue.poll();
        return user;
    }

    @Override
    public void onBookStatusChanged(Book book) {
        if (book.getState() != BookState.CHECKED_IN) { return; }

        User user = getNextOnWaitListIfAvailable(book);
        if (user != null) {
            try {
                book.setState(BookState.ON_HOLD);
            } catch (MaximumBookCheckedOutException e) {
                System.out.println(e.getLocalizedMessage());
                return;
            }
        }
    }
}
