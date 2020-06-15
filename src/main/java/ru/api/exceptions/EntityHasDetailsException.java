package ru.api.exceptions;

import org.springframework.util.Assert;

/**
 * Исключение выбрасывается при вызове удаления сущности, на которую усть ссылка у других сущностей
 */

public class EntityHasDetailsException extends BaseException {
    public EntityHasDetailsException(String message) {
        super(message);
    }

    public EntityHasDetailsException(String type, Object id) {
        this(formatMessage(type, id));
    }

    private static String formatMessage(String type, Object id) {
        Assert.hasText(type, "Тип не может быть пустым");
        Assert.notNull(id, "Индификатор не может быть null");
        Assert.hasText(id.toString(), "Индификатор не может быть пустым");
        return String.format("%s сылается на управляемый объект с идентификатором %s", type, id);
    }
}
