package tasche_packen.handlers;


import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tasche_packen.model.SubjectItemAssignment;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;


public class GetNotVisitedSubjectIntentHandlerTest {

    @Test
    public void getNotVisitedSubjectIntentHandlerCanHandle() {
        GetNotVisitedSubjectIntentHandler sut = new GetNotVisitedSubjectIntentHandler();
        Slot slot = HandlerTest.mockSlot("Answer", "nein");
        Map<String, Slot> slots = new HashMap<>();
        slots.put("Answer", slot);
        WelcomeIntentHandler.setWelcomeFinished(true);
        final HandlerInput inputMock = HandlerTest.mockHandlerInputWithSlot("GetNotVisitedSubjectIntent", slots);
        final boolean have =  sut.canHandle(inputMock);
        boolean want = true;
        assertEquals(want, have);
    }

    @Test
    public void getNotVisitedSubjectIntentHandlerHandles() {
        //HandlerInput Mock
        GetNotVisitedSubjectIntentHandler sut = new GetNotVisitedSubjectIntentHandler();
        final HandlerInput inputMock = HandlerTest.mockHandlerInput();
        final Response response = sut.handle(inputMock).get();
        final String have = response.toString();
        Assert.assertTrue(have.contains( "Welches Fach willst du nicht besuchen?"));
    }
}
