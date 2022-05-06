/**
 * Copyright (c) 2020 All rights reserved.
 *
 * 版权所有，侵权必究！
 */

package tech.icoding.commons.log;

import lombok.Data;

import java.io.Serializable;

/**
 * Log基类
 *
 * @author Mark cxwm
 * @since 1.0.0
 */
@Data
public abstract class BaseLog implements Serializable {
    /**
     * 日志类型
     */
    private Integer type;

}