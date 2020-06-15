package ru.api.exceptions;

/**
 * Исключение выбрасывается при вызове метода с неверными параметрами
 */

public class EntityIllegalArgumentException extends BaseException {
    public EntityIllegalArgumentException(String message) {
        super(message);
    }
}
