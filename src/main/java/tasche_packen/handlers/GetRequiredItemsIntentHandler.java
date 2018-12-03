package tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.request.Predicates;
import tasche_packen.model.Answer;
import tasche_packen.model.SubjectItemAssignment;

import java.util.Map;
import java.util.Optional;
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
        Slot inputSlot = slots.get(ANSWER_SLOT);
        inputString = inputSlot == null ? NULL_VALUE : inputSlot.getValue();
        return   (inputString != null   && inputString.equals("Ja")  && WelcomeIntentHandler.getWelcomeFinished() )
                || (inputString != null && RemoveNotVisitedSubjectsIntentHandler.getRemoveNotVisitedSubjectsIntentHandlerFinished()  && inputString.equals(Answer.No.getName()));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        final String answer = "Du brauchst heute ";
        final String requiredItems = subjectItemAssignment.getTodayRequiredItemsAsString();
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
