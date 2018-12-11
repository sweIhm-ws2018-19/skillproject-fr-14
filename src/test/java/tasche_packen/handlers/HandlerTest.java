package tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.*;

import java.util.Map;

public class HandlerTest {
    public static HandlerInput mockHandlerInput() {
        HandlerInput inputMock = HandlerInput.builder()
                .withRequestEnvelope(RequestEnvelope.builder()
                        .withRequest(IntentRequest.builder().withIntent(Intent.builder().build()).build()).build()).build();
        return inputMock;
    }


    public static HandlerInput mockHandlerInputWithIntentName(String intentName) {
        HandlerInput inputMock = HandlerInput.builder()
                .withRequestEnvelope(RequestEnvelope.builder()
                        .withRequest(IntentRequest.builder().withIntent(Intent.builder().withName(intentName).build()).build())
                        .build()).build();
        return inputMock;
    }


    public static HandlerInput mockHandlerInputWithStringInput(String intentName, String inputString) {
        HandlerInput inputMock = HandlerInput.builder()
                .withRequestEnvelope(RequestEnvelope.builder()
                        .withRequest(IntentRequest.builder().withIntent(Intent.builder().withName(intentName).build()).build())
                        .build()).build();
        return inputMock;
    }

    public static HandlerInput mockHandlerInputWithSlot(String intentName, Map<String, Slot> slots) {
        HandlerInput inputMock = HandlerInput.builder()
                .withRequestEnvelope(RequestEnvelope.builder()
                        .withRequest(IntentRequest.builder().withIntent(Intent.builder().withName(intentName).withSlots(slots).build()).build())
                        .build()).build();
        return inputMock;
    }




    public static Slot mockSlot(String slotName, String value) {
        Slot slot = Slot.builder().withName(slotName).withValue(value).build();
        return slot;
    }


    public static HandlerInput mockHandlerLaunchRequest() {
        HandlerInput inputMock = HandlerInput.builder()
                .withRequestEnvelope(RequestEnvelope.builder()
                        .withRequest(LaunchRequest.builder().build()).build())
                        .build();
        return inputMock;
    }
}
