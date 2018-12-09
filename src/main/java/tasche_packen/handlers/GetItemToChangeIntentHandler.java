package tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.request.Predicates;
import tasche_packen.model.Utitlities;

//mvn org.apache.maven.plugins:maven-assembly-plugin:2.6:assembly -DdescriptorId=jar-with-dependencies package
import java.util.Map;
import java.util.Optional;

//extra handler für fach und gegenstand über statisches Fach?

import static tasche_packen.handlers.GetSubjectToChangeIntentHandler.getGetSubjectToChangeFin;
import static tasche_packen.handlers.GetSubjectToChangeIntentHandler.setGetSubjectToChangeFin;
import static tasche_packen.handlers.WelcomeIntentHandler.setWelcomeFinished;

public class GetItemToChangeIntentHandler implements RequestHandler {

    private static boolean getItemToChangeFin = false;
    private static String SUBJECT_SLOT = "Subject";
    //private static final String NULL_VALUE ="NULL";

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return getGetSubjectToChangeFin();
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        Slot subjectSlot = slots.get(SUBJECT_SLOT);
        String subject = subjectSlot == null ? null : subjectSlot.getResolutions().getResolutionsPerAuthority().get(0).getValues().get(0).getValue().getId();
        Utitlities.subjectToBeChanged = subject;

        String procedure = "Welches Item möchtest Du hinzufügen oder entfernen?";

        setGetItemToChangeFin(true);
        setGetSubjectToChangeFin(false);

        return input.getResponseBuilder()
                .withSpeech(procedure)
                .withSimpleCard("ChangeItem", procedure)
                .withReprompt(procedure)
                .build();
    }

    public static boolean getGetItemToChangeFin() {
        return getItemToChangeFin;
    }

    public static void setGetItemToChangeFin(boolean finished) {
        getItemToChangeFin = finished;
    }

}