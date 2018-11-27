package main.java.tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import main.java.tasche_packen.model.SubjectItemAssignment;

import java.util.Optional;

public class WelcomeIntentHandler implements RequestHandler {
        private static boolean welcomeFinished = false;
        private final SubjectItemAssignment subjectItemAssignment;
        private final static String INTENT_NAME = "WelcomeIntent";


    public WelcomeIntentHandler(SubjectItemAssignment subjectItemAssignment) {
        this.subjectItemAssignment = subjectItemAssignment;
    }


    @Override
    public boolean canHandle(HandlerInput input) {

        return input.matches(Predicates.intentName(INTENT_NAME));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        final String subjectsToday = "Du hast heute " + subjectItemAssignment.getSubjectsTodayAsString();
        final String questionMissingSubjects = "Willst du heute alle Fächer besuchen";
        final String output = subjectsToday + questionMissingSubjects;
        welcomeFinished = true;
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




