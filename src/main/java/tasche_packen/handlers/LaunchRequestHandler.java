package tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import main.java.tasche_packen.model.SubjectItemAssignment;

import java.util.Optional;

    public class LaunchRequestHandler implements RequestHandler {

        @Override
        public boolean canHandle(HandlerInput input) {
            return input.matches(Predicates.requestType(LaunchRequest.class));
        }

        @Override
        public Optional<Response> handle(HandlerInput input) {
            String speechText = "Willkommen beim Skill Tasche packen";
            //TODO evtl an anderer Stelle erzeugen
            return input.getResponseBuilder()
                    .withSpeech(speechText)
                    .withSimpleCard("HelloWorld", speechText)
                    .withReprompt(speechText)
                    .build();
        }

    }

