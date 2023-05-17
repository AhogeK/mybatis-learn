package com.ahogek.ibatis.logging;

import com.ahogek.ibatis.exceptions.PersistenceException;

/**
 * @author AhogeK ahogek@gmail.com
 * @since 2023-05-17 14:49:19
 */
public class LogException extends PersistenceException {

    private static final long serialVersionUID = 6581227420929504274L;

    public LogException() {
    }

    public LogException(String message) {
        super(message);
    }

    public LogException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogException(Throwable cause) {
        super(cause);
    }
}
