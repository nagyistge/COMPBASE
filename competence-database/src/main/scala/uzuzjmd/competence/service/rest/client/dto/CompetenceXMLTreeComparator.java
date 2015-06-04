package uzuzjmd.competence.service.rest.client.dto;

public class CompetenceXMLTreeComparator extends TreeEntryComparator {

	@Override
	public int compare(AbstractTreeEntry o1, AbstractTreeEntry o2) {
		CompetenceXMLTree compTree1 = (CompetenceXMLTree) o1;
		CompetenceXMLTree compTree2 = (CompetenceXMLTree) o2;
		if (compTree1.getIsCompulsory().equals(compTree2.getIsCompulsory())) {
			return super.compare(o1, o2);
		} else if (compTree1.getIsCompulsory() && !compTree2.getIsCompulsory()) {
			return -1;
		} else if (!compTree1.getIsCompulsory() && compTree2.getIsCompulsory()) {
			return 1;
		}

		// TODO Auto-generated method stub
		return 0;
	}
}
