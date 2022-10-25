package controllers;

import models.Book;
import models.User;
import observer.BookStateObserver;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class WaitListController implements BookStateObserver {

    /**
     * Check if the book has a user on the waitlist.
     * Check the book out if there is a user. Otherwise, leave it checked in to be processed later.
     * @param book
     */
    @Override
    public void onBookStatusChanged(Book book) {
        //TODO Step 2.1 - Implement this functionality
    }




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


}
