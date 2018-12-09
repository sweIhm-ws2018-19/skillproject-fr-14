package tasche_packen.handlers;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.request.Predicates;
import tasche_packen.model.SubjectItemAssignment;
import tasche_packen.model.Utitlities;


import java.util.Map;
import java.util.Optional;

import static tasche_packen.handlers.AddOrRemoveItemIntentHandler.getAddOrRemoveItemFin;
import static tasche_packen.handlers.GetItemToChangeIntentHandler.getGetItemToChangeFin;
import static tasche_packen.handlers.GetSubjectToChangeIntentHandler.getGetSubjectToChangeFin;

public class RemoveNotVisitedSubjectsIntentHandler implements RequestHandler {
    private static boolean removeNotVisitedSubjectsIntentHandlerFinished = false;
    private SubjectItemAssignment subjectItemAssignment;
    private static final String INTENT_NAME = "RemoveNotVisitedSubjectIntent";


    public RemoveNotVisitedSubjectsIntentHandler(SubjectItemAssignment subjectItemAssignment) {
        this.subjectItemAssignment = subjectItemAssignment;
    }

    @Override
    public boolean canHandle(HandlerInput input) {
        if(getAddOrRemoveItemFin() || getGetSubjectToChangeFin() || getGetItemToChangeFin()) return false;
        return input.matches(Predicates.intentName(INTENT_NAME));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();

        Slot subjectSlot = slots.get("Subjects");
        String subjectToMiss = "kein fach gefunden";
        if (subjectSlot != null) {
            subjectToMiss = subjectSlot.getResolutions().getResolutionsPerAuthority().get(0).getValues().get(0).getValue().getId();
        }

        //subjectItemAssignment.deleteNotVisitedSubjects(subjectToMiss);
        Utitlities.SUBJECTS_TODAY.removeSubject(subjectToMiss);

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
