package dto;

import com.kmdb.entity.Actor;
import lombok.Data;

import java.util.List;

@Data
public class ActorResponse {
    private List<Actor> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
