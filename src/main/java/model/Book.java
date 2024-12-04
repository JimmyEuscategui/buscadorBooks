package model;

public class Book {
    private int id; // ID, si es necesario
    private String title;
    private String author;
    private String language;
    private int downloadCount;

    // Constructor
    public Book(int id, String title, String author, String language, int downloadCount) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.language = language;
        this.downloadCount = downloadCount;
    }

    // Métodos getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getLanguage() {
        return language;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Título: " + title +
                ", Autor: " + author +
                ", Idioma: " + language +
                ", Descargas: " + downloadCount;
    }
}
