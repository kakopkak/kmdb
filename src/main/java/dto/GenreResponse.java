package dto;

import com.kmdb.entity.Genre;
import lombok.Data;

import java.util.List;

@Data
public class GenreResponse {
    private List<Genre> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
