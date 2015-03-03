package uzuzjmd.competence.assessment.service.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

import uzuzjmd.competence.assessment.service.ClpSerializer;
import uzuzjmd.competence.assessment.service.ReflexionsAssessmentLocalServiceUtil;
import uzuzjmd.competence.assessment.service.ReflexionsAssessmentServiceUtil;
import uzuzjmd.competence.assessment.service.UserLearningTemplateMapLocalServiceUtil;
import uzuzjmd.competence.assessment.service.UserLearningTemplateMapServiceUtil;


public class ClpMessageListener extends BaseMessageListener {
    public static String getServletContextName() {
        return ClpSerializer.getServletContextName();
    }

    @Override
    protected void doReceive(Message message) throws Exception {
        String command = message.getString("command");
        String servletContextName = message.getString("servletContextName");

        if (command.equals("undeploy") &&
                servletContextName.equals(getServletContextName())) {
            ReflexionsAssessmentLocalServiceUtil.clearService();

            ReflexionsAssessmentServiceUtil.clearService();
            UserLearningTemplateMapLocalServiceUtil.clearService();

            UserLearningTemplateMapServiceUtil.clearService();
        }
    }
}
