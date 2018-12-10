package tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoodbyeIntentHandlerTest {

    @Test
    public void goodbyeIntentHandlerCanHandle() {
        //HandlerInput Mock
        GoodbyeIntentHandler sut = new GoodbyeIntentHandler();
        HandlerInput inputMock = HandlerTest.mockHandlerInputWithIntentName("GoodbyeIntent");
        final boolean have =  sut.canHandle(inputMock);
        boolean want = true;
        assertEquals(want, have);
    }



    @Test
    public void goodbyeIntentHandlerHandles() {
        //HandlerInput Mock
        GoodbyeIntentHandler sut = new GoodbyeIntentHandler();
        final HandlerInput inputMock = HandlerTest.mockHandlerInput();
        final Response response = sut.handle(inputMock).get();

        final String have = response.getOutputSpeech().toString();
        System.out.println(have);
        Assert.assertTrue(have.contains("Auf Wiedersehen")|| have.contains("Bis Morgen") || have.contains("Machs gut")|| have.contains("Ich wuensche dir einen schoenen Tag"));
    }



}
