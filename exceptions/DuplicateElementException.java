// DuplicateEntryException.java
package exceptions;

// Extends RuntimeException instead of Exception since that's the
// convention set by NoSuchElementException.
public class DuplicateElementException extends RuntimeException {
    public DuplicateElementException() {
    }

    public DuplicateElementException(String message) {
        super(message);
    }

    public DuplicateElementException(Throwable cause) {
        super(cause);
    }

    public DuplicateElementException(String message, Throwable cause) {
        super(message, cause);
    }
}
