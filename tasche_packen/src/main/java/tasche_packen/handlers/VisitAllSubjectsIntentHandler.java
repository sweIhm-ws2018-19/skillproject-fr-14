package main.java.tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import main.java.tasche_packen.model.SubjectItemAssignment;

import java.util.Optional;
public class VisitAllSubjectsIntentHandler implements RequestHandler {
    private final SubjectItemAssignment subjectItemAssignment;

    public VisitAllSubjectsIntentHandler(SubjectItemAssignment subjectItemAssignment) {
        this.subjectItemAssignment = subjectItemAssignment;
    }


    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("VisitAllSubjectsIntent")) && WelcomeIntentHandler.getWelcomeFinished();
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String requiredItems = subjectItemAssignment.getTodayRequiredItemsAsString();

        return input.getResponseBuilder()
                .withSpeech("Du brauchst heute" + requiredItems)
                .withReprompt(requiredItems)
                .withSimpleCard("HelloWorld","Du brauchst heute" + requiredItems)
                .build();
    }
}
