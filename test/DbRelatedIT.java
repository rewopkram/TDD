
import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests using Maven Failsafe plugin to split plain unit tests from Integration Tests.
 * This class should include DB integration test
 * @author markpower
 *
 */
public class DbRelatedIT {

    // Mockito tests
    
    // mock the DB connection so we can test the framework without actually running up a DB
    @Test
    public void testDbConnection() {
        DBConnection dmock = Mockito.mock(DBConnection.class);
        DBConnection c = dmock.getConnection();
        assertNotNull("must get a connection", c);
    }
}
