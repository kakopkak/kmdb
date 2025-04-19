package dto;

import com.kmdb.entity.Movie;
import lombok.Data;

import java.util.List;

@Data
public class MovieResponse {
    private List<Movie> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
