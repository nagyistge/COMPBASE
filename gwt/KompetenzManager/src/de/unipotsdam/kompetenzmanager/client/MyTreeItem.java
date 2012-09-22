package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;

public class MyTreeItem extends TreeItem{
	public MyTreeItem(Widget widget) {
		this.addItem(widget);
	}
	
	@Override
	public int hashCode() {
		return this.getText().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.getText().equals(((MyTreeItem)obj).getText());
	}
}
