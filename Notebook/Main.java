package Notebook;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Notebook model = new Notebook();
        NotebookView view = new ConsoleNotebookView();
        NotebookPresenter presenter = new NotebookPresenter(model, view);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Добавить запись");
            System.out.println("2. Показать записи на день");
            System.out.println("3. Показать записи на неделю");
            System.out.println("4. Сохранить записи");
            System.out.println("5. Загрузить записи");
            System.out.println("6. Удалить все записи из файла");
            System.out.println("7. Выйти");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    presenter.addNote();
                    break;
                case 2:
                    presenter.showNotesForDay();
                    break;
                case 3:
                    presenter.showNotesForWeek();
                    break;
                case 4:
                    presenter.saveNotes();
                    break;
                case 5:
                    presenter.loadNotes();
                    break;
                case 6:
                    presenter.deleteNotes();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Неверный выбор");
            }
        }
    }
}
