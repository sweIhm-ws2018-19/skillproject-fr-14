package main.java.tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

//mvn org.apache.maven.plugins:maven-assembly-plugin:2.6:assembly -DdescriptorId=jar-with-dependencies package
import java.util.Optional;

public class AidIntentHandler implements RequestHandler {

    private static boolean aidFinished;

    public AidIntentHandler() {

    }

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.intentName("AidIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        final String appreciation = "Danke für das Oeffnen von Tasche packen. ";
        final String function1 = "Du kannst Dir Deine Liste an mitzunehmenden Gegenstaenden für den heutigen Tag ausgeben lassen. ";
        final String function2 = "Außerdem kannst Du angeben welche Faecher Du heute nicht besuchen willst. Ich passe dann deine Liste darauf an. ";
        final String function3 = "Falls Du zu einem Fach jetzt andere Sachen mitnehmen willst kannst Du das nach Alexas erster Anfrage mit dem Befehl \"Gegenstand hinzufügen\" tun. ";
        final String question = "Willst Du jetzt Deine Tasche packen? ";
        setAidFinished(true);
        return input.getResponseBuilder()
                .withSpeech(appreciation + function1 + function2 + function3 + question)
                .withSimpleCard("Hilfe", appreciation + function1 + function2 + function3 + question)
                .withReprompt("Ich habe dich nicht verstanden, " + question)
                .build();
    }

    public static boolean getAidFinished() {
        return aidFinished;
    }

    public static void setAidFinished(boolean finished) {
        AidIntentHandler.aidFinished = finished;
    }

}
