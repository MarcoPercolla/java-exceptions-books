package org.exceptions;

public class Book {
    private String title;
    private int pageCount;
    private String author;
    private String publisher;

    public Book(String title, int pageCount, String author, String publisher) throws IllegalArgumentException {
        setTitle(title);
        setPageCount(pageCount);
        setAuthor(author);
        setPublisher(publisher);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Il titolo non può essere vuoto");
        }
        this.title = title;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        if (pageCount <= 0   ) {
            throw new IllegalArgumentException("Il numero di pagine deve essere maggiore di zero");
        }
        this.pageCount = pageCount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null || author.isEmpty()) {
            throw new IllegalArgumentException("L'autore non può essere vuoto");
        }
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        if (publisher == null || publisher.isEmpty()) {
            throw new IllegalArgumentException("L'editore non può essere vuoto");
        }
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return title + ";" + pageCount + ";" + author + ";" + publisher;
    }

    public static Book fromString(String bookString) {
        String[] parts = bookString.split(";");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Formato del libro non valido");
        }
        String title = parts[0];
        int pageCount = Integer.parseInt(parts[1]);
        String author = parts[2];
        String publisher = parts[3];
        return new Book(title, pageCount, author, publisher);
    }
}
