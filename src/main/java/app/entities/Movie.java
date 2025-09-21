package app.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "adult")
    private Boolean adult;

    @Column(name = "original_title")
    private String originalTitle;

    @Column(name = "popularity")
    private Double popularity;

    @Column(name = "video")
    private Boolean video;
    //Attributes

    //Getters & Setters
}
