package uzuzjmd.competence.gui.client.viewcontroller;

import java.util.Set;

import com.github.gwtbootstrap.client.ui.DropdownButton;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class ContextSelectionController {
	public static DropdownButton courseSelectionButton;

	public static String firstItem = null;

	public static void initContextController(Set<String> set) {
		GWT.log("initializeing ContextController with " + set);
		courseSelectionButton = new DropdownButton("Kurse");
		firstItem = null;

		for (final String selectedCourse : set) {
			if (firstItem == null) {
				firstItem = selectedCourse;
			}
			NavLink button = new NavLink();
			button.setText(selectedCourse);
			button.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					Controller.contextFactory.setCourseContext(selectedCourse);
					Controller.reloadController.reload();
				}
			});
			courseSelectionButton.add(button);
		}
		Controller.tabbedView.updateCourseSelectionPanel();
	}

	public static String getSelectedCourseContext() {
		if (courseSelectionButton.getLastSelectedNavLink() != null) {
			return courseSelectionButton.getLastSelectedNavLink().getText();
		} else {
			return firstItem;
		}

	}
}
