package uzuzjmd.competence.liferay;

import java.util.Date;
import java.util.List;

import javax.portlet.PortletContext;
import javax.portlet.PortletSession;

import org.jsoup.Jsoup;

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
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.WrappedPortletSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import de.unipotsdam.elis.NoSuchEvidenceException;
import de.unipotsdam.elis.model.Evidence;
import de.unipotsdam.elis.service.EvidenceLocalServiceUtil;

@Theme("competencetheme")
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
		
		String serverPath = "http://"+themeDisplay.getServerName() + ":"+themeDisplay.getServerPort()+request.getContextPath();
		
		System.out.println(serverPath);
		
		String user = "default";
		try {
			user = UserLocalServiceUtil.getUser(PrincipalThreadLocal.getUserId()).getLogin();
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		BrowserFrame browser = new BrowserFrame("", new ExternalResource(serverPath+"/Competence_webapp.html?user="+user));
		browser.setWidth("1200px");
		browser.setHeight("400px");				
	
		layout.addComponent(browser);
		
		updateActivities(request, themeDisplay);
	}
	


	private void updateActivities(VaadinRequest request, ThemeDisplay themeDisplay) {
		
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
						evidence = EvidenceLocalServiceUtil.getEvidence(socialActivity.getActivityId());
					}
					catch (NoSuchEvidenceException e) {					
						evidence = EvidenceLocalServiceUtil.createEvidence(socialActivity.getActivityId());											
					}
					evidence.setLink(interpretedActivity.getLink());
					evidence.setTitle(socialActivity.getExtraData().split(":")[1]);
					evidence.setActivityTyp(cleanActivityTyp(interpretedActivity));
					evidence.setCreateDate(new Date(socialActivity.getCreateDate()));			
					evidence.setGroupId(serviceContext.getScopeGroupId());
					evidence.setCompanyId(serviceContext.getCompanyId());
					evidence.setUserId(socialActivity.getUserId());
					evidence.setUserName(UserLocalServiceUtil.getUser(socialActivity.getUserId()).getFullName());					
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
	
	private String cleanActivityTyp (SocialActivityFeedEntry input) {		
		return Jsoup.parse(input.getTitle()).getElementsByTag("body").text().split(",")[0].split(" a ")[1];
	}

	
}
