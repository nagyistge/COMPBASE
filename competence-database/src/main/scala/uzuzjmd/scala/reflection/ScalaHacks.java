package uzuzjmd.scala.reflection;

import java.util.List;

import com.hp.hpl.jena.ontology.Individual;

public class ScalaHacks {
	public static Object[] getObjectArray(Object... objects) {
		return new Object[] { objects };
	}

	public Individual[] getIndividualArray() {
		return new Individual[0];
	}

	public Object[] getBiggerObjectArray(List<java.lang.Object> objects) {
		Object[] biggerOne = new Object[objects.size() + 1];
		biggerOne[0] = objects.get(0);
		biggerOne[1] = objects.get(1);
		return biggerOne;
	}
}
