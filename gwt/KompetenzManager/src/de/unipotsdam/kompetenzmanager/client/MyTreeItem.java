package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;

public class MyTreeItem extends TreeItem {
	public MyTreeItem(Widget widget) {
		this.addItem(widget);
	}

	public MyTreeItem(String paper) {
		super(paper);
	}

	@Override
	public int hashCode() {
		return this.getText().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof String) {
			return this.getText().equals(obj);			
		} else {
			MyTreeItem other = (MyTreeItem) obj;
			return this.getText().equals(other.getText());
		}
	}
}
