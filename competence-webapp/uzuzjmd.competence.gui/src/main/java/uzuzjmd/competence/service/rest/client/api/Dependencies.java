package uzuzjmd.competence.service.rest.client.api;

import org.fusesource.restygwt.client.Resource;

import com.google.gwt.query.client.GQuery;

/**
 * useless class that is only here to load the dependencies
 * 
 * @author julian
 * 
 */
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