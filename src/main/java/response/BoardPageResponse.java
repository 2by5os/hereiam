package response;

import domain.entity.HiaBoardEntity;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ohseoklee on 2018-12-07.
 */
public class BoardPageResponse {
    private int totalPages;
    private int totalElements;
    private int number;
    private int size;
    private List<BoardResponse> content = new ArrayList<>();

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<BoardResponse> getContent() {
        return content;
    }

    public void setContent(List<BoardResponse> content) {
        this.content = content;
    }

    public void init(Page<HiaBoardEntity> boardEntities) {
        this.totalElements = boardEntities.getNumberOfElements();
        this.totalPages = boardEntities.getTotalPages();
        this.number = boardEntities.getNumber();
        this.size = boardEntities.getSize();
    }
}
