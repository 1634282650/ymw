package dxhualuo.reflect.exception;

public class NotAnArray extends RuntimeException {
    public NotAnArray(Class<?> message) {
        super(message.getName() + "Not an array!");
    }
}
