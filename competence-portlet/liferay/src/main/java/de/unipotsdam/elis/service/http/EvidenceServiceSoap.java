package de.unipotsdam.elis.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import de.unipotsdam.elis.service.EvidenceServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link de.unipotsdam.elis.service.EvidenceServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link de.unipotsdam.elis.model.EvidenceSoap}.
 * If the method in the service utility returns a
 * {@link de.unipotsdam.elis.model.Evidence}, that is translated to a
 * {@link de.unipotsdam.elis.model.EvidenceSoap}. Methods that SOAP cannot
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
 * @see EvidenceServiceHttp
 * @see de.unipotsdam.elis.model.EvidenceSoap
 * @see de.unipotsdam.elis.service.EvidenceServiceUtil
 * @generated
 */
public class EvidenceServiceSoap {
    private static Log _log = LogFactoryUtil.getLog(EvidenceServiceSoap.class);

    public static void helloWorld() throws RemoteException {
        try {
            EvidenceServiceUtil.helloWorld();
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static de.unipotsdam.elis.model.EvidenceSoap[] getGroupEvidences(
        long groupId) throws RemoteException {
        try {
            java.util.List<de.unipotsdam.elis.model.Evidence> returnValue = EvidenceServiceUtil.getGroupEvidences(groupId);

            return de.unipotsdam.elis.model.EvidenceSoap.toSoapModels(returnValue);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }
}
