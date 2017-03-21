import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;

/**
 * Slow unit tests...not really but the idea is to verify how to split unit tests using maven pom.xml
 * directives. These tests are invoked by running maven with the goal set to 'test -Pslow-tests'
 * @author markpower
 *
 */
public class StringCalculatorSlowTest {

    // these are actually fast tests also but we're just testing the framework to split unit tests
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
    
   
    
    
}
