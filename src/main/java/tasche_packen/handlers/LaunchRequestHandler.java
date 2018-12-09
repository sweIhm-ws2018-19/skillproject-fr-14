package tasche_packen.handlers;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.request.Predicates;
import tasche_packen.model.Item;
import tasche_packen.model.Subject;
import tasche_packen.model.Subjects;


import java.util.*;

public class LaunchRequestHandler implements RequestHandler {

    //mittlerweile unnötig, kann man immer auf true lassen
    private final static boolean RESET = true;

        @Override
        public boolean canHandle(HandlerInput input) {
            return input.matches(Predicates.requestType(LaunchRequest.class));
        }

        @Override
        public Optional<Response> handle(HandlerInput input) {

            if(RESET) resetDatabase(input);


            String speechText = "Willkommen beim Skill Tasche packen!";
            //TODO evtl an anderer Stelle erzeugen
            return input.getResponseBuilder()
                    .withSpeech(speechText)
                    .withSimpleCard("HelloWorld", speechText)
                    .withReprompt(speechText)
                    .build();
        }

        private void resetDatabase(HandlerInput input) {
            final Subject netzwerkeVL = new Subject("Netzwerke").addItem("Federmaeppchen");
            final Subject datenbankenVL = new Subject("Datenbanksysteme").addItem("Federmaeppchen", "Kaffee");
            final Subject sweIVL = new Subject("Software").addItem("Notebook", "Kaffee");
            final Subject numerischeVL = new Subject("Numerik").addItem("Federmaeppchen", "Block");
            final Subject difDGLVL = new Subject("Differentialrechnung").addItem("Block");
            final Subject algodatVL = new Subject("Algorithmen").addItem("Federmaeppchen");
            final Subject statistikVL = new Subject("Wahrscheinlichkeitsrechnung").addItem("Block");
            final Subject operationsVL = new Subject("Operations").addItem("Federmaeppchen");

            AttributesManager attributesManager = input.getAttributesManager();
            Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
            if(persistentAttributes.isEmpty()) {
            persistentAttributes.put(netzwerkeVL.getName(), netzwerkeVL.getItems());
            persistentAttributes.put(datenbankenVL.getName(), datenbankenVL.getItems());
            persistentAttributes.put(sweIVL.getName(), sweIVL.getItems());
            persistentAttributes.put(difDGLVL.getName(), difDGLVL.getItems());
            persistentAttributes.put(numerischeVL.getName(), numerischeVL.getItems());
            persistentAttributes.put(algodatVL.getName(), algodatVL.getItems());
            persistentAttributes.put(statistikVL.getName(), statistikVL.getItems());
            persistentAttributes.put(operationsVL.getName(), operationsVL.getItems());
            }
            attributesManager.setPersistentAttributes(persistentAttributes);
            attributesManager.savePersistentAttributes();
        }








    }

