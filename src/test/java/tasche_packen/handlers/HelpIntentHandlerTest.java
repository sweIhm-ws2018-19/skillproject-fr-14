package tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class HelpIntentHandlerTest {

    @Test
    public void helpCanHandle(){
        HelpIntentHandler sut = new HelpIntentHandler();
        HandlerInput inputMock = HandlerTest.mockHandlerInputWithIntentName("AMAZON.HelpIntent");
        final boolean have =  sut.canHandle(inputMock);
        boolean want = true;
        assertEquals(want, have);
    }

    @Test
    public void helpHandles() {
        HelpIntentHandler sut = new HelpIntentHandler();
        final HandlerInput inputMock = HandlerTest.mockHandlerInput();
        final Response response = sut.handle(inputMock).get();
        final String have = response.toString();
        Assert.assertTrue(have.contains( "Wenn du \"hilf mir\" sagst kann ich dir Auskunft zum Skill geben"));
    }


}