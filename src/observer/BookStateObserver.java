package observer;

import models.Book;

public interface BookStateObserver {
    void onBookStatusChanged(Book book);
}
