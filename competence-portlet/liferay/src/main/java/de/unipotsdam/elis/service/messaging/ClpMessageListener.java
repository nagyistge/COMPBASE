package de.unipotsdam.elis.service.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

import de.unipotsdam.elis.service.ClpSerializer;
import de.unipotsdam.elis.service.EvidenceLocalServiceUtil;
import de.unipotsdam.elis.service.EvidenceServiceUtil;


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
            EvidenceLocalServiceUtil.clearService();

            EvidenceServiceUtil.clearService();
        }
    }
}
