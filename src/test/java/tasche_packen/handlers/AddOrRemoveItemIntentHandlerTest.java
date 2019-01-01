package tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import org.junit.Test;

import static org.junit.Assert.*;
import static tasche_packen.handlers.GetItemToChangeIntentHandler.getGetItemToChangeFin;
import static tasche_packen.handlers.GetItemToChangeIntentHandler.setGetItemToChangeFin;

public class AddOrRemoveItemIntentHandlerTest {

    @Test
    public void addOrRemoveItemCanHandle() {
        AddOrRemoveItemIntentHandler sut = new AddOrRemoveItemIntentHandler();
        setGetItemToChangeFin(true);
        final HandlerInput inputMock = HandlerTest.mockHandlerInput();
        final boolean have =  sut.canHandle(inputMock);
        boolean want = true;
        assertEquals(want, have);
    }


}