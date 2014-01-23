package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.user.client.ui.Label;
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
		super();
//		super(new MyTreeItemUI(paper, literatureView, false));
//		this.setWidget(new MyTreeItemUI(paper, literatureView, false, this));	
		this.setWidget(new Label(paper));
		this.literatureView = literatureView;
		this.paper = paper;
	}
	
	public MyTreeItem(String paper, LiteratureView literatureView, Boolean isClickBoolean) {
//		super(new MyTreeItemUI(paper, literatureView, isClickBoolean));
		super();
		if (isClickBoolean) {
		this.setWidget((new MyTreeItemUI(paper, literatureView, isClickBoolean, this)));
		} else {
			this.setWidget(new Label(paper));
		}
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
