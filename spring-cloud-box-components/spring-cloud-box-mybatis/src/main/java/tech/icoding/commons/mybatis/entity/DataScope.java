/**
 * Copyright (c) 2020 All rights reserved.
 *
 * 版权所有，侵权必究！
 */

package tech.icoding.commons.mybatis.entity;

/**
 * 数据范围
 *
 * @author Mark cxwm
 * @since 1.0.0
 */
public class DataScope {
    private String sqlFilter;

    public DataScope(String sqlFilter) {
        this.sqlFilter = sqlFilter;
    }

    public String getSqlFilter() {
        return sqlFilter;
    }

    public void setSqlFilter(String sqlFilter) {
        this.sqlFilter = sqlFilter;
    }

    @Override
    public String toString() {
        return this.sqlFilter;
    }
}