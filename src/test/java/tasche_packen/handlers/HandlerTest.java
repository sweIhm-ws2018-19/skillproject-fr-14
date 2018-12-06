package tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.RequestEnvelope;

public class HandlerTest {
    public static HandlerInput mockHandlerInput() {
        HandlerInput inputMock = HandlerInput.builder()
                .withRequestEnvelope(RequestEnvelope.builder()
                        .withRequest(IntentRequest.builder().withIntent(Intent.builder().build()).build()).build()).build();
        return inputMock;
    }


    public static HandlerInput mockHandlerInputWithString(String intentName) {
        HandlerInput inputMock = HandlerInput.builder()
                .withRequestEnvelope(RequestEnvelope.builder()
                        .withRequest(IntentRequest.builder().withIntent(Intent.builder().withName(intentName).build()).build())
                        .build()).build();
        return inputMock;
    }


    public static HandlerInput mockHandlerLaunchRequest() {
        HandlerInput inputMock = HandlerInput.builder()
                .withRequestEnvelope(RequestEnvelope.builder()
                        .withRequest(LaunchRequest.builder().build()).build())
                        .build();
        return inputMock;
    }
}
