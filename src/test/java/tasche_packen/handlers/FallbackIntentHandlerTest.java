package tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class FallbackIntentHandlerTest {

    @Test
    public void aidHandlerCanHandle() {
        //HandlerInput Mock
        final FallbackIntentHandler sut = new FallbackIntentHandler();
        final HandlerInput inputMock = HandlerTest.mockHandlerInputWithIntentName("AMAZON.FallbackIntent");
        final boolean have =  sut.canHandle(inputMock);
        final boolean want = true;
        assertEquals(want, have);
    }



    @Test
    public void aidIntentHandlerHandles() {
        //HandlerInput Mock
        final FallbackIntentHandler sut = new FallbackIntentHandler();
        final HandlerInput inputMock = HandlerTest.mockHandlerInput();
        final Response response = sut.handle(inputMock).get();

        final String have = response.getOutputSpeech().toString();
        Assert.assertTrue(have.contains("Ich habe dich leider nicht verstanden"));
    }

}