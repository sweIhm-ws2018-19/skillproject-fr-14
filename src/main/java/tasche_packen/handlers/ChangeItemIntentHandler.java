package tasche_packen.handlers;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.request.Predicates;
import com.amazonaws.services.dynamodbv2.xspec.NULL;
import tasche_packen.model.Answer;

//mvn org.apache.maven.plugins:maven-assembly-plugin:2.6:assembly -DdescriptorId=jar-with-dependencies package
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

import static tasche_packen.handlers.GetChangedItemIntentHandler.getGetChangedItemFinished;
import static tasche_packen.handlers.GetChangedItemIntentHandler.setGetChangedItemFinished;
import static tasche_packen.handlers.WelcomeIntentHandler.setWelcomeFinished;

public class ChangeItemIntentHandler implements RequestHandler {
    private String subject;
    private String item;
    private static String ANSWER_SLOT = "Answer";
    private static String SUBJECT_SLOT = "Subject";
    private static String ITEM_SLOT = "Item";
    private static final String NULL_VALUE ="NULL";
    private static final String INTENT_NAME= "ChangeItemIntent";
    private static boolean changeItemFinished;
    private Slot subjectSlot;
    private Slot itemSlot;

    @Override
    public boolean canHandle(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        Slot answerSlot = slots.get(ANSWER_SLOT);
        subjectSlot = slots.get(SUBJECT_SLOT);
        itemSlot = slots.get(ITEM_SLOT);
        String answer = answerSlot == null ? NULL_VALUE : answerSlot.getValue();
        if(getChangedItemFinished() && !getGetChangedItemFinished() && Answer.Yes.getName().equals(answer)) return true;
        return getGetChangedItemFinished();
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        subject = subjectSlot == null ? NULL_VALUE : subjectSlot.getValue();
        item = itemSlot == null ? NULL_VALUE : itemSlot.getValue();
        String status;

        if(subject != NULL_VALUE && item != NULL_VALUE) {
            AttributesManager attributesManager = input.getAttributesManager();
            Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
            HashSet<String> items = (HashSet<String>) persistentAttributes.get(subject);
            items.add(item);
            persistentAttributes.put(subject,items);
            attributesManager.setPersistentAttributes(persistentAttributes);
            attributesManager.savePersistentAttributes();
            status = "Eingabe gespeichert. Willst Du noch einen weiteren Gegenstand hinzufügen?";
        }
        else
            status = "Eingabe gescheitert. Willst Du es noch einmal versuchen?";


        setGetChangedItemFinished(false);
        setChangedItemFinished(true);
        return input.getResponseBuilder()
                .withSpeech(status)
                .withSimpleCard("ChangeItem", status)
                .withReprompt("Ich habe dich nicht verstanden. Nenne zuerst das Fach und dann den Gegenstand. ")
                .build();
    }

    public static boolean getChangedItemFinished() {
        return changeItemFinished;
    }

    public static void setChangedItemFinished(boolean finished) {
        changeItemFinished = finished;
    }

}