package tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Slot;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static tasche_packen.handlers.AddOrRemoveItemIntentHandler.setAddOrRemoveItemFin;

public class GetSubjectToChangeIntentHandlerTest {

    @Test
    public void getSubjectToChangeCanHandleWithYes() {
        GetSubjectToChangeIntentHandler sut = new GetSubjectToChangeIntentHandler();
        Slot slot = HandlerTest.mockSlot("Answer", "ja");
        Map<String, Slot> slots = new HashMap<>();
        slots.put("Answer", slot);
        setAddOrRemoveItemFin(true);
        final HandlerInput inputMock = HandlerTest.mockHandlerInputWithSlot("GetSubjectToChangeIntent", slots);
        final boolean have =  sut.canHandle(inputMock);
        boolean want = true;
        assertEquals(want, have);
    }

    @Test
    public void getSubjectToChangeCanHandleDirectCall() {
        GetSubjectToChangeIntentHandler sut = new GetSubjectToChangeIntentHandler();
        HandlerInput inputMock = HandlerTest.mockHandlerInputWithIntentName("GetSubjectToChangeIntent");
        final boolean have =  sut.canHandle(inputMock);
        boolean want = true;
        assertEquals(want, have);
    }

}