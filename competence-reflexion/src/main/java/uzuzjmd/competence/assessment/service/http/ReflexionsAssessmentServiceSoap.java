package uzuzjmd.competence.assessment.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import uzuzjmd.competence.assessment.service.ReflexionsAssessmentServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link uzuzjmd.competence.assessment.service.ReflexionsAssessmentServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link uzuzjmd.competence.assessment.model.ReflexionsAssessmentSoap}.
 * If the method in the service utility returns a
 * {@link uzuzjmd.competence.assessment.model.ReflexionsAssessment}, that is translated to a
 * {@link uzuzjmd.competence.assessment.model.ReflexionsAssessmentSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReflexionsAssessmentServiceHttp
 * @see uzuzjmd.competence.assessment.model.ReflexionsAssessmentSoap
 * @see uzuzjmd.competence.assessment.service.ReflexionsAssessmentServiceUtil
 * @generated
 */
public class ReflexionsAssessmentServiceSoap {
    private static Log _log = LogFactoryUtil.getLog(ReflexionsAssessmentServiceSoap.class);

    public static uzuzjmd.competence.assessment.model.ReflexionsAssessmentSoap[] getReflexionsAssessments(
        long userId, java.lang.String competenceId) throws RemoteException {
        try {
            java.util.List<uzuzjmd.competence.assessment.model.ReflexionsAssessment> returnValue =
                ReflexionsAssessmentServiceUtil.getReflexionsAssessments(userId,
                    competenceId);

            return uzuzjmd.competence.assessment.model.ReflexionsAssessmentSoap.toSoapModels(returnValue);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }
}
