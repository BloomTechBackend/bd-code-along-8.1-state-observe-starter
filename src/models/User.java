package models;

import exceptions.BookNotFoundException;
import exceptions.MaximumBookCheckedOutException;
import validators.Validator;

import java.util.*;

public class User implements Comparable {

    private final String id;
    private String name;
    private String password;
    private List<Book> books;

    private static final int MAX_BOOKS = 10;

    public User(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.books = new ArrayList<>();
    }

    /**
     * Validates that the password is valid before saving it to the user.
     * Validations include a minimum character count of 8 and that a number,
     * upper case letter, and special character are included.
     * @param password - password to store
     * @return true if the password is successfully saved
     */
    public boolean validateAndSet(String password) {
        Validator validator = new Validator.Builder()
                .characterLimit(8)
                .mustIncludeUpperCaseLetter()
                .mustIncludeNumber()
                .mustIncludeSpecialCharacter().build();
        if (validator.validate(password)) {
            this.password = password;
            return true;
        }
        return false;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    /**
     * Adds the book to the user's books
     * @param book - book to be added
     * @throws MaximumBookCheckedOutException
     */
    public void addBook(Book book) throws MaximumBookCheckedOutException {
        if (books.size() >= MAX_BOOKS) {
            throw new MaximumBookCheckedOutException();
        }

        books.add(book);
    }

    /**
     * Removes the book from the
     * @param book
     * @throws BookNotFoundException
     */
    public void removeBook(Book book) throws BookNotFoundException {
        if (!books.remove(book)) {
            throw new BookNotFoundException();
        }
    }

    @Override
    public int compareTo(Object o) {
        User u = (User) o;
        if (u == null) {
            throw new ClassCastException();
        }
        return u.id.compareTo(this.id);
    }
}
