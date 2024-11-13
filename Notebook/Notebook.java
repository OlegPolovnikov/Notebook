package Notebook;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Notebook {
    private final List<Note>notes = new ArrayList<>();
    public void add(Note note) {
        notes.add(note);
    }
    public List<Note>getNotesForDay(LocalDateTime dateTime) {
        return notes.stream() .filter(note-> note.dateTime().toLocalDate().isEqual(dateTime.toLocalDate())) .sorted(Comparator.comparing(Note::dateTime)) .collect(Collectors.toList());
    }
    public List<Note>getNotesForWeek(LocalDateTime startOfWeek) {
        LocalDateTime endOfWeek=startOfWeek.plusWeeks(1);
        return notes.stream() .filter(note-> !note.dateTime().isBefore(startOfWeek)&& !note.dateTime().isAfter(endOfWeek)) .sorted(Comparator.comparing(Note::dateTime)) .collect(Collectors.toList());
    }
    public void saveToFile(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Note note : notes) {
                writer.write(note.toString());
                writer.newLine();
            }
        }
    }
    public void loadFromFile(String fileName) throws IOException {
        notes.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine())!=null) {
                String[]parts = line.split("-",2);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(parts[0], formatter);
                String description = parts[1];
                notes.add(new Note(dateTime,description));
            }
        }
    }


}
