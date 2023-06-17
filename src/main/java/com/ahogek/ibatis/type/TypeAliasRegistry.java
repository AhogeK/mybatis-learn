package com.ahogek.ibatis.type;

import com.ahogek.ibatis.io.Resources;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.*;

/**
 * 类型别名注册类
 *
 * @author AhogeK ahogek@gmail.com
 * @since 2023-06-18 02:04:46
 */
public class TypeAliasRegistry {

    private final Map<String, Class<?>> typeAliases = new HashMap<>();

    public TypeAliasRegistry() {
        registerAlias("string", String.class);

        registerAlias("byte", Byte.class);
        registerAlias("char", Character.class);
        registerAlias("character", Character.class);
        registerAlias("long", Long.class);
        registerAlias("short", Short.class);
        registerAlias("int", Integer.class);
        registerAlias("integer", Integer.class);
        registerAlias("double", Double.class);
        registerAlias("float", Float.class);
        registerAlias("boolean", Boolean.class);

        registerAlias("byte[]", Byte[].class);
        registerAlias("char[]", Character[].class);
        registerAlias("character[]", Character[].class);
        registerAlias("long[]", Long[].class);
        registerAlias("short[]", Short[].class);
        registerAlias("int[]", Integer[].class);
        registerAlias("integer[]", Integer[].class);
        registerAlias("double[]", Double[].class);
        registerAlias("float[]", Float[].class);
        registerAlias("boolean[]", Boolean[].class);

        registerAlias("_byte", byte.class);
        registerAlias("_char", char.class);
        registerAlias("_character", char.class);
        registerAlias("_long", long.class);
        registerAlias("_short", short.class);
        registerAlias("_int", int.class);
        registerAlias("_integer", int.class);
        registerAlias("_double", double.class);
        registerAlias("_float", float.class);
        registerAlias("_boolean", boolean.class);

        registerAlias("_byte[]", byte[].class);
        registerAlias("_char[]", char[].class);
        registerAlias("_character[]", char[].class);
        registerAlias("_long[]", long[].class);
        registerAlias("_short[]", short[].class);
        registerAlias("_int[]", int[].class);
        registerAlias("_integer[]", int[].class);
        registerAlias("_double[]", double[].class);
        registerAlias("_float[]", float[].class);
        registerAlias("_boolean[]", boolean[].class);

        registerAlias("date", Date.class);
        registerAlias("decimal", BigDecimal.class);
        registerAlias("bigdecimal", BigDecimal.class);
        registerAlias("biginteger", BigInteger.class);
        registerAlias("object", Object.class);

        registerAlias("date[]", Date[].class);
        registerAlias("decimal[]", BigDecimal[].class);
        registerAlias("bigdecimal[]", BigDecimal[].class);
        registerAlias("biginteger[]", BigInteger[].class);
        registerAlias("object[]", Object[].class);

        registerAlias("map", Map.class);
        registerAlias("hashmap", HashMap.class);
        registerAlias("list", List.class);
        registerAlias("arraylist", ArrayList.class);
        registerAlias("collection", Collection.class);
        registerAlias("iterator", Iterator.class);

        registerAlias("ResultSet", ResultSet.class);
    }

    /**
     * 根据提供的字符串参数，解析并返回相应的类对象 Class<T>。此字符串可以是已经注册的类型别名，也可以是全类名。
     * 首先，方法会检查是否存在与输入参数对应的类型别名，如果存在，则返回对应的类对象；
     * 如果不存在，那么尝试将输入字符串解析为类对象。
     * 如果解析失败，将会抛出一个 TypeException 异常。
     *
     * @param string 类别名或全类名
     * @return Class<T> 对应的类对象
     * @throws TypeException 如果无法解析输入的字符串为一个类对象，将抛出此异常
     */
    @SuppressWarnings("unchecked")
    public <T> Class<T> resolveAlias(String string) {
        try {
            if (string == null) {
                return null;
            }
            String key = string.toLowerCase(Locale.ENGLISH);
            Class<T> value;
            if (typeAliases.containsKey(key)) {
                value = (Class<T>) typeAliases.get(key);
            } else {
                value = (Class<T>) Resources.classForName(string);
            }
            return value;
        } catch (ClassNotFoundException e) {
            throw new TypeException("无法解析类型别名 '" + string + "'。原因：" + e, e);
        }
    }

    /**
     * 为特定类别注册别名。
     * 如果给定的别名已在使用中且与另一个类别关联，将抛出TypeException。这是为了避免不同的类别试图使用同一别名而引发的冲突。
     * 请注意，别名将使用英语区域设置转换为小写，因为别名注册对大小写不敏感。
     *
     * @param alias 想要与类别关联的别名。这应是可以用来识别类别的唯一名称。
     * @param value 想要与别名关联的类别。这应是完整的类名。
     * @throws TypeException 如果别名为null，或者别名已经与另一个类别关联。
     */
    public void registerAlias(String alias, Class<?> value) {
        if (alias == null) {
            throw new TypeException("参数别名不能为空。");
        }
        String key = alias.toLowerCase(Locale.ENGLISH);
        if (typeAliases.containsKey(key) && typeAliases.get(key) != null && !typeAliases.get(key).equals(value)) {
            throw new TypeException("别名 '" + alias + "' 已经映射到值 '" + typeAliases.get(key).getName() + "'。");
        }
        typeAliases.put(key, value);
    }

    /**
     * 为特定类别注册别名。
     * 如果给定的类名没有找到，将抛出TypeException。这是因为该方法试图根据给定的类名找到对应的类。
     * 注意，这个方法将尝试加载类，然后再为加载的类注册别名。
     *
     * @param alias 想要与类别关联的别名。这应是可以用来识别类别的唯一名称。
     * @param value 想要与别名关联的类别名称。这应是完整的类名。
     * @throws TypeException 如果别名为null，或者别名已经与另一个类别关联，或者给定的类名无法找到对应的类。
     */
    public void registerAlias(String alias, String value) {
        try {
            registerAlias(alias, Resources.classForName(value));
        } catch (ClassNotFoundException e) {
            throw new TypeException("注册类型别名 " + alias + " 失败，对应值为 " + value + "。原因：" + e, e);
        }
    }
}
