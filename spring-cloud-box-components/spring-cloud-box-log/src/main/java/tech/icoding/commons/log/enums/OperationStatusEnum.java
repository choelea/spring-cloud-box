/**
 * Copyright (c) 2020 All rights reserved.
 *
 * 版权所有，侵权必究！
 */

package tech.icoding.commons.log.enums;

/**
 * 操作状态枚举
 *
 * @author Mark cxwm
 * @since 1.0.0
 */
public enum OperationStatusEnum {
    /**
     * 失败
     */
    FAIL(0),
    /**
     * 成功
     */
    SUCCESS(1);

    private int value;

    OperationStatusEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}