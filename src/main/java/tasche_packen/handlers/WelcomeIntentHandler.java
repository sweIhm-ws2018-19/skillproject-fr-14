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

    public WelcomeIntentHandler(SubjectItemAssignment subjectItemAssignment) {
        this.subjectItemAssignment = subjectItemAssignment;
    }


    @Override
        public boolean canHandle(HandlerInput input) {
            return input.matches(Predicates.intentName("WelcomeIntent"));
        }

        @Override
        public Optional<Response> handle(HandlerInput input) {
            String subjectsToday = "Du hast heute " + subjectItemAssignment.getSubjectsTodayAsString();
            String questionMissingSubjects = "Willst du heute alle Fächer besuchen";
            welcomeFinished = true;
            return input.getResponseBuilder()
                    .withSpeech(subjectsToday + questionMissingSubjects)
                    .withSimpleCard("HelloWorld", subjectsToday + questionMissingSubjects)
                    .withReprompt("test")
                    .build();
        }


        public static boolean getWelcomeFinished() {
            return welcomeFinished;
        }

    public static void setWelcomeFinished(boolean welcomeFinished) {
        WelcomeIntentHandler.welcomeFinished = welcomeFinished;
    }
}



