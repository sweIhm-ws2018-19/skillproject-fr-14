package tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


public class AidIntentHandlerTest {
    @Test
    public void aidHandlerCanHandle() {
        //HandlerInput Mock
        final AidIntentHandler sut = new AidIntentHandler();
        final HandlerInput inputMock = HandlerTest.mockHandlerInputWithIntentName("AidIntent");
        final boolean have =  sut.canHandle(inputMock);
        final boolean want = true;
        assertEquals(want, have);
    }



    @Test
    public void aidIntentHandlerHandles() {
        //HandlerInput Mock
        final AidIntentHandler sut = new AidIntentHandler();
        final HandlerInput inputMock = HandlerTest.mockHandlerInput();
        final Response response = sut.handle(inputMock).get();

        final String have = response.getOutputSpeech().toString();
        Assert.assertTrue(have.contains("Willst Du jetzt Deine Tasche packen?"));
    }



}
