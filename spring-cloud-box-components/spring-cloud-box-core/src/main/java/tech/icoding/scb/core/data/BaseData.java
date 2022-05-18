package tech.icoding.scb.core.data;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author : Joe
 * @date : 2022/4/26
 */
@Data
public abstract class BaseData <ID extends Serializable> implements Serializable {
    abstract protected ID getId();

    /**
     * 创建日期
     */
    protected LocalDateTime createdTime;

    /**
     * 最后修改日期
     */
    protected LocalDateTime lastModifiedTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseData<?> baseData = (BaseData<?>) o;
        return getId().equals(baseData.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
