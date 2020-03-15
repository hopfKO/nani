package nani.app.welcome;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Anime {
    private String id;
    private String title;
    private LocalDate airDate;
}
