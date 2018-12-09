package tasche_packen.handlers;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.request.Predicates;
import tasche_packen.model.Answer;
import tasche_packen.model.SubjectItemAssignment;
import tasche_packen.model.Utitlities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

import static tasche_packen.handlers.AddOrRemoveItemIntentHandler.getAddOrRemoveItemFin;
import static tasche_packen.handlers.GetNotVisitedSubjectIntentHandler.getGetNotVisitedSubjectIntentHandlerFinished;
import static tasche_packen.handlers.WelcomeIntentHandler.getWelcomeFinished;


public class GetRequiredItemsIntentHandler implements RequestHandler {
    private final SubjectItemAssignment subjectItemAssignment;
    private String inputString;
    private final String intentName = "GetRequiredItemsIntent";
    private static final String ANSWER_SLOT = "Answer";
    private static final String NULL_VALUE = "NULL";
    private static final String YES = "ja";
    private static final String NO = "nein";
    private static  boolean getRequiredItemsIntentHandlerFinished = false;

    public GetRequiredItemsIntentHandler(SubjectItemAssignment subjectItemAssignment) {
        this.subjectItemAssignment = subjectItemAssignment;
    }


    @Override
    public boolean canHandle(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        Slot inputSlot = slots == null ? null :  slots.get(ANSWER_SLOT);
        inputString = inputSlot == null ? NULL_VALUE : inputSlot.getValue();
        if(getAddOrRemoveItemFin()) return false;
        return   (inputString != null   && inputString.equals("Ja")  && WelcomeIntentHandler.getWelcomeFinished() )||
                (inputString != null   && inputString.equals("ja")  && WelcomeIntentHandler.getWelcomeFinished() )
                || (inputString != null && RemoveNotVisitedSubjectsIntentHandler.getRemoveNotVisitedSubjectsIntentHandlerFinished()  && inputString.equals(Answer.No.getName()));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        final String answer = "Du brauchst heute ";


        AttributesManager attributesManager = input.getAttributesManager();
        Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
        ArrayList<String> subjects = Utitlities.SUBJECTS_TODAY.getSubjectsToday();
        HashSet<String> items = new HashSet<>();
        for(int i = 0; i < subjects.size(); i++) {
            HashSet<String> subjectItem = (HashSet<String>) persistentAttributes.get(subjects.get(i));
            if(subjectItem != null) items.addAll(subjectItem);
        }
        String requiredItems = items.stream()
                .reduce((first,second) -> first + "," + second)
                .orElse("nichts");
        if(requiredItems.contains(","))
            requiredItems = requiredItems.substring(0, requiredItems.lastIndexOf(',')) + " und "
                    + requiredItems.substring(requiredItems.lastIndexOf(',') + 1);


        final String output = answer + requiredItems;
        RemoveNotVisitedSubjectsIntentHandler.setRemoveNotVisitedSubjectsIntentHandlerFinished(false);
        setGetRequiredItemsIntentHandlerFinished(true);
        WelcomeIntentHandler.setWelcomeFinished(false);
        return input.getResponseBuilder()
                .withSpeech(output)
                .withReprompt(requiredItems)
                .withSimpleCard(intentName, output)
                .build();
    }


    public static boolean getGetRequiredItemsIntentHandlerFinished() {
        return getRequiredItemsIntentHandlerFinished;
    }

    public static void setGetRequiredItemsIntentHandlerFinished(boolean getRequiredItemsIntentHandlerFinished) {
        GetRequiredItemsIntentHandler.getRequiredItemsIntentHandlerFinished = getRequiredItemsIntentHandlerFinished;
    }
}
