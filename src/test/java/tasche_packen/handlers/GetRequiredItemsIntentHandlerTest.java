package tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import org.junit.Assert;
import org.junit.Test;
import tasche_packen.model.SubjectItemAssignment;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;


public class GetRequiredItemsIntentHandlerTest {

    @Test
    public void getRequiredItemsIntentHandlerCanHandle() {
        //HandlerInput Mock
        GetRequiredItemsIntentHandler sut = new GetRequiredItemsIntentHandler();
        Slot slot = HandlerTest.mockSlot("Answer", "nein");
        Map<String, Slot> slots = new HashMap<>();
        slots.put("Answer", slot);
        RemoveNotVisitedSubjectsIntentHandler.setRemoveNotVisitedSubjectsIntentHandlerFinished(true);
        final HandlerInput inputMock = HandlerTest.mockHandlerInputWithSlot("GetRequiredItemsIntent", slots);
        final boolean have =  sut.canHandle(inputMock);
        boolean want = true;
        assertEquals(want, have);
    }



/*
    @Test
    public void getRequiredItemsIntentHandlerHandles() {
        //HandlerInput Mock
        GetRequiredItemsIntentHandler sut = new GetRequiredItemsIntentHandler(new SubjectItemAssignment());
        Slot slot = HandlerTest.mockSlot("Answer", "nein");
        Map<String, Slot> slots = new HashMap<>();
        slots.put("Answer", slot);
        final HandlerInput inputMock = HandlerTest.mockHandlerInputWithSlot("GetRequiredItemsIntent", slots);
        final Response response = sut.handle(inputMock).get();

        final String have = response.getOutputSpeech().toString();
        System.out.println(have);
        Assert.assertTrue(have.contains("Du brauchst heute"));
    }
*/


}
