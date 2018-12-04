package tasche_packen.handlers;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.SkillStreamHandler;
import tasche_packen.model.SubjectItemAssignment;


/**This class is the entry point for the skill**/
public class PackInBagStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        SubjectItemAssignment subjectItemAssignment = new SubjectItemAssignment();

        return Skills.standard()
                .addRequestHandlers(
                        new LaunchRequestHandler(),
                        new CancelAndStopIntentHandler(),
                        new HelloWorldIntentHandler(),
                        new HelpIntentHandler(),
                        new SessionEndedRequestHandler(),
                        new AidIntentHandler(),
                        new WelcomeIntentHandler(subjectItemAssignment),
                        new GetRequiredItemsIntentHandler(subjectItemAssignment),
                        new GetNotVisitedSubjectIntentHandler(),
                        new AidIntentHandler(),
                        new RemoveNotVisitedSubjectsIntentHandler(subjectItemAssignment),
                        new GoodbyeIntentHandler()
                        )

      .withTableName("packInBagData")
                .withAutoCreateTable(true)
             //   .withSkillId("id")
                  .build();
    }

    public PackInBagStreamHandler() {
        super(getSkill());
    }

}