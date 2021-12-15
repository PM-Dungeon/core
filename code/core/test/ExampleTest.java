import jdk.jfr.Description;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

/** Shows how we set up tests in this project and how to use mockito */
@RunWith(PowerMockRunner.class)
@PrepareForTest(List.class)
public class ExampleTest {

    /*
     * How to name tests:
     * MethodName_StateUnderTest_ExpectedBehavior
     * example: isAdult_AgeLessThan18_False
     */

    @Mock List<Object> mockedList;

    @Test
    @Description("Example on how to use mockito")
    public void get_positiveParameter_True() {
        when(mockedList.get(0)).thenReturn("First");
        assertEquals("First", mockedList.get(0));
    }

    @Test
    @Description("Example on how to use mockito")
    public void get_negativeParameter_False() {
        when(mockedList.get(-1)).thenReturn(false);
        assertFalse((Boolean) mockedList.get(-1));
    }
}
