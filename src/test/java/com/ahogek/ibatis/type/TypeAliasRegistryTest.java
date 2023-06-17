package com.ahogek.ibatis.type;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void shouldBeAbleToRegisterSameAliasWithSameTypeAgain() {
        TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();
        assertDoesNotThrow(() -> {
            typeAliasRegistry.registerAlias("String", String.class);
            typeAliasRegistry.registerAlias("string", String.class);
        });
    }

    @Test
    void shouldNotBeAbleToRegisterSameAliasWithDifferentType() {
        TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();
        assertThrows(TypeException.class, () -> typeAliasRegistry.registerAlias("string", BigDecimal.class));
    }

    @Test
    void shouldBeAbleToRegisterAliasWithNullType() {
        TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();
        typeAliasRegistry.registerAlias("foo", (Class<?>) null);
        assertNull(typeAliasRegistry.resolveAlias("foo"));
    }
}
