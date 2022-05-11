package tech.icoding.scb.core.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Joe
 * @date : 2022/5/11
 */
@Data
@NoArgsConstructor
public class PageData<T> implements Serializable {


    private long total;
    private int pageNumber;
    private int pageSize;
    private List<T> content = new ArrayList<>();


    public PageData(Page<T> page){
        this.content = page.getContent();
        this.total = page.getTotalElements();
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
    }

    public boolean isEmpty(){
        return this.total < 1;
    }
}
