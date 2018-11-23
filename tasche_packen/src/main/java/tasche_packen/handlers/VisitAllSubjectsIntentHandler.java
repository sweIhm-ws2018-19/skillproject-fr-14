package main.java.tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.request.Predicates;
import main.java.tasche_packen.model.SubjectItemAssignment;

import java.util.Map;
import java.util.Optional;
public class VisitAllSubjectsIntentHandler implements RequestHandler {
    private final SubjectItemAssignment subjectItemAssignment;
    private String inputString;
    public VisitAllSubjectsIntentHandler(SubjectItemAssignment subjectItemAssignment) {
        this.subjectItemAssignment = subjectItemAssignment;
    }


    @Override
    public boolean canHandle(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        Slot inputSlot = slots.get("Answer");
        inputString = inputSlot == null ? "kein Slot gefunden" : inputSlot.getValue();
        return (input.matches(Predicates.intentName("VisitAllSubjectsIntent")) && WelcomeIntentHandler.getWelcomeFinished() && inputString.equals("Ja"))
                || (input.matches(Predicates.intentName("VisitAllSubjectsIntent")) && MissedSubjectsListIntentHandler.getMissSubjectsListIntentHandlerFinished() &&inputString.equals("nein"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String requiredItems = subjectItemAssignment.getTodayRequiredItemsAsString();

        return input.getResponseBuilder()
                .withSpeech("Du brauchst heute" + requiredItems + inputString)
                .withReprompt(requiredItems)
                .withSimpleCard("HelloWorld","Du brauchst heute" + requiredItems)
                .build();
    }
}
