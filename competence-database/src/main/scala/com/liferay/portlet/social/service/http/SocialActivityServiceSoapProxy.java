package com.liferay.portlet.social.service.http;

public class SocialActivityServiceSoapProxy implements com.liferay.portlet.social.service.http.SocialActivityServiceSoap {
	private String _endpoint = null;
	private com.liferay.portlet.social.service.http.SocialActivityServiceSoap socialActivityServiceSoap = null;

	public SocialActivityServiceSoapProxy() {
		_initSocialActivityServiceSoapProxy();
	}

	public SocialActivityServiceSoapProxy(String endpoint) {
		_endpoint = endpoint;

		_initSocialActivityServiceSoapProxy();
	}

	private void _initSocialActivityServiceSoapProxy() {
		try {
			setSocialActivityServiceSoap((new com.liferay.portlet.social.service.http.SocialActivityServiceSoapServiceLocator()).getPortlet_Social_SocialActivityService());
			if (getSocialActivityServiceSoap() != null) {
				if (_endpoint != null)
					((javax.xml.rpc.Stub) getSocialActivityServiceSoap())._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
				else
					_endpoint = (String) ((javax.xml.rpc.Stub) getSocialActivityServiceSoap())._getProperty("javax.xml.rpc.service.endpoint.address");
			}

		} catch (javax.xml.rpc.ServiceException serviceException) {
		}
	}

	public String getEndpoint() {
		return _endpoint;
	}

	public void setEndpoint(String endpoint) {
		_endpoint = endpoint;
		if (getSocialActivityServiceSoap() != null)
			((javax.xml.rpc.Stub) getSocialActivityServiceSoap())._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

	}

	public com.liferay.portlet.social.service.http.SocialActivityServiceSoap getSocialActivityServiceSoap() {
		if (socialActivityServiceSoap == null)
			_initSocialActivityServiceSoapProxy();
		return socialActivityServiceSoap;
	}

	public int getActivitiesCount(long classNameId) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getActivitiesCount(classNameId);
	}

	public int getActivitiesCount(long mirrorActivityId, long classNameId, long classPK) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getActivitiesCount(mirrorActivityId, classNameId, classPK);
	}

	public int getActivitiesCount(long mirrorActivityId, java.lang.String className, long classPK) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getActivitiesCount(mirrorActivityId, className, classPK);
	}

	public int getActivitiesCount(java.lang.String className) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getActivitiesCount(className);
	}

	public com.liferay.portlet.social.model.SocialActivitySoap[] getActivities(long classNameId, int start, int end) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getActivities(classNameId, start, end);
	}

	public com.liferay.portlet.social.model.SocialActivitySoap[] getActivities(long mirrorActivityId, long classNameId, long classPK, int start, int end) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getActivities(mirrorActivityId, classNameId, classPK, start, end);
	}

	public com.liferay.portlet.social.model.SocialActivitySoap[] getActivities(long mirrorActivityId, java.lang.String className, long classPK, int start, int end) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getActivities(mirrorActivityId, className, classPK, start, end);
	}

	public com.liferay.portlet.social.model.SocialActivitySoap[] getActivities(java.lang.String className, int start, int end) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getActivities(className, start, end);
	}

	public com.liferay.portlet.social.model.SocialActivitySoap[] getActivitySetActivities(long activitySetId, int start, int end) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getActivitySetActivities(activitySetId, start, end);
	}

	public com.liferay.portlet.social.model.SocialActivitySoap getActivity(long activityId) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getActivity(activityId);
	}

	public int getGroupActivitiesCount(long groupId) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getGroupActivitiesCount(groupId);
	}

	public com.liferay.portlet.social.model.SocialActivitySoap[] getGroupActivities(long groupId, int start, int end) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getGroupActivities(groupId, start, end);
	}

	public int getGroupUsersActivitiesCount(long groupId) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getGroupUsersActivitiesCount(groupId);
	}

	public com.liferay.portlet.social.model.SocialActivitySoap[] getGroupUsersActivities(long groupId, int start, int end) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getGroupUsersActivities(groupId, start, end);
	}

	public com.liferay.portlet.social.model.SocialActivitySoap getMirrorActivity(long mirrorActivityId) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getMirrorActivity(mirrorActivityId);
	}

	public int getOrganizationActivitiesCount(long organizationId) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getOrganizationActivitiesCount(organizationId);
	}

	public com.liferay.portlet.social.model.SocialActivitySoap[] getOrganizationActivities(long organizationId, int start, int end) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getOrganizationActivities(organizationId, start, end);
	}

	public int getOrganizationUsersActivitiesCount(long organizationId) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getOrganizationUsersActivitiesCount(organizationId);
	}

	public com.liferay.portlet.social.model.SocialActivitySoap[] getOrganizationUsersActivities(long organizationId, int start, int end) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getOrganizationUsersActivities(organizationId, start, end);
	}

	public int getRelationActivitiesCount(long userId) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getRelationActivitiesCount(userId);
	}

	public int getRelationActivitiesCount(long userId, int type) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getRelationActivitiesCount(userId, type);
	}

	public com.liferay.portlet.social.model.SocialActivitySoap[] getRelationActivities(long userId, int start, int end) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getRelationActivities(userId, start, end);
	}

	public com.liferay.portlet.social.model.SocialActivitySoap[] getRelationActivities(long userId, int type, int start, int end) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getRelationActivities(userId, type, start, end);
	}

	public int getUserActivitiesCount(long userId) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getUserActivitiesCount(userId);
	}

	public com.liferay.portlet.social.model.SocialActivitySoap[] getUserActivities(long userId, int start, int end) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getUserActivities(userId, start, end);
	}

	public int getUserGroupsActivitiesCount(long userId) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getUserGroupsActivitiesCount(userId);
	}

	public com.liferay.portlet.social.model.SocialActivitySoap[] getUserGroupsActivities(long userId, int start, int end) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getUserGroupsActivities(userId, start, end);
	}

	public int getUserGroupsAndOrganizationsActivitiesCount(long userId) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getUserGroupsAndOrganizationsActivitiesCount(userId);
	}

	public com.liferay.portlet.social.model.SocialActivitySoap[] getUserGroupsAndOrganizationsActivities(long userId, int start, int end) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getUserGroupsAndOrganizationsActivities(userId, start, end);
	}

	public int getUserOrganizationsActivitiesCount(long userId) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getUserOrganizationsActivitiesCount(userId);
	}

	public com.liferay.portlet.social.model.SocialActivitySoap[] getUserOrganizationsActivities(long userId, int start, int end) throws java.rmi.RemoteException {
		if (getSocialActivityServiceSoap() == null)
			_initSocialActivityServiceSoapProxy();
		return getSocialActivityServiceSoap().getUserOrganizationsActivities(userId, start, end);
	}

	private void setSocialActivityServiceSoap(com.liferay.portlet.social.service.http.SocialActivityServiceSoap socialActivityServiceSoap) {
		this.socialActivityServiceSoap = socialActivityServiceSoap;
	}

}