package tasche_packen.handlers;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazonaws.services.dynamodbv2.xspec.NULL;
import tasche_packen.model.Utitlities;

//mvn org.apache.maven.plugins:maven-assembly-plugin:2.6:assembly -DdescriptorId=jar-with-dependencies package
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

import static tasche_packen.handlers.GetItemToChangeIntentHandler.getGetItemToChangeFin;
import static tasche_packen.handlers.GetItemToChangeIntentHandler.setGetItemToChangeFin;
import static tasche_packen.handlers.GetSubjectToChangeIntentHandler.getGetSubjectToChangeFin;
import static tasche_packen.handlers.GetSubjectToChangeIntentHandler.setGetSubjectToChangeFin;

public class AddOrRemoveItemIntentHandler implements RequestHandler {
    private String subject;
    private String item;
    //private static String ANSWER_SLOT = "Answer";
    private static String SUBJECT_SLOT = "Subject";
    private static String ITEM_SLOT = "Item";
    private static final String NULL_VALUE ="NULL";
    private static final String INTENT_NAME= "ChangeItemIntent";
    private static boolean addOrRemoveItemFin = false;
    private Slot subjectSlot;
    private Slot itemSlot;

    @Override
    public boolean canHandle(HandlerInput input) {
        return getGetItemToChangeFin();
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        Slot itemSlot = slots.get(ITEM_SLOT);
        String item = itemSlot == null ? NULL_VALUE : itemSlot.getValue();
        String status;

        if(!item.equals(NULL_VALUE) && Utitlities.subjectToBeChanged != null) {
            AttributesManager attributesManager = input.getAttributesManager();
            Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
            HashSet<String> items = (HashSet<String>) persistentAttributes.get(Utitlities.subjectToBeChanged);
            if(items.contains(item))
                items.remove(item);
            else
                items.add(item);
            persistentAttributes.put(subject,items);
            attributesManager.setPersistentAttributes(persistentAttributes);
            attributesManager.savePersistentAttributes();
            status = "Eingabe gespeichert. Willst Du noch einen weiteren Gegenstand hinzufügen? ";
        }
        else
            status = "Eingabe gescheitert. Willst Du es noch einmal versuchen? ";

        Utitlities.subjectToBeChanged = null;
        setGetItemToChangeFin(false);
        setAddOrRemoveItemFin(true);

        return input.getResponseBuilder()
                .withSpeech(status)
                .withSimpleCard("Add or Remove Item", status)
                .withReprompt("Ich habe dich nicht verstanden. Nenne zuerst das Fach und dann den Gegenstand. ")
                .build();
    }

    public static boolean getAddOrRemoveItemFin() {
        return addOrRemoveItemFin;
    }

    public static void setAddOrRemoveItemFin(boolean finished) {
        addOrRemoveItemFin = finished;
    }

}