package tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import tasche_packen.model.Answer;
import java.util.Map;
import java.util.Optional;

import static tasche_packen.handlers.AddOrRemoveItemIntentHandler.getAddOrRemoveItemFin;
import static tasche_packen.handlers.WelcomeIntentHandler.getWelcomeFinished;

public class GetNotVisitedSubjectIntentHandler implements RequestHandler {
    private static boolean getNotVisitedSubjectIntentHandlerFinished = false;
    private static String answerSlot = "Answer";
    private static final String NULL_VALUE ="NULL";
    private static final String INTENT_NAME= "GetNotVisitedSubjectIntent";


    @Override
    public boolean canHandle(HandlerInput input) {
        String inputString;
        if(!getWelcomeFinished()) return false;
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        Slot inputSlot = slots.get(answerSlot);
        inputString = inputSlot == null ? NULL_VALUE : inputSlot.getValue();
        return (inputString != null && inputString.equals(Answer.NO.getName()) &&   getWelcomeFinished()  )||( inputString != null  && inputString.equals("Ja")&& RemoveNotVisitedSubjectsIntentHandler.getRemoveNotVisitedSubjectsIntentHandlerFinished() && !getAddOrRemoveItemFin());
    }



    @Override
    public Optional<Response> handle(HandlerInput input) {
        String questionWhichSubjectsToMiss = "Welches Fach willst du nicht besuchen?";
        getNotVisitedSubjectIntentHandlerFinished = true;
        WelcomeIntentHandler.setWelcomeFinished(false);
        RemoveNotVisitedSubjectsIntentHandler.setRemoveNotVisitedSubjectsIntentHandlerFinished(false);
        return input.getResponseBuilder()
                .withSpeech(questionWhichSubjectsToMiss)
                .withReprompt(questionWhichSubjectsToMiss)
                .withSimpleCard(INTENT_NAME,questionWhichSubjectsToMiss)
                .build();
    }

    public static boolean getGetNotVisitedSubjectIntentHandlerFinished() {
        return getNotVisitedSubjectIntentHandlerFinished;
    }

    public static void setGetNotVisitedSubjectIntentHandlerFinished(boolean getNotVisitedSubjectIntentHandlerFinished) {
        GetNotVisitedSubjectIntentHandler.getNotVisitedSubjectIntentHandlerFinished = getNotVisitedSubjectIntentHandlerFinished;
    }
}
