import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;

/**
 * Fast unit tests. Will be invoked by the default maven test directive in pom.xml
 * 
 * @author markpower
 *
 */
public class StringCalculatorFastTest {

    @Test
    public void testExists() {
        StringCalculator st = new StringCalculator();
        assertNotNull("must not be null", st);
    }
    
    @Test
    public void testAddNothing() {
        StringCalculator st = new StringCalculator();
        assertEquals("must return zero", 0, st.add(""));
    }
    
    @Test
    public void testAddSingle() {
        StringCalculator st = new StringCalculator();
        assertEquals("must return one", 1, st.add("1"));
    }
    
    @Test
    public void testAddDouble() {
        StringCalculator st = new StringCalculator();
        assertEquals("must return three", 3, st.add("1,2"));
    }
    
    @Test
    public void testAddMultiple() {
        StringCalculator st = new StringCalculator();
        assertEquals("must return 15", 15, st.add("1,2,3,4,5"));
    }
    
    @Test
    public void testMultiplyExists() {
        StringCalculator st = new StringCalculator();
        st.multiply("");
        assert(true);
    }
    
    @Test
    public void testMultiplySingleNumberReturnsSelf() {
        StringCalculator st = new StringCalculator();
        assertEquals("must return 1", 1, st.multiply("1"));
    }
    
    @Test
    public void testMultiplyTwoNumbersReturnsProduct() {
        StringCalculator st = new StringCalculator();
        assertEquals("must return 6", 6, st.multiply("2,3"));
    }
    
    @Test
    public void testMultiplyManyNumbersReturnsCorrectProduct() {
        StringCalculator st = new StringCalculator();
        assertEquals("must return 24", 24, st.multiply("2,3,4"));
    }
    
    @Test
    public void testMultiplyManyNumbersReturnsCorrectProduct2() {
        StringCalculator st = new StringCalculator();
        assertEquals("must return 120", 120, st.multiply("2,3,4,5"));
    }
    
    // Mockito tests
    // Although this works it is pointless because it doesn't test anything
    // TODO more RnD into Mockito to produce meaningful tests
    // e.g. a SUT (java business object) which depends on another resource (database)
    // write a unit test for the SUT that tests some feature and uses Mockito to mock the
    // behaviour of the other resource.
    
    @Test
    public void testAddMultipleMock() {
        StringCalculator stmock = Mockito.mock(StringCalculator.class);
        
        when(stmock.add("1,2,3,4,5,6")).thenReturn(21);
        int num = stmock.add("1,2,3,4,5,6");
        assertEquals(21, num);
    }
    
    @Test
    public void testAddMultipleMockBDDSemantics() {
        StringCalculator stmock = Mockito.mock(StringCalculator.class);
        
        // given
        given(stmock.add("1,2,3,4,5,6,7")).willReturn(28);
        
        // when
        int res = stmock.add("1,2,3,4,5,6,7");
        
        // then
        assertEquals(28, res);
    }
    
    @Test
    public void testShouldMatchSimpleArgument() {
        StringCalculator stmock = Mockito.mock(StringCalculator.class);
        
        given(stmock.add("1,2,3,4,5,6,7")).willReturn(28);
        
        int res = stmock.add("1,2,3,4,5,6,7");
        int resZipArgument = stmock.add("");
        
        assertEquals(res, 28);
        assertEquals(resZipArgument, 0);
    }
    
    @Test
    public void testMultipleCalls() {
        StringCalculator stmock = Mockito.mock(StringCalculator.class);
        
       stmock.add("");
       stmock.add("");
       stmock.add("1,2");
       
       // was method called twice
       verify(stmock, times(2)).add("");
       
       verify(stmock, atLeastOnce()).add("1,2");
       verify(stmock, never()).add("1,2,3");
       
       given(stmock.add("")).willReturn(28, 21, 22);
       assertEquals(stmock.add(""), 28);
       assertEquals(stmock.add(""), 21);
       assertEquals(stmock.add(""), 22);
    }

}
