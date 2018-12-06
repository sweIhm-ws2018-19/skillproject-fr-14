package tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.request.Predicates;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import tasche_packen.model.SubjectItemAssignment;

import static org.junit.Assert.*;

public class LaunchRequestHandlerTest {


    @Test
    public void launchRequestHandlerCanHandle() {
        final LaunchRequestHandler sut = new LaunchRequestHandler();
        final HandlerInput handlerInput = HandlerTest.mockHandlerLaunchRequest();
        final boolean want = true;
        final boolean have = sut.canHandle(handlerInput);
        Assert.assertEquals(want, have );
    }





    @Test
    public void launchRequestHandlerHandles() {
       final LaunchRequestHandler sut = new LaunchRequestHandler();
       final HandlerInput inputMock = HandlerTest.mockHandlerInput();
       final Response response = sut.handle(inputMock).get();
       final String have = response.toString();
       assertTrue(have.contains("Willkommen beim Skill Tasche packen"));
    }




}