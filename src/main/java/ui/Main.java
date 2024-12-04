package ui;

import api.GutendexAPI;
import api.BookParser;
import model.Book;
import service.BookService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GutendexAPI api = new GutendexAPI();
        BookParser parser = new BookParser();
        BookService service = new BookService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Catálogo de libros ---");
            System.out.println("1. Buscar libro por título");
            System.out.println("2. Listar todos los libros");
            System.out.println("3. Listar libros por idioma");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (option) {
                case 1:
                    System.out.print("Ingrese el título del libro: ");
                    String title = scanner.nextLine();
                    try {
                        String jsonResponse = api.fetchBookByTitle(title);
                        System.out.println("Respuesta de la API:\n" + jsonResponse); // Imprimir para depurar
                        Book book = parser.parseBook(jsonResponse);
                        if (book != null) {
                            service.addBook(book);
                            System.out.println("Libro encontrado y agregado:");
                            System.out.println(book);
                        } else {
                            System.out.println("No se encontró ningún libro con ese título.");
                        }
                    } catch (Exception e) {
                        System.err.println("Error al buscar el libro: " + e.getMessage());
                    }
                    break;

                case 2:
                    List<Book> allBooks = service.getAllBooks();
                    if (allBooks.isEmpty()) {
                        System.out.println("No hay libros en el catálogo.");
                    } else {
                        System.out.println("Lista de libros:");
                        allBooks.forEach(System.out::println);
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el idioma (ejemplo: 'en' para inglés): ");
                    String language = scanner.nextLine();
                    List<Book> booksByLanguage = service.getBooksByLanguage(language);
                    if (booksByLanguage.isEmpty()) {
                        System.out.println("No hay libros en el catálogo con el idioma especificado.");
                    } else {
                        System.out.println("Libros en el idioma " + language + ":");
                        booksByLanguage.forEach(System.out::println);
                    }
                    break;

                case 4:
                    System.out.println("Saliendo ¡Hasta pronto!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }
}
