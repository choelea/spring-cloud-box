/**
 * Copyright (c) 2020 All rights reserved.
 *
 * 版权所有，侵权必究！
 */

package tech.icoding.vo;
import java.io.Serializable;

/**
 * 响应数据
 *
 * @author Mark cxwm
 * @since 1.0.0
 */
public class ResultVO<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 编码：0表示成功，其他值表示失败
     */
    private int code = 0;
    /**
     * 消息内容
     */
    private String msg = "success";
    /**
     * 响应数据
     */
    private T data;

    public ResultVO<T> ok(T data) {
        this.setData(data);
        return this;
    }

    public boolean success(){
        return code == 0 ? true : false;
    }



    public ResultVO<T> error(int code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
