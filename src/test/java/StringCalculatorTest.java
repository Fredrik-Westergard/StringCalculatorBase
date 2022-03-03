import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

    @Test
    public void testEmptyStringReturnsZero(){
        try {
            assertEquals(0, new StringCalculator().add(""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSingleDigitStringReturnsTen(){
        try {
            assertEquals(10, new StringCalculator().add("10"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTwoDigitStringReturnsSum(){
        try {
            assertEquals(3, new StringCalculator().add("1,2"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUnknownNumberOfDigitsReturnSum(){
        try {
            assertEquals(91, new StringCalculator().add("1,2,3,4,5,6,7,8,9,10,11,12,13"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUnknownNumberOfDigitsWithNewLineSeparatorAndCommaReturnsSum(){
        try {
            assertEquals(91, new StringCalculator().add("1\n2,3,4\n5,6,7,8,9\n10,11,12\n13"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUnknownNumberOfDigitsWithDifferentSeparatorReturnsSum(){
        try {
            assertEquals(91, new StringCalculator().add("//;\n1;2;3;4;5;6;7;8;9;10;11;12;13"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExceptionThrownForNegativeNumbers(){
        Exception exception = assertThrows(Exception.class, () -> new StringCalculator().add("//;\n1;2;3;4;5;6;-7;8;9;10;11;12;13"));

        String expected = "Negatives not allowed -7";

        assertTrue(exception.getMessage().contains(expected));
    }
}
