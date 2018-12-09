package tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tasche_packen.model.SubjectItemAssignment;

import static org.junit.Assert.*;


public class GetRequiredItemsIntentHandlerTest {

    @Test
    public void getRequiredItemsIntentHandlerCanHandle() {
        //HandlerInput Mock
        GetRequiredItemsIntentHandler sut = new GetRequiredItemsIntentHandler(new SubjectItemAssignment());
        HandlerInput inputMock = HandlerTest.mockHandlerInputWithString("GetRequiredItemsIntent");
        final boolean have =  sut.canHandle(inputMock);
        boolean want = true;
        assertEquals(want, have);
    }



    @Test
    public void goodbyeIntentHandlerHandles() {
        //HandlerInput Mock
        GetRequiredItemsIntentHandler sut = new GetRequiredItemsIntentHandler(new SubjectItemAssignment());
        final HandlerInput inputMock = HandlerTest.mockHandlerInput();
        final Response response = sut.handle(inputMock).get();

        final String have = response.getOutputSpeech().toString();
        System.out.println(have);
        Assert.assertTrue(have.contains("Du brauchst heute"));
    }


}
