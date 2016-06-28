package uzuzjmd.competence.shared.assessment;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by dehne on 30.05.2016.
 */
@XmlRootElement
public class AbstractAssessment {
    private String identifier;
    private int assessmentIndex;
    private String[] scale;
    private int minValue;
    private int maxValue;

    public AbstractAssessment() {
    }

    public AbstractAssessment(String identifier, int assessmentIndex, String[] scale) {
        this.identifier = identifier;
        this.assessmentIndex = assessmentIndex;
        this.scale = scale;
    }

    public AbstractAssessment(String identifier, int assessmentIndex, java.util.List<String> scale) {
        this.identifier = identifier;
        this.assessmentIndex = assessmentIndex;
        if (scale != null) {
            this.scale = scale.toArray(new String[0]);
        } else {
            this.scale = new String[0];
        }
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public int getAssessmentIndex() {
        return assessmentIndex;
    }

    public void setAssessmentIndex(int assessmentIndex) {
        this.assessmentIndex = assessmentIndex;
    }

    public String[] getScale() {
        return scale;
    }

    public void setScale(String[] scale) {
        this.scale = scale;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }
}
