package tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import tasche_packen.model.Utilities;

//mvn org.apache.maven.plugins:maven-assembly-plugin:2.6:assembly -DdescriptorId=jar-with-dependencies package
import java.util.Map;
import java.util.Optional;



import static tasche_packen.handlers.GetSubjectToChangeIntentHandler.getGetSubjectToChangeFin;
import static tasche_packen.handlers.GetSubjectToChangeIntentHandler.setGetSubjectToChangeFin;

public class GetItemToChangeIntentHandler implements RequestHandler {

    private static boolean getItemToChangeFin = false;
    private static String slotSubject = "Subject";

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
        Slot subjectSlot = slots.get(GetItemToChangeIntentHandler.slotSubject);
        String subject = subjectSlot == null ? null : subjectSlot.getResolutions().getResolutionsPerAuthority().get(0).getValues().get(0).getValue().getId();
        Utilities.setSubjectToBeChanged(subject);

        String procedure = "Welchen Gegenstand moechtest Du hinzufuegen oder entfernen?";

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