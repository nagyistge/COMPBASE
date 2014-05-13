package uzuzjmd.scalahacks

object ScalaHacksInScala {
  /**
   * Hilfsfunktion, um eine generisch spezifizierte Klasse zu instantiieren
   */
  def instantiate[A](clazz: java.lang.Class[A])(args: AnyRef*): Any = {

    val constructor = clazz.getDeclaredConstructors().toList.filter(x => x.getGenericParameterTypes().length == args.length).head
    constructor.setAccessible(true);
    try {
      val instance = constructor.newInstance(args: _*)
      return instance
    } catch {
      case e: Exception =>
        println("hello my friend")
    }
    throw new Error("error with instantiation of class" + clazz)
  }
}