import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTests {

    private StringCalculator stringCalc;

    @Before
    public void setup() {
        stringCalc = new StringCalculator();
    }

    @Test
    public void testAdd_emptyString_return0() {
        final int result = stringCalc.add("");
        Assert.assertEquals(0, result);
    }

    @Test
    public void testAdd_oneNumber_returnNumber() {
        final int result = stringCalc.add("1");
        Assert.assertEquals(1, result);
    }

    @Test
    public void testAdd_twoNumbers_returnSum() {
        final int result = stringCalc.add("1,2");
        Assert.assertEquals(3, result);
    }

    @Test
    public void testAdd_manyNumbers_returnSum() {
        final int result = stringCalc.add("1,2,3,4,5,6,7,8,9,10");
        Assert.assertEquals(55, result);
    }

    @Test
    public void testAdd_manyNumbersTwoDelimeters_returnSum() {
        final int result = stringCalc.add("1\n2,3,4,5,6\n7,8,9,10");
        Assert.assertEquals(55, result);
    }

    @Test
    public void testAdd_manyNumbersCustomDelimeters_returnSum() {
        final int result = stringCalc.add("//;\n1;2;3;4;5;6;7;8;9;10");
        Assert.assertEquals(55, result);
    }

    @Test
    public void testAdd_oneNegativeNumber_catchException() {
        try {
            final int result = stringCalc.add("-1");
            Assert.fail("No exception thrown");
        } catch (final NegativesFoundException e) {
            Assert.assertEquals("negatives not allowed", e.getMessage());
        } catch (final Exception e) {
            Assert.fail("Wrong exception thrown");
        }
    }

    @Test
    public void testAdd_manyNegativeNumbers_catchException() {
        try {
            final int result = stringCalc.add("//;\n1;2;-3;4;5;6;7;-8;9;10");
            Assert.fail("No exception thrown");
        } catch (final NegativesFoundException e) {
            Assert.assertEquals("negatives not allowed: -3, -8", e.getMessage());
        } catch (final Exception e) {
            Assert.fail("Wrong exception thrown");
        }
    }

    @Test
    public void testGetCalledCount_manyCalls_returnCount() {
        stringCalc.add("//;\n1;2;3;4;5;6;7;8;9;10");
        stringCalc.add("//;\n1;2;3;4;5;6;7;8;9;10");
        stringCalc.add("//;\n1;2;3;4;5;6;7;8;9;10");

        final int count = stringCalc.getCalledCount();
        Assert.assertEquals(3, count);
    }

    @Test
    public void testAdd_ignoreOver1000_returnSum() {
        final int result = stringCalc.add("//;\n1;2;3;4;5;6;7;8;9;1001");
        Assert.assertEquals(45, result);

        final int count = stringCalc.getCalledCount();
        Assert.assertEquals(1, count);
    }

    @Test
    public void testAdd_multiCharDelimiter_returnSum() {
        final int result = stringCalc.add("//[***]\n1***2***3***4***1001");
        Assert.assertEquals(10, result);

        final int count = stringCalc.getCalledCount();
        Assert.assertEquals(1, count);
    }

    @Test
    public void testAdd_manyMultiCharDelimiter_returnSum() {
        final int result = stringCalc.add("//[***][%][$$$]\n1%2***3%4$$$1001");
        Assert.assertEquals(10, result);

        final int count = stringCalc.getCalledCount();
        Assert.assertEquals(1, count);
    }
}
