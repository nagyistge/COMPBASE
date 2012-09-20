package de.unipotsdam.kompetenzmanager.shared;

public class GeometryUtil {
	public static Boolean inRange(Integer x1, Integer y1, Integer x2, Integer y2) {
		if (Math.abs(x1-x2) > 60 || (Math.abs(y1-y2)) > 60) {
			 return false;
		}  else {
			return true;
		}
	}
	
}
