import models.Book;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class Step1Test {

    @Test
    public void testStateAvailability() throws NoSuchFieldException {
        Field field = Book.class.getDeclaredField("state");
        assertNotNull(field);
    }
}
