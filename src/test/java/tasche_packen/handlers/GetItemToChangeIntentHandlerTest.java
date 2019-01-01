package tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import org.junit.Test;

import static org.junit.Assert.*;
import static tasche_packen.handlers.GetSubjectToChangeIntentHandler.setGetSubjectToChangeFin;

public class GetItemToChangeIntentHandlerTest {

    @Test
    public void getItemToChangeIntentCanHandle() {
        GetItemToChangeIntentHandler sut = new GetItemToChangeIntentHandler();
        setGetSubjectToChangeFin(true);
        final HandlerInput inputMock = HandlerTest.mockHandlerInput();
        final boolean have =  sut.canHandle(inputMock);
        boolean want = true;
        assertEquals(want, have);
    }

}