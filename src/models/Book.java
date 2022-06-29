package models;

public class Book {

    private final String isbn;
    private final String title;
    private final String genre;
    private final String author;
    private final String publishedYear;
    private final String description;
    private float rating;
    //TODO Step 1.1 Add state and default it to AVAILABLE

    private Book(String isbn, String title, String genre, String author, String publishedYear, String description, float rating) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.publishedYear = publishedYear;
        this.description = description;
        this.rating = rating;
    }

    public static class Builder {

        private String isbn;
        private String title;
        private String genre;
        private String author;
        private String publishedYear;
        private String description;
        private float rating;

        public Builder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder genre(String genre) {
            this.genre = genre;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder publishedYear(String publishedYear) {
            this.publishedYear = publishedYear;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder rating(float rating) {
            this.rating = rating;
            return this;
        }

        public Book build() {
            return new Book(isbn, title, genre, author, publishedYear, description, rating);
        }

    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublishedYear() {
        return publishedYear;
    }

    public String getDescription() {
        return description;
    }

    public float getRating() { return rating; }
}
