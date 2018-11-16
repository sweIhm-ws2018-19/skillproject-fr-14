package main.java.tasche_packen.handlers;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.SkillStreamHandler;

import main.java.tasche_packen.handlers.CancelAndStopIntentHandler;
import main.java.tasche_packen.handlers.HelloWorldIntentHandler;
import main.java.tasche_packen.handlers.HelpIntentHandler;
import main.java.tasche_packen.handlers.SessionEndedRequestHandler;
import main.java.tasche_packen.handlers.LaunchRequestHandler;


/**This class is the entry point for the skill**/
public class PackInBagStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new CancelAndStopIntentHandler(),
                        new HelloWorldIntentHandler(),
                        new HelpIntentHandler(),
                        new LaunchRequestHandler(),
                        new SessionEndedRequestHandler())
                .build();
    }

    public PackInBagStreamHandler() {
        super(getSkill());
    }

}