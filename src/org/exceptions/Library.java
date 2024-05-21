
package org.exceptions;

import java.io.*;
import java.util.Scanner;

public class Library {
    private static final String FILE_NAME = "./src/org/exceptions/books.txt";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Inserisci il numero di libri da aggiungere: ");
            int n = scanner.nextInt();
            scanner.nextLine();

            Book[] books = new Book[n];

            for (int i = 0; i < n; i++) {
                try {
                    System.out.println("Inserisci i dati del libro " + (i + 1) + ":");
                    System.out.print("Titolo: ");
                    String title = scanner.nextLine();

                    System.out.print("Numero di pagine: ");
                    int pageCount = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Autore: ");
                    String author = scanner.nextLine();

                    System.out.print("Editore: ");
                    String publisher = scanner.nextLine();

                    books[i] = new Book(title, pageCount, author, publisher);
                } catch (IllegalArgumentException e) {
                    System.out.println("Errore: " + e.getMessage());
                    i--;
                }
            }

            writeBooksToFile(books);

            Book[] loadedBooks = readBooksFromFile();
            System.out.println("Libri caricati dal file:");
            for (Book book : loadedBooks) {
                System.out.println(book);
            }
        }
    }

    private static void writeBooksToFile(Book[] books) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (Book book : books) {
                writer.write(book.toString() + "\n");
            }
            System.out.println("Dati salvati su file con successo.");
        } catch (IOException e) {
            System.out.println("Errore durante la scrittura su file: " + e.getMessage());
        }
    }

    private static Book[] readBooksFromFile() {
        Book[] books = null;
        try (FileReader fileReader = new FileReader(FILE_NAME)) {
            int count = 0;
            int ch;
            boolean newLine = true;
            while ((ch = fileReader.read()) != -1) {
                if (newLine) {
                    count++;
                    newLine = false;
                }
                if (ch == '\n') {
                    newLine = true;
                }
            }

            books = new Book[count];
        } catch (IOException e) {
            System.out.println("Errore durante la lettura dal file: " + e.getMessage());
        }

        try (FileReader fileReader = new FileReader(FILE_NAME)) {
            int index = 0;
            String line = "";
            int ch;
            while ((ch = fileReader.read()) != -1) {
                if (ch == '\n') {
                    try {
                        Book book = Book.fromString(line);
                        books[index++] = book;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Errore durante la lettura di un libro dal file: " + e.getMessage());
                    }
                    line = "";
                } else {
                    line += (char) ch;
                }
            }

            if (!line.isEmpty()) {
                try {
                    Book book = Book.fromString(line);
                    books[index++] = book;
                } catch (IllegalArgumentException e) {
                    System.out.println("Errore durante la lettura di un libro dal file: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Errore durante la lettura dal file: " + e.getMessage());
        }

        return books;
    }
}

