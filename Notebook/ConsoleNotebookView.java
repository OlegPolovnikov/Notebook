package Notebook;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ConsoleNotebookView implements NotebookView {
    private final Scanner scanner = new Scanner(System.in);
    @Override
    public void showNotes(List<Note>notes) {
        if (notes.isEmpty()) {
            System.out.println("Записи не найдены.");
        } else {
            for (Note note : notes) {
                System.out.println(note);
            }
        }
    }
    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
    @Override
    public LocalDateTime getDateTimeInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите дату и время (dd.MM.yyyy HH:mm):");
            String input = scanner.nextLine();

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Неверный формат даты и времени. Попробуйте еще раз.");
            }
        }
    }
    @Override
    public LocalDate getDateInputFormat1() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите дату (dd.MM.yyyy):");
            String input = scanner.nextLine();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Неверный формат даты и времени. Попробуйте еще раз.");
            }
        }
    }
    @Override
    public String getDescriptionInput() {
        System.out.println("Введите описание записи:");
        return scanner.nextLine();
    }
    @Override
    public String getFileNameInput() {
        System.out.println("Введите имя файла:");
        return scanner.nextLine();
    }
}
