package uzuzjmd.test.mapper

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import scala.collection.JavaConverters._


object LetsHaveFun {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

	val x  = new Spike                        //> x  : uzuzjmd.test.mapper.LetsHaveFun.Spike = uzuzjmd.test.mapper.LetsHaveFun
                                                  //| $Spike@151a0f60
	x.dosemthingbefore                        //> res0: String = hello

  class Spike {
  	@Before
  	def dosemthingbefore() : String = {
  		  "hello"
  	}
  }
}