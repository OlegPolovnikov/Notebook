package Notebook;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record Note(LocalDateTime dateTime, String description) {
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm-");
        String formattedDateTime = dateTime.format(formatter);
        return formattedDateTime + description;
    }
}
