package tasche_packen.handlers;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import tasche_packen.model.Item;
import tasche_packen.model.Subject;
import tasche_packen.model.Subjects;


import java.util.*;

public class LaunchRequestHandler implements RequestHandler {

    private final static boolean RESET = false;

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
            final Subject netzwerkeVL = new Subject("Netzwerke I").addItem("Federmaeppchen");
            final Subject datenbankenVL = new Subject("Datenbanksysteme I").addItem("Federmaeppchen", "Kaffee");
            final Subject sweIVL = new Subject("Software Engineering I").addItem("Notebook", "Kaffee");
            final Subject numerischeVL = new Subject("Numerische Mathematik").addItem("Federmaeppchen");
            final Subject difDGLVL = new Subject("Differentialrechnung in Rn und Differentialgleichungen").addItem("Block");
            final Subject algodatVL = new Subject("Algorithmen und Datenstrukturen I").addItem("Federmaeppchen");
            final Subject statistikVL = new Subject("Wahrscheinlichkeitsrechnung und Statistik").addItem("Block");
            final Subject operationsVL = new Subject("Operations Research").addItem("Federmaeppchen");
            AttributesManager attributesManager = input.getAttributesManager();
            Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
            persistentAttributes.put(netzwerkeVL.getName(), netzwerkeVL.getItems());
            persistentAttributes.put(datenbankenVL.getName(), datenbankenVL.getItems());
            persistentAttributes.put(sweIVL.getName(), sweIVL.getItems());
            persistentAttributes.put(difDGLVL.getName(), difDGLVL.getItems());
            persistentAttributes.put(numerischeVL.getName(), numerischeVL.getItems());
            persistentAttributes.put(algodatVL.getName(), algodatVL.getItems());
            persistentAttributes.put(statistikVL.getName(), statistikVL.getItems());
            persistentAttributes.put(operationsVL.getName(), operationsVL.getItems());
            attributesManager.setPersistentAttributes(persistentAttributes);
            attributesManager.savePersistentAttributes();
        }

    }

