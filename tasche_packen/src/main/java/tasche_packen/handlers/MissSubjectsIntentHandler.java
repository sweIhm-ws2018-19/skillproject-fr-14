package main.java.tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class MissSubjectsIntentHandler implements RequestHandler {
    private static boolean missSubjectsIntentHandlerFinished = false;
    @Override
    public boolean canHandle(HandlerInput input) {
         return input.matches(Predicates.intentName("MissSubjectsIntent")) && WelcomeIntentHandler.getWelcomeFinished();
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String questionWhichSubjectsToMiss = "Welches Fach willst du nicht besuchen?";
        WelcomeIntentHandler.setWelcomeFinished(false);
        return input.getResponseBuilder()
                .withSpeech(questionWhichSubjectsToMiss)
                .withReprompt(questionWhichSubjectsToMiss)
                .withSimpleCard("HelloWorld",questionWhichSubjectsToMiss)
                .build();
    }

    public static boolean getMissSubjectsIntentHandlerFinished() {
        return missSubjectsIntentHandlerFinished;
    }

    public static void setMissSubjectsIntentHandlerFinished(boolean missSubjectsIntentHandlerFinished) {
        MissSubjectsIntentHandler.missSubjectsIntentHandlerFinished = missSubjectsIntentHandlerFinished;
    }
}
