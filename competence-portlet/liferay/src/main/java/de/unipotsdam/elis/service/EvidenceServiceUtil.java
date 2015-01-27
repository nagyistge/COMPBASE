package de.unipotsdam.elis.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service utility for Evidence. This utility wraps
 * {@link de.unipotsdam.elis.service.impl.EvidenceServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see EvidenceService
 * @see de.unipotsdam.elis.service.base.EvidenceServiceBaseImpl
 * @see de.unipotsdam.elis.service.impl.EvidenceServiceImpl
 * @generated
 */
public class EvidenceServiceUtil {
    private static EvidenceService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link de.unipotsdam.elis.service.impl.EvidenceServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return getService().invokeMethod(name, parameterTypes, arguments);
    }

    public static void helloWorld() {
        getService().helloWorld();
    }

    public static java.util.List<de.unipotsdam.elis.model.Evidence> getGroupEvidences(
        long groupId) {
        return getService().getGroupEvidences(groupId);
    }

    public static void clearService() {
        _service = null;
    }

    public static EvidenceService getService() {
        if (_service == null) {
            InvokableService invokableService = (InvokableService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    EvidenceService.class.getName());

            if (invokableService instanceof EvidenceService) {
                _service = (EvidenceService) invokableService;
            } else {
                _service = new EvidenceServiceClp(invokableService);
            }

            ReferenceRegistry.registerReference(EvidenceServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(EvidenceService service) {
    }
}
