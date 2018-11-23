package main.java.tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class AidIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.intentName("AidIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        final String appreciation = "Danke für das Öffnen von Tasche packen.";
        final String function1 = "Du kannst Dir Deine Liste an mitzunehmenden Gegenständen für den heutigen Tag ausgeben lassen.";
        final String function2 = "Außerdem kannst Du angeben welche Fächer Du heute nicht besuchen willst. Ich passe dann deine Liste darauf an";
        final String function3 = "Falls Du zu einem Fach jetzt andere Sachen mitnehmen willst kannst Du das nach Alexas erster Anfrage mit dem Befehl \"Gegenstand hinzufügen\" tun.";
        final String question = "Willst Du jetzt Deine Tasche packen?";
        return handlerInput.getResponseBuilder()
                .withSpeech(appreciation)
                .withSpeech(function1)
                .withSpeech(function2)
                .withSpeech(function3)
                .withSpeech(question)
                .withSimpleCard("Hilfe", appreciation + function1 + function2 + function3 + question)
                .withReprompt("Ich habe dich nicht verstanden." + question)
                .build();
    }
}
