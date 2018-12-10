package tasche_packen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.request.Predicates;
import tasche_packen.model.Answer;
import tasche_packen.model.Utilities;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import static tasche_packen.handlers.AddOrRemoveItemIntentHandler.*;
import static tasche_packen.handlers.GetSubjectToChangeIntentHandler.setGetSubjectToChangeFin;

public class WelcomeIntentHandler implements RequestHandler {
    private static boolean welcomeFinished = false;
    private static final String INTENT_NAME = "WelcomeIntent";
    private static final String ANSWER_SLOT = "Answer";
    private static final String NULL_VALUE = "NULL";


    @Override
    public boolean canHandle(HandlerInput input) {
        String inputString;
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        Slot inputSlot = null;
        if (slots != null) {
            inputSlot = slots.get(ANSWER_SLOT);
        }
        inputString = inputSlot == null ? NULL_VALUE : inputSlot.getValue();

        return input.matches(Predicates.intentName(INTENT_NAME))
                || (inputString.equals("Ja") && AidIntentHandler.getAidFinished() && !getAddOrRemoveItemFin()
                || (getAddOrRemoveItemFin() && Answer.NO.getName().equals(inputString)));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

      String subjectsToday = Utilities.SUBJECTS_TODAY.getTodaysSubjects().stream()
                .distinct()
                .filter(Objects::nonNull)
                .map(Utilities::subjectMapper)
                .reduce((first,second) -> first + ", " + second)
                .orElse("nichts");


        if(subjectsToday.contains(","))
            subjectsToday = subjectsToday.substring(0, subjectsToday.lastIndexOf(',')) + " und " + subjectsToday.substring(subjectsToday.lastIndexOf(',') + 1);
        final String questionMissingSubjects = " Willst du heute alle Faecher besuchen ? ";
        final String output = "Du hast heute " + subjectsToday + " . " + questionMissingSubjects;


        welcomeFinished = true;
        AidIntentHandler.setAidFinished(false);
        setGetSubjectToChangeFin(false);
        setAddOrRemoveItemFin(false);
        RemoveNotVisitedSubjectsIntentHandler.setRemoveNotVisitedSubjectsIntentHandlerFinished(false);

        return input.getResponseBuilder()
                .withSpeech(output)
                .withSimpleCard(INTENT_NAME, output)
                .withReprompt(output)
                .build();
    }

    public static boolean getWelcomeFinished() {
        return welcomeFinished;
    }

    public static void setWelcomeFinished(boolean welcomeFinished) {
        WelcomeIntentHandler.welcomeFinished = welcomeFinished;
    }
}



