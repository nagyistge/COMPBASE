package uzuzjmd.competence.liferay;

import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;

import uzuzjmd.competence.gui.client.LmsContextFactory;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.service.SocialActivityInterpreterLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import de.unipotsdam.elis.NoSuchEvidenceException;
import de.unipotsdam.elis.model.Evidence;
import de.unipotsdam.elis.service.EvidenceLocalServiceUtil;

@StyleSheet({		
		"vaadin://competencecss/progressTab.css",
		"vaadin://js/resources/css/ext-all.css",
		"vaadin://competencecss/activity.css",
		"vaadin://competencecss/preview.css",
		"vaadin://competencecss/competenceSelection.css",
		"vaadin://competencecss/requirementTab.css",
		"vaadin://competencecss/evidencePopup.css",
		"vaadin://competencecss/graphTab.css", 
		"vaadin://js/columntree/column-tree.css", 
	    "vaadin://gwtcss/GwtExt.css", 
	    "vaadin://gwtcss/css/bootstrap.min.css",
	    "vaadin://gwtcss/css/font-awesome.min.css",
	    "vaadin://gwtcss/gwt/clean/clean.css",
	    "vaadin://gwtcss/gwt/standard/standard.css",
		})
@JavaScript({
	    "vaadin://js/adapter/yui/yui-utilities.js",
		"vaadin://js/adapter/yui/ext-yui-adapter.js",
		"vaadin://js/ext-all.js",
		"vaadin://js/columntree/ColumnNodeUI.js",
		"vaadin://js/jquery11.js",
		"vaadin://js/jquery11migrate.js",
		"vaadin://js/jquery.qtip-1.0.0-rc3.min.js",
		"vaadin://js/competencejs/preview.js",
		"http://html5shim.googlecode.com/svn/trunk/html5.js",		
		"vaadin://js/competencejs/zeige_kompetenz.js",
		"vaadin://js/dracula/js/raphael-min2.js",
		"vaadin://js/dracula/js/dracula_graffle.js",
		"vaadin://js/dracula/js/dracula_graph.js",
		"vaadin://js/competencejs/graphLayouter.js",
//		"vaadin://js/gwtjs/competence_webapp.nocache.js"		
})
//@Theme("competencetheme")
@SuppressWarnings("serial")
@Widgetset("uzuzjmd.competence.liferay.AppWidgetSet")
public class CompetencePortletUI extends UI {

	private static Log log = LogFactoryUtil.getLog(CompetencePortletUI.class);

	@Override
	protected void init(VaadinRequest request) {					
		
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);
				

		final ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);

		String serverPath = "http://" + themeDisplay.getServerName() + ":"
				+ themeDisplay.getServerPort() + request.getContextPath();

		System.out.println(serverPath);
		ThemeResource resource = new ThemeResource("icons/competence.png");
		System.out.println(resource.getResourceId());
		
		
		String user = "default";
		try {
			user = UserLocalServiceUtil.getUser(
					PrincipalThreadLocal.getUserId()).getLogin();
		} catch (PortalException e) {			
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		
		long courseId = themeDisplay.getScopeGroupId();

				
		LmsContextFactory contextFactory = new LmsContextFactory(courseId, null, null, "teacher", user);
		CompetenceUIVaadinComponent competenceUI = new CompetenceUIVaadinComponent(contextFactory);					
		layout.addComponent(competenceUI);	
	
	
//		layout.addComponent(new Label("Hendrik ist der Gott des Powerpoints und des Googlens"));

		updateActivities(request, themeDisplay);
	}

	private void updateActivities(VaadinRequest request,
			ThemeDisplay themeDisplay) {

		com.liferay.portal.service.ServiceContext serviceContext;
		try {
			serviceContext = ServiceContextFactory.getInstance(themeDisplay
					.getRequest());

			List<SocialActivity> activities;
			activities = SocialActivityLocalServiceUtil.getGroupActivities(
					serviceContext.getScopeGroupId(), -1, -1);
			for (SocialActivity socialActivity : activities) {
				SocialActivityFeedEntry interpretedActivity = SocialActivityInterpreterLocalServiceUtil
						.interpret(StringPool.BLANK, socialActivity,
								serviceContext);
				try {
					Evidence evidence = null;
					try {
						evidence = EvidenceLocalServiceUtil
								.getEvidence(socialActivity.getActivityId());
					} catch (NoSuchEvidenceException e) {
						evidence = EvidenceLocalServiceUtil
								.createEvidence(socialActivity.getActivityId());
					}
					evidence.setLink(interpretedActivity.getLink());
					evidence.setTitle(socialActivity.getExtraData().split(":")[1]);
					evidence.setActivityTyp(cleanActivityTyp(interpretedActivity));
					evidence.setCreateDate(new Date(socialActivity
							.getCreateDate()));
					evidence.setGroupId(serviceContext.getScopeGroupId());
					evidence.setCompanyId(serviceContext.getCompanyId());
					evidence.setUserId(socialActivity.getUserId());
					evidence.setUserName(UserLocalServiceUtil.getUser(
							socialActivity.getUserId()).getFullName());
					EvidenceLocalServiceUtil.updateEvidence(evidence);
				} catch (NullPointerException e) {

				}

			}

		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (PortalException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private String cleanActivityTyp(SocialActivityFeedEntry input) {
		return Jsoup.parse(input.getTitle()).getElementsByTag("body").text()
				.split(",")[0].split(" a ")[1];
	}
	
	


}
