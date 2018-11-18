package main.java.tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import main.java.tasche_packen.model.SubjectItemAssignment;
import java.util.Optional;

public class MissSubjectsListIntentHandler implements RequestHandler {
    private final SubjectItemAssignment subjectItemAssignment;

    public MissSubjectsListIntentHandler(SubjectItemAssignment subjectItemAssignment) {
        this.subjectItemAssignment = subjectItemAssignment;
    }


    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("MissedSubjectsListIntent")) && MissSubjectsIntentHandler.getMissSubjectsIntentHandlerFinished();
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        subjectItemAssignment.deleteNotVisitedSubjects(input.toString());
        String questionWhichSubjectsToMiss = "Willst du noch ein Fach nicht besuchen?";
        String testInput = subjectItemAssignment.getSubjectsVisitedAsString();
        MissSubjectsIntentHandler.setMissSubjectsIntentHandlerFinished(false);
        return input.getResponseBuilder()
                .withSpeech(testInput)
                .withSimpleCard("HelloWorld",questionWhichSubjectsToMiss)
                .build();
    }








}
