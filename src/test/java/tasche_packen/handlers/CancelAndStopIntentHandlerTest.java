package tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


public class CancelAndStopIntentHandlerTest {
    @Test
    public void cancelAndStopIntentHandlerCanHandle() {
        //HandlerInput Mock
        final CancelAndStopIntentHandler sut = new CancelAndStopIntentHandler();
        final HandlerInput inputMock = HandlerTest.mockHandlerInputWithIntentName("AMAZON.StopIntent");
        final boolean have =  sut.canHandle(inputMock);
        final boolean want = true;
        assertEquals(want, have);
    }



    @Test
    public void cancelAndStopIntentHandlerHandles() {
        //HandlerInput Mock
        final CancelAndStopIntentHandler sut = new CancelAndStopIntentHandler();
        final HandlerInput inputMock = HandlerTest.mockHandlerInput();
        final Response response = sut.handle(inputMock).get();

        final String have = response.getOutputSpeech().toString();
        Assert.assertTrue(have.contains("Schade, dass du den Skill verlassen willst. Hoffentlich bis bald."));
    }



}
