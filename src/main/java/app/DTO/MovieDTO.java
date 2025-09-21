package app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private boolean adult;
    private int id;
    private String original_title;
    private double popularity;
    private boolean video;
}