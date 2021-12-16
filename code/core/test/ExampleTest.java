import jdk.jfr.Description;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static org.mockito.Mockito.when;

/** Shows how we set up tests in this project and how to use mockito */
@RunWith(PowerMockRunner.class)
@PrepareForTest(List.class)
public class ExampleTest extends TestCase {
    /*
     * How to name tests:
     * MethodName_StateUnderTest_ExpectedBehavior
     * example: isAdult_AgeLessThan18_False
     */

    @SuppressWarnings("rawtypes")
    List mockedList;

    public void setUp() {
        mockedList = Mockito.mock(List.class);
    }

    @Description("Example on how to use mockito")
    public void testGetPositiveParameter_true() {
        when(mockedList.get(0)).thenReturn("First");
        assertEquals("First", mockedList.get(0));
    }

    @SuppressWarnings("all")
    @Description("Example on how to use mockito")
    public void testGetNegativeParameter_false() {
        when(mockedList.get(-1)).thenReturn(false);
        assertFalse((Boolean) mockedList.get(-1));
    }
}
