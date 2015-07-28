package com.kompetenzen.unipotsdam.kompetenzenapp;

import android.content.Context;
import android.util.SparseArray;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


/**
 * Created by Manuel on 07/07/2015.
 */

public class XMLParser {
    private String response;
    Context context;

    public Hashtable<String,int[]> map = new Hashtable<String,int[]>();
    

    public XMLParser(String resp, Context con) {
        context= con;
        this.response = resp;
    }


    /* This method makes the XML document which is formed just by one
        line, easier to read
     */
    public String preProcess(String entry){
        String newResponse= "";
        String string1= "\"Kompetenz\">";
        String string2= "</isCompulsory>";
        String string3= "</children>";
        String string4= "</competence>";
        Pattern pat1= Pattern.compile(string1);
        Pattern pat2= Pattern.compile(string2);
        Pattern pat3= Pattern.compile(string3);
        Pattern pat4= Pattern.compile(string4);
        Matcher mat1 = pat1.matcher(entry);
        newResponse= mat1.replaceAll("\"Kompetenz\">\n");
        Matcher mat2 = pat2.matcher(newResponse);
        newResponse= mat2.replaceAll("</isCompulsory>\n");
        Matcher mat3 = pat3.matcher(newResponse);
        newResponse= mat3.replaceAll("</children>\n");
        Matcher mat4 = pat4.matcher(newResponse);
        newResponse= mat4.replaceAll("</competence>\n");
        return newResponse;
    }


    public SparseArray<CompetenceGroup> parse() {

        SparseArray<CompetenceGroup> competences = new SparseArray<CompetenceGroup>();
        Competence competence= new Competence();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        int groupCounter= 0;

        map= new Hashtable<String,int[]>();

        try {
            builder = factory.newDocumentBuilder();
            InputSource ip= new InputSource(new StringReader(this.response));
            ip.setEncoding("UTF-8");
            Document document = builder.parse(ip);
            document.getDocumentElement().normalize();
            System.out.print("\n Root element: " + document.getDocumentElement().getNodeName());
            NodeList parentList= document.getChildNodes();
            Node parentNode= parentList.item(0);
            NodeList RootList= parentNode.getChildNodes();
            Node rootNode= RootList.item(0);
            NodeList nList= rootNode.getChildNodes();
            for (int i=0; i < nList.getLength(); i++){
                Node nNode= nList.item(i);
                String nodeNAME= nNode.getNodeName();
                if (!nodeNAME.equals("competence"))continue;
                Element element= (Element)nNode;
                String comNAME= element.getAttribute("name");
                competence.setName(comNAME);
                String compulsory= element.getElementsByTagName("isCompulsory").item(0).getTextContent();
                competence.setIsCompulsory(compulsory.equals("true"));
                CompetenceGroup compGroup= new CompetenceGroup(competence);
                competence= new Competence();
                competences.append(groupCounter, compGroup);
                int[] valueParent= {groupCounter,-1};
                map.put(comNAME,valueParent);
                groupCounter++;
                NodeList secondList= nNode.getChildNodes();
                int childCounter= 0;
                for(int j=0; j< secondList.getLength(); j++){
                    Node secondNode= secondList.item(j);
                    String childNodeNAME= secondNode.getNodeName();
                    if (!childNodeNAME.equals("competence"))continue;
                    Element childElement= (Element)secondNode;
                    String childName= childElement.getAttribute("name");
                    int[] childVal= {groupCounter - 1,childCounter};
                    map.put(childName,childVal);
                    childCounter++;
                    competence.setName(childName);
                    String childCompulsory= childElement.getElementsByTagName("isCompulsory").item(0).getTextContent();
                    competence.setUpper_competence(false);
                    competence.setIsCompulsory(childCompulsory.equals("true"));
                    compGroup.children.add(competence);
                    competence= new Competence();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return competences;
    }
}
