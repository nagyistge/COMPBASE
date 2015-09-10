package de.unipotsdam.anh.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import uzuzjmd.competence.shared.DESCRIPTORSETType;
import uzuzjmd.competence.shared.DESCRIPTORType;
import uzuzjmd.competence.shared.dto.EPOSTypeWrapper;
import uzuzjmd.competence.shared.dto.Graph;
import de.unipotsdam.anh.dao.LearningTemplateDao;

public class LearningTemplateDaoTest {

	@Test
	public void testcreateTemplate() {
		System.out.println("##### Test createTemplate #####");
		
		Graph graph = new Graph();
		Map<Integer, String> nodeIdMap = new HashMap<Integer, String>();
		nodeIdMap.put(1, "test graph");
		graph.setNodeIdValues(nodeIdMap);
		
		Graph uni = LearningTemplateDao.getGraphFromCourse("university/");
		uni.mergeWith(graph);
		
		System.out.println(LearningTemplateDao.createTemplate("11 Sprachkompetenz, Univ. (ELC, DE)", uni));
	}

	@Test
	public void testFindAll() {
		System.out.println("##### Test findAll #####");
		for(String s : LearningTemplateDao.findAll().getData()) {
			System.out.println(s);
		}
	}
	
	@Test
	public void testCreateEpos() {
		System.out.println("##### Test createEpos #####");
		
		List<DESCRIPTORSETType> eposCompetences = new ArrayList<DESCRIPTORSETType>();
		DESCRIPTORSETType des1 = new DESCRIPTORSETType();
		des1.setNAME("test");
		eposCompetences.add(des1);
		
		DESCRIPTORType des2 = new DESCRIPTORType();
		des2.setNAME("test DESCRIPTORType");
		des2.setCOMPETENCE("programmieren");
		des2.setEVALUATIONS("gar nicht; ausreichend; befriedigend; gut");
		des2.setGOAL((byte) 1);
		des2.setLEVEL("A1");
		
		des1.getDESCRIPTOR().add(des2);

		EPOSTypeWrapper typeWrapper = new EPOSTypeWrapper();
		
		typeWrapper.setEposCompetences(eposCompetences.toArray(new DESCRIPTORSETType[0]));
		
		System.out.println(typeWrapper.getEposCompetences().length);
		
		LearningTemplateDao.createEpos(typeWrapper);
	}
	
	@Test
	public void testGetGraphFromCourse() {
		System.out.println("##### Test getGraphFromCourse #####");
		System.out.println(LearningTemplateDao.getGraphFromCourse("university/"));
	}
}
