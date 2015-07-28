package de.gimpel.kompetenz;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XML {
	public static ArrayList<Kompetenz> parseXml(String xml){
		//neue Instanz der DocumentBuilderFactory holen
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		ArrayList<Kompetenz> kompetenzen = null;
		try {
			
			//neuen DocumentBuilder aus der DocumentBuilderFactory holen
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			//InputSource aus dem zu parsenden XML erstellen
			InputSource is = new InputSource(new StringReader(xml));
			
			//mit dem Builder die DOM Repräsentation erhalten
			Document dom = db.parse(is);
			
			//Dokument parsen
			kompetenzen = parseDocument(dom);
			
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		//Adressobjektliste zurückgeben
		return kompetenzen;
	}
	
	//Adressen aus der DOM Repräsentation parsen
	private static ArrayList<Kompetenz> parseDocument(Document dom){
		ArrayList<Kompetenz> koList = new ArrayList<Kompetenz>();
		NodeList nl;
		
		//das Wurzelelement holen
		Element docEl = dom.getDocumentElement();

		//eine Nodelist der Elemente gml:featureMember holen
		nl = docEl.getElementsByTagName("competence");
		
		if(nl != null) {
			for(int i=0;i<nl.getLength();i++) {
				Element e = (Element)nl.item(i);
				String name = e.getAttribute("name");
				Kompetenz ko = new Kompetenz(name,false);
				koList.add(ko);
			}
		}
		//Adressobjektliste zurückgeben
		return koList;
	}
}