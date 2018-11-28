package tasche_packen.handlers;




 import com.amazon.ask.dispatcher.request.handler.HandlerInput;
 import com.amazon.ask.dispatcher.request.handler.RequestHandler;
 import com.amazon.ask.model.Response;
 import com.amazon.ask.request.Predicates;

 import java.util.Optional;

    public class HelloWorldIntentHandler implements RequestHandler {

        @Override
        public boolean canHandle(HandlerInput input) {
            return input.matches(Predicates.intentName("HelloWorldIntent"));
        }

        @Override
        public Optional<Response> handle(HandlerInput input) {
            String speechText = "5 Liter Kaffee";
            return input.getResponseBuilder()
                    .withSpeech(speechText)
                    .withSimpleCard("HelloWorld", speechText)
                    .build();
        }

    }

