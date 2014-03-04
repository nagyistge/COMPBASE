package uzuzjmd.service.competence;

import java.util.ArrayList;

import javax.jws.WebService;

import uzuzjmd.rcd.generated.Rdceo;

@WebService(endpointInterface="uzuzjmd.service.competence.CompetenceService")
public class CompetenceServiceImpl implements CompetenceService {

	
	@Override
	public Rdceo[] getCompetences() {
		System.out.println("Competences queried");
		return null;
	}

	@Override
	public void insertCompetence(Rdceo rcdeo) {
		ArrayList<Rdceo> list = new ArrayList<Rdceo>();
		list.add(rcdeo);
		//uzuzjmd.console.util.ConsoleOut.printRcdeoCompetences(list);
		System.out.println("Competences inserted");				
	}

}
