package uzuzjmd.competence.liferay.reflexion;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@ManagedBean
@RequestScoped
@FacesConverter("learningTemplateValidator")
public class learningTemplateValidator implements Validator {

	@ManagedProperty("#{LearningTemplatesFullSet}")
	private LearningTemplatesFullSet allLearningTemplates;
	
	public LearningTemplatesFullSet getAllLearningTemplates() {
		return allLearningTemplates;
	}
	
	public void setAllLearningTemplates(
			LearningTemplatesFullSet allLearningTemplates) {
		this.allLearningTemplates = allLearningTemplates;
	}
	
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (!allLearningTemplates.getLearningTemplates().contains(value)) {
			System.out.println("throwing conversion exception:" + value);
			throw new ValidatorException(new FacesMessage("EingabeFehler: Es gibt kein Lernziel mit diesem Namen"));
		}
		System.out.println("not throwing conversion exception:" + value);		
	}

}
