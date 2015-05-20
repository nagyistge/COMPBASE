package uzuzjmd.competence.service.rest.client;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserCourseListResponse extends ArrayList<UserCourseListItem>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserCourseListResponse() {

	}

	public void setSize(int size) {

	}

}
