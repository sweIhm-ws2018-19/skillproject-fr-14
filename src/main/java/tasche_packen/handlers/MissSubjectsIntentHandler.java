package main.java.tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.request.Predicates;
import java.util.Map;
import java.util.Optional;

public class MissSubjectsIntentHandler implements RequestHandler {
    private static boolean missSubjectsIntentHandlerFinished = false;
    String inputString;


    @Override
    public boolean canHandle(HandlerInput input) {
         Request request = input.getRequestEnvelope().getRequest();
         IntentRequest intentRequest = (IntentRequest) request;
         Intent intent = intentRequest.getIntent();
         Map<String, Slot> slots = intent.getSlots();
         Slot inputSlot = slots.get("Answer");
         inputString = inputSlot == null ? "kein Slot gefunden" : inputSlot.getValue();
         return inputString.equals("nein") ||( inputString != null  && inputString.equals("Ja")  && MissedSubjectsListIntentHandler.getMissSubjectsListIntentHandlerFinished())
                 ;
         //                 ;// WelcomeIntentHandler.getWelcomeFinished() && WelcomeIntentHandler.getWelcomeFinished() && &&  input.matches(Predicates.intentName("MissSubjectsIntent"))
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String questionWhichSubjectsToMiss = "Welches Fach willst du nicht besuchen?";
        missSubjectsIntentHandlerFinished = true;
        WelcomeIntentHandler.setWelcomeFinished(false);
        MissedSubjectsListIntentHandler.setMissSubjectsListIntentHandlerFinished(false);
        return input.getResponseBuilder()
                .withSpeech(questionWhichSubjectsToMiss + inputString)
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
