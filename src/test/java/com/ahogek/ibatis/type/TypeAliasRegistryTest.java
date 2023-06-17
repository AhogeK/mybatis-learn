package com.ahogek.ibatis.type;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 类型别名注册单元测试
 *
 * @author AhogeK ahogek@gmail.com
 * @since 2023-06-18 02:13:41
 */
class TypeAliasRegistryTest {

    @Test
    void shouldRegisterAndResolveTypeAlias() {
        TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

        typeAliasRegistry.registerAlias("rich", "com.ahogek.ibatis.domain.misc.RichType");

        assertEquals("com.ahogek.ibatis.domain.misc.RichType", typeAliasRegistry.resolveAlias("rich").getName());
    }

    @Test
    void shouldFetchArrayType() {
        TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();
        assertEquals(Byte[].class, typeAliasRegistry.resolveAlias("byte[]"));
    }
}
