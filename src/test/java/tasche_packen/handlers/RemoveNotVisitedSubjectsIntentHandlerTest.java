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
import static tasche_packen.handlers.AddOrRemoveItemIntentHandler.setAddOrRemoveItemFin;
import static tasche_packen.handlers.GetItemToChangeIntentHandler.setGetItemToChangeFin;
import static tasche_packen.handlers.GetSubjectToChangeIntentHandler.setGetSubjectToChangeFin;


public class RemoveNotVisitedSubjectsIntentHandlerTest {


    @Test
    public void removeNotVisitedSubjectIntentHandlerCanHandle() {
        RemoveNotVisitedSubjectsIntentHandler sut = new RemoveNotVisitedSubjectsIntentHandler();
        setAddOrRemoveItemFin(false);
        setGetItemToChangeFin(false);
        setGetSubjectToChangeFin(false);
        final HandlerInput inputMock = HandlerTest.mockHandlerInputWithIntentName("RemoveNotVisitedSubjectIntent");
        final boolean have =  sut.canHandle(inputMock);
        boolean want = true;
        assertEquals(want, have);
    }

    @Test
    public void removeNotVisitedSubjectIntentHandlerHandles() {
        //HandlerInput Mock
        RemoveNotVisitedSubjectsIntentHandler sut = new RemoveNotVisitedSubjectsIntentHandler();
        final HandlerInput inputMock = HandlerTest.mockHandlerInput();
        final Response response = sut.handle(inputMock).get();
        final String have = response.toString();
        Assert.assertTrue(have.contains( "Willst du noch ein Fach nicht besuchen?"));
    }

}
