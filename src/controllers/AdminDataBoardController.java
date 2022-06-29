package controllers;

import models.Book;
import observer.BookStateObserver;
import view.AdminDataBoardView;

public class AdminDataBoardController implements BookStateObserver {

    AdminDataBoardView view;

    @Override
    public void onBookStatusChanged(Book book) {
        view.updateBookData();
    }


}
