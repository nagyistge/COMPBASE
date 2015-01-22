/**
 * SocialActivityServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.liferay.portlet.social.service.http;

public interface SocialActivityServiceSoap extends java.rmi.Remote {
    public int getActivitiesCount(long classNameId) throws java.rmi.RemoteException;
    public int getActivitiesCount(long mirrorActivityId, long classNameId, long classPK) throws java.rmi.RemoteException;
    public int getActivitiesCount(long mirrorActivityId, java.lang.String className, long classPK) throws java.rmi.RemoteException;
    public int getActivitiesCount(java.lang.String className) throws java.rmi.RemoteException;
    public com.liferay.portlet.social.model.SocialActivitySoap[] getActivities(long classNameId, int start, int end) throws java.rmi.RemoteException;
    public com.liferay.portlet.social.model.SocialActivitySoap[] getActivities(long mirrorActivityId, long classNameId, long classPK, int start, int end) throws java.rmi.RemoteException;
    public com.liferay.portlet.social.model.SocialActivitySoap[] getActivities(long mirrorActivityId, java.lang.String className, long classPK, int start, int end) throws java.rmi.RemoteException;
    public com.liferay.portlet.social.model.SocialActivitySoap[] getActivities(java.lang.String className, int start, int end) throws java.rmi.RemoteException;
    public com.liferay.portlet.social.model.SocialActivitySoap[] getActivitySetActivities(long activitySetId, int start, int end) throws java.rmi.RemoteException;
    public com.liferay.portlet.social.model.SocialActivitySoap getActivity(long activityId) throws java.rmi.RemoteException;
    public int getGroupActivitiesCount(long groupId) throws java.rmi.RemoteException;
    public com.liferay.portlet.social.model.SocialActivitySoap[] getGroupActivities(long groupId, int start, int end) throws java.rmi.RemoteException;
    public int getGroupUsersActivitiesCount(long groupId) throws java.rmi.RemoteException;
    public com.liferay.portlet.social.model.SocialActivitySoap[] getGroupUsersActivities(long groupId, int start, int end) throws java.rmi.RemoteException;
    public com.liferay.portlet.social.model.SocialActivitySoap getMirrorActivity(long mirrorActivityId) throws java.rmi.RemoteException;
    public int getOrganizationActivitiesCount(long organizationId) throws java.rmi.RemoteException;
    public com.liferay.portlet.social.model.SocialActivitySoap[] getOrganizationActivities(long organizationId, int start, int end) throws java.rmi.RemoteException;
    public int getOrganizationUsersActivitiesCount(long organizationId) throws java.rmi.RemoteException;
    public com.liferay.portlet.social.model.SocialActivitySoap[] getOrganizationUsersActivities(long organizationId, int start, int end) throws java.rmi.RemoteException;
    public int getRelationActivitiesCount(long userId) throws java.rmi.RemoteException;
    public int getRelationActivitiesCount(long userId, int type) throws java.rmi.RemoteException;
    public com.liferay.portlet.social.model.SocialActivitySoap[] getRelationActivities(long userId, int start, int end) throws java.rmi.RemoteException;
    public com.liferay.portlet.social.model.SocialActivitySoap[] getRelationActivities(long userId, int type, int start, int end) throws java.rmi.RemoteException;
    public int getUserActivitiesCount(long userId) throws java.rmi.RemoteException;
    public com.liferay.portlet.social.model.SocialActivitySoap[] getUserActivities(long userId, int start, int end) throws java.rmi.RemoteException;
    public int getUserGroupsActivitiesCount(long userId) throws java.rmi.RemoteException;
    public com.liferay.portlet.social.model.SocialActivitySoap[] getUserGroupsActivities(long userId, int start, int end) throws java.rmi.RemoteException;
    public int getUserGroupsAndOrganizationsActivitiesCount(long userId) throws java.rmi.RemoteException;
    public com.liferay.portlet.social.model.SocialActivitySoap[] getUserGroupsAndOrganizationsActivities(long userId, int start, int end) throws java.rmi.RemoteException;
    public int getUserOrganizationsActivitiesCount(long userId) throws java.rmi.RemoteException;
    public com.liferay.portlet.social.model.SocialActivitySoap[] getUserOrganizationsActivities(long userId, int start, int end) throws java.rmi.RemoteException;
}
