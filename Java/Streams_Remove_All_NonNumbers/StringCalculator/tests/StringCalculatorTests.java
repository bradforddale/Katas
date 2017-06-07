import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class StringCalculatorTests {

    StringCalculator sc;

    @Before
    public void setupStringCalculator() {
        sc = new StringCalculator();
    }

    @Test
    public void testSum_givenBlankString_returnZero() throws NegativesFoundException {
        assertEquals(0, sc.Add(""));
    }

    @Test
    public void testSum_givenStringWithOneNumber_returnNumber() throws NegativesFoundException {
        assertEquals(1, sc.Add("1"));
    }

    @Test
    public void testSum_givenStringWithTwoNumbersSeperatedByComma_returnSum() throws NegativesFoundException {
        assertEquals(22, sc.Add("1,21"));
    }

    @Test
    public void testSum_givenStringWithManyNumbersSeperatedByComma_returnSum() throws NegativesFoundException {
        assertEquals(30, sc.Add("1,21,3,4,1"));
    }

    @Test
    public void testSum_givenStringWithManyNumbersSeperatedByCommaAndNewLines_returnSum() throws NegativesFoundException{
        assertEquals(30, sc.Add("1,21\n3,4,1"));
    }

    @Test
    public void testSum_givenStringWithManyNumbersSeperatedBySpecifiedDelimiter_returnSum() throws NegativesFoundException {
        String testInput = "//;\n1;2";
        assertEquals(3, sc.Add(testInput));
    }

    @Test
    public void testSum_givenStringWithNegativeNumbersSeperatedBySpecifiedDelimiter_returnException() throws NegativesFoundException {
        try {
            String testInput = "//;\n1;2;-1;-2";
            sc.Add(testInput);
            fail();
        } catch (NegativesFoundException e) {
            assertEquals("The following negatives were found: -1, -2", e.getMessage());
        }
    }

    @Test
    public void testSum_givenStringWithANumberLargerThan1000SeperatedBySpecifiedDelimiter_returnSum() throws NegativesFoundException {
        String testInput = "//;\n1;2;1002";
        assertEquals(3, sc.Add(testInput));
    }

    @Test
    public void testSum_givenStringWithAMultiCharacterDelimiter_returnSum() throws NegativesFoundException {
        String testInput = "//[***]\\n1***2***3";
        assertEquals(6, sc.Add(testInput));
    }

    @Test
    public void testSum_givenStringWithAMultipleDelimiters_returnSum() throws NegativesFoundException {
        String testInput = "//[*][%]\\n1*2%3";
        assertEquals(6, sc.Add(testInput));
    }

    @Test
    public void testSum_givenStringWithAMultipleDelimitersWithMultipleCharacters_returnSum() throws NegativesFoundException {
        String testInput = "//[***][%]\n1***2%3";
        assertEquals(6, sc.Add(testInput));
    }

}
