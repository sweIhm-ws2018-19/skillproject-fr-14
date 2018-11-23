package main.java.tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.request.Predicates;
import main.java.tasche_packen.model.SubjectItemAssignment;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class MissSubjectsListIntentHandler implements RequestHandler {
    private final SubjectItemAssignment subjectItemAssignment;
    private static boolean missSubjectsListIntentHandlerFinished;

    public MissSubjectsListIntentHandler(SubjectItemAssignment subjectItemAssignment) {
        this.subjectItemAssignment = subjectItemAssignment;
    }


    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("MissedSubjectsListIntent"))
                && MissSubjectsIntentHandler.getMissSubjectsIntentHandlerFinished();
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
 /*       Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();

        Slot subjectSlot = slots.get("Subject");
        String subjectToMiss = "kein fach gefunden";
        if (subjectSlot != null) {
            subjectToMiss = subjectSlot.getValue();
        }*/
/*        subjectItemAssignment.deleteNotVisitedSubjects(subjectToMiss);
        MissSubjectsIntentHandler.setMissSubjectsIntentHandlerFinished(false);
        String missOneMoreSubject = "Willst du noch ein Fach nicht besuchen?";
        missSubjectsListIntentHandlerFinished = true*/;
        return input.getResponseBuilder()
                .withSpeech("test")
                .withReprompt("test")
                .withSimpleCard("HelloWorld", "test")
                .build();


    }


    public static boolean getMissSubjectsListIntentHandlerFinished() {
        return missSubjectsListIntentHandlerFinished;
    }

    public static void setMissSubjectsListIntentHandlerFinished(boolean missSubjectsListIntentHandlerFinished) {
        missSubjectsListIntentHandlerFinished = missSubjectsListIntentHandlerFinished;
    }
}
