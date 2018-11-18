package main.java.tasche_packen.handlers;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.SkillStreamHandler;
import main.java.tasche_packen.model.SubjectItemAssignment;


/**This class is the entry point for the skill**/
public class PackInBagStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        SubjectItemAssignment subjectItemAssignment = new SubjectItemAssignment();

        return Skills.standard()
                .addRequestHandlers(
                        new CancelAndStopIntentHandler(),
                        new HelloWorldIntentHandler(),
                        new HelpIntentHandler(),
                        new LaunchRequestHandler(),
                        new SessionEndedRequestHandler(),
                        new WelcomeIntentHandler(subjectItemAssignment),
                        new VisitAllSubjectsIntentHandler(subjectItemAssignment),
                        new MissSubjectsIntentHandler(),
                        new MissSubjectsListIntentHandler(subjectItemAssignment))
                .build();
    }

    public PackInBagStreamHandler() {
        super(getSkill());
    }

}