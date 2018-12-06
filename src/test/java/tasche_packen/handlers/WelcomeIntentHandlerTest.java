package tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import org.junit.Test;
import tasche_packen.model.SubjectItemAssignment;

import static org.junit.Assert.assertEquals;

public class WelcomeIntentHandlerTest {

    @Test
    public void welcomeIntentCanHandle() {
        //HandlerInput Mock
        WelcomeIntentHandler sut = new WelcomeIntentHandler(new SubjectItemAssignment());
        HandlerInput inputMock = HandlerTest.mockHandlerInputWithString("WelcomeIntent");
        final boolean have =  sut.canHandle(inputMock);
        boolean want = true;
        assertEquals(want, have);
    }
}
