import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StringCalculatorTest {

    private Logger mockLogger;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    public void loggerSetup(){
        this.mockLogger = mock(Logger.class);
    }

    @BeforeEach
    public void setOut(){
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    public void setOriginalInOut (){
        System.setOut(originalOut);
        System.setIn(originalIn);
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
        ByteArrayInputStream in = new ByteArrayInputStream("\n".getBytes());
        System.setIn(in);
        Main.main(null);
        assertEquals("Welcome to String Calculator\nExample usage: scalc '1,2,3'\n", out.toString());
    }

    @Test
    public void testInputOutput(){
        ByteArrayInputStream in = new ByteArrayInputStream("scalc '1,2,3'\n\n".getBytes());
        System.setIn(in);
        Main.main(null);
        assertEquals("Welcome to String Calculator\nExample usage: scalc '1,2,3'\nThe result is 6\n", out.toString());
    }

    @Test
    public void testMultipleInputs(){
        ByteArrayInputStream in = new ByteArrayInputStream("scalc '1,2,3'\nscalc '4,5,6'\nscalc '7,8,9'\n\n".getBytes());
        System.setIn(in);
        Main.main(null);
        assertEquals("Welcome to String Calculator\nExample usage: scalc '1,2,3'\nThe result is 6\nThe result is 15\nThe result is 24\n", out.toString());
    }
}
