package de.unipotsdam.elis.service.impl;

import java.util.List;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

import de.unipotsdam.elis.model.Evidence;
import de.unipotsdam.elis.service.EvidenceLocalServiceUtil;
import de.unipotsdam.elis.service.EvidenceServiceUtil;
import de.unipotsdam.elis.service.base.EvidenceServiceBaseImpl;

/**
 * The implementation of the evidence remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link de.unipotsdam.elis.service.EvidenceService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see de.unipotsdam.elis.service.base.EvidenceServiceBaseImpl
 * @see de.unipotsdam.elis.service.EvidenceServiceUtil
 */
public class EvidenceServiceImpl extends EvidenceServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link de.unipotsdam.elis.service.EvidenceServiceUtil} to access the evidence remote service.
     */
	
	public void helloWorld() {
		System.out.println("hello world");
	}
	
	public List<Evidence> getGroupEvidences(long groupId) {
		DynamicQuery dynamicQuery = new DynamicQueryFactoryUtil().forClass(Evidence.class, PortletClassLoaderUtil.getClassLoader());
		dynamicQuery.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		try {
			return EvidenceLocalServiceUtil.dynamicQuery(dynamicQuery);
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
