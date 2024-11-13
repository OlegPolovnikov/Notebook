package Notebook;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class NotebookPresenter {
    private final Notebook model;
    private final NotebookView view;
    public NotebookPresenter(Notebook model, NotebookView view) {
        this.model = model;
        this.view = view;
    }
    public void addNote() {
        LocalDateTime dateTime = view.getDateTimeInput();
        String description = view.getDescriptionInput();
        model.add(new Note(dateTime,description));
        view.showMessage("Запись добавлена.");
    }
    public void showNotesForDay() {
        LocalDate date = view.getDateInputFormat1();
        List<Note>notes = model.getNotesForDay(date.atStartOfDay());
        view.showNotes(notes);
    }
    public void showNotesForWeek() {
        LocalDate startOfWeek = view.getDateInputFormat1();
        List<Note>notes = model.getNotesForWeek(startOfWeek.atStartOfDay());
        view.showNotes(notes);
    }
    public void saveNotes() {
        String fileName = view.getFileNameInput();
        if (fileName == null || fileName.isEmpty()) {
            view.showMessage("Пожалуйста, введите имя файла.");
            return;
        }
        try {
            model.saveToFile(fileName);
            view.showMessage("Записи сохранены в " + fileName);
        } catch (Exception e) {
            view.showMessage("Ошибка при сохранении записей: " + e.getMessage());
        }
    }
    public void loadNotes() {
        String fileName = view.getFileNameInput();
        if (fileName == null || fileName.isEmpty()) {
            view.showMessage("Пожалуйста, введите имя файла.");
            return;
        }
        try {
            model.loadFromFile(fileName);
            view.showMessage("Записи загружены из " + fileName);
        } catch (Exception e) {
            view.showMessage("Ошибка при загрузке записей: " + e.getMessage());
        }
    }
    public void deleteNotes() {
        String fileName = view.getFileNameInput();
        if (fileName == null || fileName.isEmpty()) {
            view.showMessage("Пожалуйста, введите имя файла.");
            return;
        }
        try {
            Files.writeString(Paths.get(fileName), "");
            System.out.println("Файл очищен.");
        } catch (Exception e) {
            view.showMessage("Ошибка при удалении записей: " + e.getMessage());
        }
    }
}
