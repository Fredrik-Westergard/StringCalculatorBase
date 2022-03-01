import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {

    @Test
    public void testEmptyStringReturnsZero(){
        assertEquals(0, new StringCalculator().add(""));
    }

    @Test
    public void testSingleDigitStringReturnsTen(){
        assertEquals(10, new StringCalculator().add("10"));
    }
}
