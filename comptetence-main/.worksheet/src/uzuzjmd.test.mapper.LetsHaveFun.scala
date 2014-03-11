package uzuzjmd.test.mapper

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import scala.collection.JavaConverters._


object LetsHaveFun {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(209); 
  println("Welcome to the Scala worksheet");$skip(21); 

	val x  = new Spike;System.out.println("""x  : uzuzjmd.test.mapper.LetsHaveFun.Spike = """ + $show(x ));$skip(20); val res$0 = 
	x.dosemthingbefore;System.out.println("""res0: String = """ + $show(res$0))}

  class Spike {
  	@Before
  	def dosemthingbefore() : String = {
  		  "hello"
  	}
  }
}
