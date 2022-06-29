import controllers.LibraryController;
import org.junit.jupiter.api.Test;
import validators.Validator;
import validators.rules.EmailRule;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class Step3Test {

    @Test
    public void testUserEmailValidation() {
        Validator validator = new Validator.Builder()
                .addRule(new EmailRule())
                .build();
        assertTrue(validator.validate("test@test.com"));
        assertTrue(validator.validate("woitrammeizoitti-1663@yopmail.com"));
        assertTrue(validator.validate("f%+5803@yopmail.com"));

        assertFalse(validator.validate("testtestcom"));
        assertFalse(validator.validate("testtest.com"));
        assertFalse(validator.validate("test@testcom"));
        assertFalse(validator.validate("te'st@test.com"));
        assertFalse(validator.validate("test@t&%t.com"));
    }

}
