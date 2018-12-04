package tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import tasche_packen.controller.GoodbyeSentenceGenerator;

import java.util.Optional;

public class GoodbyeIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("GoodbyeIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
       String goodbyeText = GoodbyeSentenceGenerator.GoodbyeSentenceAsString();
        return input.getResponseBuilder()
                .withSpeech(goodbyeText)
                .withSimpleCard("GoodbyeIntent", goodbyeText)
                .withReprompt("")
                .build();
    }
}
