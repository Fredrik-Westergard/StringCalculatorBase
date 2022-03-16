import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StringCalculatorTest {

    private Logger mockLogger;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void loggerSetup(){
        this.mockLogger = mock(Logger.class);
    }

    @BeforeEach
    public void setOut(){
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    public void setOriginalOut (){
        System.setOut(originalOut);
    }

    @Test
    public void testEmptyStringReturnsZero(){
        try {
            assertEquals(0, new StringCalculator(mockLogger).add(""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSingleDigitStringReturnsTen(){
        try {
            assertEquals(10, new StringCalculator(mockLogger).add("10"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTwoDigitStringReturnsSum(){
        try {
            assertEquals(3, new StringCalculator(mockLogger).add("1,2"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUnknownNumberOfDigitsReturnSum(){
        try {
            assertEquals(91, new StringCalculator(mockLogger).add("1,2,3,4,5,6,7,8,9,10,11,12,13"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUnknownNumberOfDigitsWithNewLineSeparatorAndCommaReturnsSum(){
        try {
            assertEquals(91, new StringCalculator(mockLogger).add("1\n2,3,4\n5,6,7,8,9\n10,11,12\n13"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUnknownNumberOfDigitsWithDifferentSeparatorReturnsSum(){
        try {
            assertEquals(91, new StringCalculator(mockLogger).add("//;\n1;2;3;4;5;6;7;8;9;10;11;12;13"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExceptionThrownForNegativeNumbers(){
        Exception exception = assertThrows(Exception.class, () -> new StringCalculator(mockLogger).add("//;\n1;2;3;4;5;6;-7;8;9;10;11;12;13"));

        String expected = "Negatives not allowed -7";

        assertTrue(exception.getMessage().contains(expected));
    }

    @Test
    public void testLogOver1000(){
        try{
            new StringCalculator(mockLogger).add("1001,1000");
        }catch(Exception e){
            e.printStackTrace();
        }
        verify(mockLogger, times(1)).log(1001);
    }

    @Test
    public void testWelcomeMessagePrint(){
        Main.main(null);
        assertEquals("Welcome to String Calculator\nExample usage: scalc '1,2,3'\n", out.toString());
    }
}
