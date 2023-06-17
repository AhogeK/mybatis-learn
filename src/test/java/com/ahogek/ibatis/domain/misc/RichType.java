package com.ahogek.ibatis.domain.misc;

import java.io.Serial;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试用类型
 *
 * @author AhogeK ahogek@gmail.com
 * @since 2023-06-18 02:18:20
 */
public class RichType {

    private RichType richType;

    private String richField;

    private String richProperty;

    private Map<Object, Object> richMap = new HashMap<>();

    private List<Object> richList = new ArrayList<>() {
        @Serial
        private static final long serialVersionUID = 1L;

        {
            add("bar");
        }
    };

    public RichType getRichType() {
        return richType;
    }

    public void setRichType(RichType richType) {
        this.richType = richType;
    }

    public String getRichProperty() {
        return richProperty;
    }

    public void setRichProperty(String richProperty) {
        this.richProperty = richProperty;
    }

    public List<Object> getRichList() {
        return richList;
    }

    public void setRichList(List<Object> richList) {
        this.richList = richList;
    }

    public Map<Object, Object> getRichMap() {
        return richMap;
    }

    public void setRichMap(Map<Object, Object> richMap) {
        this.richMap = richMap;
    }
}
