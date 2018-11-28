package main.java.tasche_packen.handlers;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.request.Predicates;
import main.java.tasche_packen.model.SubjectItemAssignment;

import java.util.Map;
import java.util.Optional;

public class RemoveNotVisitedSubjectsIntentHandler implements RequestHandler {
    private static boolean removeNotVisitedSubjectsIntentHandlerFinished = false;
    private  SubjectItemAssignment subjectItemAssignment;
    private static final String INTENT_NAME = "RemoveNotVisitedSubjectIntent";


    public RemoveNotVisitedSubjectsIntentHandler(SubjectItemAssignment subjectItemAssignment) {
        this.subjectItemAssignment = subjectItemAssignment;
    }

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName(INTENT_NAME));
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

        GetNotVisitedSubjectIntentHandler.setGetNotVisitedSubjectIntentHandlerFinished(false);
        removeNotVisitedSubjectsIntentHandlerFinished = true;
        String missOneMoreSubject = "Willst du noch ein Fach nicht besuchen?";


        return input.getResponseBuilder()
                .withSpeech(missOneMoreSubject)
                .withReprompt(missOneMoreSubject)
                .withSimpleCard(INTENT_NAME, missOneMoreSubject)
                .build();
    }

    public static boolean getRemoveNotVisitedSubjectsIntentHandlerFinished() {
        return removeNotVisitedSubjectsIntentHandlerFinished;
    }

    public static void setRemoveNotVisitedSubjectsIntentHandlerFinished(boolean removeNotVisitedSubjectsIntentHandlerFinished) {
        RemoveNotVisitedSubjectsIntentHandler.removeNotVisitedSubjectsIntentHandlerFinished = removeNotVisitedSubjectsIntentHandlerFinished;
    }
}
