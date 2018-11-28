package main.java.tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.request.Predicates;
import main.java.tasche_packen.model.Answer;
import main.java.tasche_packen.model.SubjectItemAssignment;

import java.util.Map;
import java.util.Optional;

public class WelcomeIntentHandler implements RequestHandler {
        private static boolean welcomeFinished = false;
        private final SubjectItemAssignment subjectItemAssignment;
        private final static String INTENT_NAME = "WelcomeIntent";
        private static final String ANSWER_SLOT = "Answer";
        private static final String NULL_VALUE = "NULL";
        private String inputString;



    public WelcomeIntentHandler(SubjectItemAssignment subjectItemAssignment) {
        this.subjectItemAssignment = subjectItemAssignment;
    }


    @Override
    public boolean canHandle(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        Slot inputSlot = null;
        if (slots != null) {
         inputSlot = slots.get(ANSWER_SLOT);

        }
        inputString = inputSlot == null ? NULL_VALUE : inputSlot.getValue();
        return input.matches(Predicates.intentName(INTENT_NAME)) || (inputString.equals("Ja") && AidIntentHandler.getAidFinished())
         ;
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        final String subjectsToday = "Du hast heute " + subjectItemAssignment.getSubjectsTodayAsString();
        final String questionMissingSubjects = " Willst du heute alle Faecher besuchen";
        final String output = subjectsToday + questionMissingSubjects;
        welcomeFinished = true;
        AidIntentHandler.setAidFinished(false);
        RemoveNotVisitedSubjectsIntentHandler.setRemoveNotVisitedSubjectsIntentHandlerFinished(false);

        return input.getResponseBuilder()
                .withSpeech(output)
                .withSimpleCard(INTENT_NAME, output)
                .withReprompt(output)
                .build();
    }

    public static boolean getWelcomeFinished() {
            return welcomeFinished;
        }

    public static void setWelcomeFinished(boolean welcomeFinished) {
        WelcomeIntentHandler.welcomeFinished = welcomeFinished;
    }
}




