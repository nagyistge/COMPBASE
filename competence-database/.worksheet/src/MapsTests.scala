import uzuzjmd.java.collections.MapsMagic

object MapsTests {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(106); 
  println("Welcome to the Scala worksheet");$skip(129); 
  val testInput = ("hellon", List("hellom1", "hellom2", "hellom3")) :: ("hellon1", List("hellom1", "hellom4", "hellom6")) :: Nil;System.out.println("""testInput  : List[(String, List[String])] = """ + $show(testInput ));$skip(59); 
  val stuff = MapsMagic.invertAssociation(testInput.toMap);System.out.println("""stuff  : Map[String,List[String]] = """ + $show(stuff ))}
}
