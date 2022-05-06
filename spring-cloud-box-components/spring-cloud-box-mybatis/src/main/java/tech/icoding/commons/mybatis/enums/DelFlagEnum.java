/**
 * Copyright (c) 2020 All rights reserved.
 *
 * 版权所有，侵权必究！
 */

package tech.icoding.commons.mybatis.enums;

/**
 * 删除标识枚举类
 *
 * @author Mark cxwm
 * @since 1.0.0
 */
public enum DelFlagEnum {
    NORMAL(0),
    DEL(1);

    private int value;

    DelFlagEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
