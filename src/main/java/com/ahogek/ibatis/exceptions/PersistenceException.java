package com.ahogek.ibatis.exceptions;

import java.io.Serial;

/**
 * @author AhogeK ahogek@gmail.com
 * @since 2023-05-17 14:50:04
 */
public class PersistenceException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -872249568193098410L;

    public PersistenceException() {
    }

    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistenceException(Throwable cause) {
        super(cause);
    }
}
