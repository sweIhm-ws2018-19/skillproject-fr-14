package tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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

    /*@Test
    public void getItemToChangeHandles(){
        GetItemToChangeIntentHandler sut = new GetItemToChangeIntentHandler();
        Slot slot = HandlerTest.mockSlot("Subject", "Netzwerke");
        Map<String, Slot> slots = new HashMap<>();
        slots.put("Subject", slot);
        final HandlerInput inputMock = HandlerTest.mockHandlerInputWithSlot("GetItemToChangeIntent", slots);
        final Response response = sut.handle(inputMock).get();
        final String have = response.toString();
        Assert.assertTrue(have.contains( "Welchen Gegenstand moechtest Du hinzufuegen oder entfernen?"));
    }*/

}