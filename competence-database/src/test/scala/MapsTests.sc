import uzuzjmd.java.collections.MapsMagic

object MapsTests {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val testInput = ("hellon", List("hellom1", "hellom2", "hellom3")) :: ("hellon1", List("hellom1", "hellom4", "hellom6")) :: Nil
                                                  //> testInput  : List[(String, List[String])] = List((hellon,List(hellom1, hello
                                                  //| m2, hellom3)), (hellon1,List(hellom1, hellom4, hellom6)))
  val stuff = MapsMagic.invertAssociation(testInput.toMap)
                                                  //> stuff  : Map[String,List[String]] = Map(hellom2 -> List(hellon), hellom6 -> 
                                                  //| List(hellon1), hellom3 -> List(hellon), hellom4 -> List(hellon1), hellom1 ->
                                                  //|  List(hellon, hellon1))
}