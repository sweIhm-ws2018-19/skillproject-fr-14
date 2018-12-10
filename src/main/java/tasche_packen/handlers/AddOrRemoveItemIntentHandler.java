package tasche_packen.handlers;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import tasche_packen.model.Utilities;

//mvn org.apache.maven.plugins:maven-assembly-plugin:2.6:assembly -DdescriptorId=jar-with-dependencies package
import java.util.*;

import static tasche_packen.handlers.GetItemToChangeIntentHandler.getGetItemToChangeFin;
import static tasche_packen.handlers.GetItemToChangeIntentHandler.setGetItemToChangeFin;

public class AddOrRemoveItemIntentHandler implements RequestHandler {
    private static String itemSlot = "Item";
    private static final String NULL_VALUE ="NULL";
    private static boolean addOrRemoveItemFin = false;

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
        Slot itemSlot = slots.get(AddOrRemoveItemIntentHandler.itemSlot);
        String item = itemSlot == null ? NULL_VALUE : itemSlot.getResolutions().getResolutionsPerAuthority().get(0).getValues().get(0).getValue().getId();
        String status;

        if(!item.equals(NULL_VALUE) && Utilities.getSubjectToBeChanged() != null) {

            AttributesManager attributesManager = input.getAttributesManager();
            Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
            HashSet<String> items = (HashSet<String>)persistentAttributes.get(Utilities.getSubjectToBeChanged());
            if(items!= null) {
                if (items.contains(item)) {
                    items.remove(item);
                    status = "Gegenstand erfolgreich entfernt. ";
                } else {
                    items.add(item);
                    status = "Gegenstand erfolgreich hinzugefügt. ";
                }
                status += "Willst du einen weiteren Gegenstand hinzufügen? ";
                persistentAttributes.put(Utilities.getSubjectToBeChanged(), items);
                attributesManager.setPersistentAttributes(persistentAttributes);
                attributesManager.savePersistentAttributes();
            }
            else
                status = "Das Fach wurde nicht erkannt. Willst Du es noch einmal versuchen? ";
        }
        else
            status = "Eingabe gescheitert. Willst Du es noch einmal versuchen? ";

        Utilities.setSubjectToBeChanged(null);
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