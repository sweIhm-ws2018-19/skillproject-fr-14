package tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

//mvn org.apache.maven.plugins:maven-assembly-plugin:2.6:assembly -DdescriptorId=jar-with-dependencies package
import java.util.Optional;

import static tasche_packen.handlers.WelcomeIntentHandler.setWelcomeFinished;

public class GetChangedItemIntentHandler implements RequestHandler {

    private static boolean getChangedItemFinished = false;

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.intentName("GetChangedItemIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        String procedure = "Nenne jetzt zuerst das Fach und daraufhin den hinzuzufügenden oder zu entfernenden Gegenstand. Sage anschließend \"Eingabe beendet\". ";
        setGetChangedItemFinished(true);
        setWelcomeFinished(false);
        return input.getResponseBuilder()
                .withSpeech(procedure)
                .withSimpleCard("ChangeItem", procedure)
                .withReprompt("Ich habe dich nicht verstanden. Nenne zuerst das Fach und dann den Gegenstand. ")
                .build();
    }

    public static boolean getGetChangedItemFinished() {
        return getChangedItemFinished;
    }

    public static void setGetChangedItemFinished(boolean finished) {
        getChangedItemFinished = finished;
    }

}