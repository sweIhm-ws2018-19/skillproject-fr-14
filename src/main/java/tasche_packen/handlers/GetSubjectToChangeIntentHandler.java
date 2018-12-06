package tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.request.Predicates;
import tasche_packen.model.Answer;

//mvn org.apache.maven.plugins:maven-assembly-plugin:2.6:assembly -DdescriptorId=jar-with-dependencies package
import java.util.Map;
import java.util.Optional;

//extra handler für fach und gegenstand über statisches Fach?

import static tasche_packen.handlers.AddOrRemoveItemIntentHandler.getAddOrRemoveItemFin;
import static tasche_packen.handlers.AddOrRemoveItemIntentHandler.setAddOrRemoveItemFin;
import static tasche_packen.handlers.WelcomeIntentHandler.setWelcomeFinished;

public class GetSubjectToChangeIntentHandler implements RequestHandler {

    private static String ANSWER_SLOT = "Answer";
    private static boolean getSubjectToChangeFin = false;
    private static final String NULL_VALUE ="NULL";

    @Override
    public boolean canHandle(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        Slot answerSlot = null;
        if(slots != null) {
            answerSlot = slots.get(ANSWER_SLOT);
        }
        String answer = answerSlot == null ? NULL_VALUE : answerSlot.getValue();

        return  input.matches(Predicates.intentName("GetChangedItemIntent")) ||
                (getAddOrRemoveItemFin() == true && Answer.Yes.equals(answer));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        String procedure = "Zu welchem Fach willst Du einen Gegenstand hinzufuegen oder entfernen?";

        setGetSubjectToChangeFin(true);
        setAddOrRemoveItemFin(false);
        setWelcomeFinished(false);

        return input.getResponseBuilder()
                .withSpeech(procedure)
                .withSimpleCard("ChangeItem", procedure)
                .withReprompt(procedure)
                .build();
    }

    public static boolean getGetSubjectToChangeFin() {
        return getSubjectToChangeFin;
    }

    public static void setGetSubjectToChangeFin(boolean finished) {
        getSubjectToChangeFin = finished;
    }

}