// DuplicateEntryException.java
package exceptions;

// Extends RuntimeException instead of Exception since that's the
// convention set by NoSuchElementException.
public class DuplicateKeyException extends RuntimeException {
    public DuplicateKeyException() {
    }

    public DuplicateKeyException(String message) {
        super(message);
    }

    public DuplicateKeyException(Throwable cause) {
        super(cause);
    }

    public DuplicateKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}
