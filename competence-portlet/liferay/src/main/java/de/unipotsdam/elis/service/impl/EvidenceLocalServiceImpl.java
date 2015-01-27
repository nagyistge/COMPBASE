package de.unipotsdam.elis.service.impl;

import de.unipotsdam.elis.service.base.EvidenceLocalServiceBaseImpl;

/**
 * The implementation of the evidence local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link de.unipotsdam.elis.service.EvidenceLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see de.unipotsdam.elis.service.base.EvidenceLocalServiceBaseImpl
 * @see de.unipotsdam.elis.service.EvidenceLocalServiceUtil
 */
public class EvidenceLocalServiceImpl extends EvidenceLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link de.unipotsdam.elis.service.EvidenceLocalServiceUtil} to access the evidence local service.
     */
	
	public void helloWorld() {
		System.out.println("hello world");
	}
}
