package uzuzjmd.competence.service.rest;

import org.fusesource.restygwt.client.Resource;

import com.google.gwt.query.client.GQuery;

public class Dependencies {
	public Dependencies() {
		Resource resource = new Resource("you know it");
		System.out.println(resource.toString());
		GQuery gqueryNode = $(".x-tree-node-anchor");
		System.out.println(gqueryNode);

	}

	private GQuery $(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
