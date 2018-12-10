package tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import org.junit.Assert;
import org.junit.Test;
import tasche_packen.model.SubjectItemAssignment;

import static org.junit.Assert.assertEquals;

public class WelcomeIntentHandlerTest {

    @Test
    public void welcomeIntentHandlerCanHandle() {
        //HandlerInput Mock
        WelcomeIntentHandler sut = new WelcomeIntentHandler(new SubjectItemAssignment());
        HandlerInput inputMock = HandlerTest.mockHandlerInputWithIntentName("WelcomeIntent");
        final boolean have =  sut.canHandle(inputMock);
        boolean want = true;
        assertEquals(want, have);
    }



    @Test
    public void welcomeIntentHandlerHandles() {
        //HandlerInput Mock
        WelcomeIntentHandler sut = new WelcomeIntentHandler(new SubjectItemAssignment());
        final HandlerInput inputMock = HandlerTest.mockHandlerInput();
        final Response response = sut.handle(inputMock).get();
        final String have = response.toString();
        Assert.assertTrue(have.contains( " Willst du heute alle Faecher besuchen ?"));
    }










}
