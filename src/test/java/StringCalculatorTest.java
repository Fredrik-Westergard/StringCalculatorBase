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

    @Test
    public void testTwoDigitStringReturnsSum(){
        assertEquals(3, new StringCalculator().add("1,2"));
    }

    @Test
    public void testUnknownNumberOfDigitsReturnSum(){
        assertEquals(91, new StringCalculator().add("1,2,3,4,5,6,7,8,9,10,11,12,13"));
    }

    @Test
    public void testUnknownNumberOfDigitsWithNewLineSeparatorAndCommaReturnsSum(){
        assertEquals(91, new StringCalculator().add("1\n2,3,4\n5,6,7,8,9\n10,11,12\n13"));
    }

    @Test
    public void testUnknownNumberOfDigitsWithDifferentSeparatorReturnsSum(){
        assertEquals(91, new StringCalculator().add(";\n1;2;3;4;5;6;7;8;9;10;11;12;13"));
    }
}
