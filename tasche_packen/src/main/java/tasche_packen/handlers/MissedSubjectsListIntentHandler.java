package main.java.tasche_packen.handlers;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.request.Predicates;
import main.java.tasche_packen.model.SubjectItemAssignment;

import java.util.Map;
import java.util.Optional;

public class MissedSubjectsListIntentHandler implements RequestHandler {
    private static boolean missSubjectsListIntentHandlerFinished = false;
    private  SubjectItemAssignment subjectItemAssignment;


    public MissedSubjectsListIntentHandler(SubjectItemAssignment subjectItemAssignment) {
        this.subjectItemAssignment = subjectItemAssignment;
    }

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("MissedSubjectsListIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
         Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();

        Slot subjectSlot = slots.get("Subject");
        String subjectToMiss = "kein fach gefunden";
        if (subjectSlot != null) {
            subjectToMiss = subjectSlot.getValue();
        }
        subjectItemAssignment.deleteNotVisitedSubjects(subjectToMiss);
        MissSubjectsIntentHandler.setMissSubjectsIntentHandlerFinished(false);
        missSubjectsListIntentHandlerFinished = true;
        String missOneMoreSubject = "Willst du noch ein Fach nicht besuchen?";


        return input.getResponseBuilder()
                .withSpeech(missOneMoreSubject +subjectItemAssignment.getTodayRequiredItemsAsString())
                .withReprompt(missOneMoreSubject)
                .withSimpleCard("HelloWorld", missOneMoreSubject)
                .build();
    }

    public static boolean getMissSubjectsListIntentHandlerFinished() {
        return missSubjectsListIntentHandlerFinished;
    }

    public static void setMissSubjectsListIntentHandlerFinished(boolean missSubjectsListIntentHandlerFinished) {
        missSubjectsListIntentHandlerFinished = missSubjectsListIntentHandlerFinished;
    }
}
