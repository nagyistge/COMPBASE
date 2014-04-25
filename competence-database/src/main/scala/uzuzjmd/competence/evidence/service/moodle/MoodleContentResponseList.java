package uzuzjmd.competence.evidence.service.moodle;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * DTOs für den Moodle REST-Service
 * 
 * @author julian
 * 
 */
@XmlSeeAlso(uzuzjmd.competence.evidence.service.moodle.MoodleContentResponse.class)
@XmlRootElement
public class MoodleContentResponseList extends ArrayList<MoodleContentResponse> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
