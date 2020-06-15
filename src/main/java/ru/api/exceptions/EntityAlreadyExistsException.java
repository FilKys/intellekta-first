package ru.api.exceptions;

/**
 * Исключение выбрасывается при поторном создании сущности с заданным ключом
 */

import org.springframework.util.Assert;

public class EntityAlreadyExistsException extends BaseException {
    public EntityAlreadyExistsException(String message) {
        super(message);
    }

    public EntityAlreadyExistsException(String type, Object id) {
        this(formatMessage(type, id));
    }

    private static String formatMessage(String type, Object id) {
        Assert.hasText(type, "Тип не может быть пустым");
        Assert.notNull(id, "Индификатор не может быть null");
        Assert.hasText(id.toString(), "Индификатор не может быть пустым");
        return String.format("%s с ключом %s уже существует", type, id);
    }
}
