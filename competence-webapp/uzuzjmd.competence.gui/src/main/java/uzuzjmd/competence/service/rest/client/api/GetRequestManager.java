package uzuzjmd.competence.service.rest.client.api;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.TextCallback;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HasText;

public class GetRequestManager extends RestClientManager {
	public void updateRequirementTextFieldContent(final HasText widget) {
		GWT.log("Initiating requirement textfield");
		Resource resource = new Resource(
				RestUrlFactory.getRequirementTextFieldUrl());
		resource.get().send(new TextCallback() {

			@Override
			public void onSuccess(Method arg0, String arg1) {
				widget.setText(arg1);
			}

			@Override
			public void onFailure(Method arg0, Throwable arg1) {
				GWT.log("could not get requirements for course");
			}
		});
		GWT.log("Initiated requirement textfield");
	}
}
