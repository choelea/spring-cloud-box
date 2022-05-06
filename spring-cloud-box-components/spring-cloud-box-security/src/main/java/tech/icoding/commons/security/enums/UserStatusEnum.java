/**
 * Copyright (c) 2020 All rights reserved.
 *
 * 版权所有，侵权必究！
 */

package tech.icoding.commons.security.enums;

/**
 * 用户状态
 *
 * @author Mark cxwm
 * @since 1.0.0
 */
public enum UserStatusEnum {
    DISABLE(0),
    ENABLED(1);

    private int value;

    UserStatusEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
