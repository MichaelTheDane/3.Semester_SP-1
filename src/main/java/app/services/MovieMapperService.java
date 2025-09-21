package app.services;

import app.DTO.MovieDTO;
import app.entities.Movie;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

public class MovieMapperService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Converts raw JSON from the API into a list of Movie entities.
     *
     * @param json the JSON string returned from ApiFetchers
     * @return List<Movie> mapped entities
     */
    public List<Movie> mapJsonToMovies(String json) {
        try {
            // Step 1: Parse JSON into a list of MovieDTOs
            List<MovieDTO> dtoList = objectMapper.readValue(json, new TypeReference<>() {});

            // Step 2: Convert DTOs into Movie entities
            return dtoList.stream()
                    .map(this::mapDtoToEntity)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Failed to map JSON to Movies", e);
        }
    }

    /**
     * Maps a MovieDTO into a Movie entity.
     */
    private Movie mapDtoToEntity(MovieDTO dto) {
        return Movie.builder()
                .adult(dto.isAdult())
                .originalTitle(dto.getOriginal_title())
                .popularity(dto.getPopularity())
                .video(dto.isVideo())
                .build();
    }
}
