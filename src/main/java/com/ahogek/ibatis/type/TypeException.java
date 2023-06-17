package com.ahogek.ibatis.type;

import com.ahogek.ibatis.exceptions.PersistenceException;

import java.io.Serial;

/**
 * 类型相关类异常类
 *
 * @author AhogeK ahogek@gmail.com
 * @since 2023-06-18 02:06:29
 */
public class TypeException extends PersistenceException {

    @Serial
    private static final long serialVersionUID = 4483027674353322208L;

    public TypeException() {
    }

    public TypeException(String message) {
        super(message);
    }

    public TypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public TypeException(Throwable cause) {
        super(cause);
    }
}
