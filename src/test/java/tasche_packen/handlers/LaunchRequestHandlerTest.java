package tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class LaunchRequestHandlerTest {


    @Test

    public void testGoodbyeHandler() {

        //HandlerInput Mock
        LaunchRequestHandler sut = new LaunchRequestHandler();
        HandlerInput inputMock = mockHandlerInput();
        Response response = sut.handle(inputMock).get();

        String have = response.toString();

        assertTrue(have.contains("Willkommen beim Skill Tasche packen"));
    }

    public static HandlerInput mockHandlerInput() {

        HandlerInput inputMock = HandlerInput.builder()

                .withRequestEnvelope(RequestEnvelope.builder()
                        .withRequest(IntentRequest.builder().withIntent(Intent.builder().build()).build()).build()).build();
        return inputMock;


    }
}