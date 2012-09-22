package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.user.client.ui.IsTreeItem;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;

public class MyTreeItem extends TreeItem {
	private LiteratureView literatureView;
	public String paper;

	public MyTreeItem(Widget widget, LiteratureView literatureView) {
		this.addItem(widget);
		this.literatureView = literatureView;
	}

	public MyTreeItem(String paper, LiteratureView literatureView) {
		super(new MyTreeItemUI(paper, literatureView));
		this.literatureView = literatureView;
		this.paper = paper;
	}

	@Override
	public String getText() {
		return paper;
	}

	@Override
	public int hashCode() {
		return this.getText().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		MyTreeItem other = (MyTreeItem) obj;
		return this.getText().equals(other.getText());
	}

	public void updateBindings() {
		this.literatureView.showLiteratureEntry(this);
	}
}
