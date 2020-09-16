import java.io.IOException;

/**
 * The interface Task.
 *
 * @param <T> the type parameter
 */
public interface Task<T> {
    /**
     * Run t.
     *
     * @return the t
     * @throws IOException the io exception
     */
    T run() throws IOException;
}