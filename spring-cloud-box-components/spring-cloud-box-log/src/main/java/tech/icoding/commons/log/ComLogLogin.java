/**
 * Copyright (c) 2020 All rights reserved.
 *
 * 版权所有，侵权必究！
 */

package tech.icoding.commons.log;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 登录日志
 *
 * @author Mark cxwm
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ComLogLogin extends BaseLog {
    private static final long serialVersionUID = 1L;
	/**
	 * 用户操作   0：用户登录   1：用户退出
	 */
	private Integer operation;
	/**
	 * 用户代理
	 */
	private String userAgent;
	/**
	 * 操作IP
	 */
	private String ip;
	/**
	 * 用户名
	 */
	private String creatorName;
	/**
	 * 创建者
	 */
	private Long creator;
	/**
	 * 创建时间
	 */
	private Date createDate;

}