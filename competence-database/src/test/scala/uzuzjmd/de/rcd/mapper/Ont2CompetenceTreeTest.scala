package uzuzjmd.de.rcd.mapper

import scala.collection.JavaConverters.seqAsJavaListConverter
import org.junit.AfterClass
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import uzuzjmd.competence.mapper.gui.Ont2CompetenceTree
import uzuzjmd.competence.owl.access.CompFileUtil
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.dao.StudentRole
import uzuzjmd.competence.owl.dao.TeacherRole
import org.scalatest.junit.JUnitRunner
import org.specs2.specification.After
import org.specs2.mutable.After
import uzuzjmd.competence.owl.dao.User
import uzuzjmd.competence.owl.ontology.CompObjectProperties
import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.dao.Role

@RunWith(classOf[JUnitRunner])
class Ont2CompetenceTreeTest extends FunSuite with ShouldMatchers {

  test("The CompetenceTree should not be empty") {
    val compOntManag = new CompOntologyManager()

    compOntManag.begin()
    compOntManag.getM().validate()
    compOntManag.close()

    val mapper = new Ont2CompetenceTree(compOntManag, List.empty.asJava, List.empty.asJava, "4", false)
    val competenceTree = mapper.getComptenceTree
    competenceTree should not be ('empty)

  }

  test("The filtered CompetenceTree should not be empty") {
    val compOntManag = new CompOntologyManager()

    compOntManag.begin()
    compOntManag.getM().validate()
    compOntManag.close()

    val catchwords = "Kooperation" :: "Diagnostik" :: List.empty
    val operators = "bewerten" :: "kooperieren" :: List.empty
    val mapper = new Ont2CompetenceTree(compOntManag, catchwords.asJava, operators.asJava, "4", false)
    val competenceTree = mapper.getComptenceTree
    competenceTree should not be ('empty)

  }

  test("the operator tree should not be empty") {
    val compOntManag = new CompOntologyManager()
    val mapper = new Ont2CompetenceTree(compOntManag, List.empty.asJava, List.empty.asJava, "4", false)
    val result = mapper.getOperatorXMLTree
    result should not be ('empty)
  }

  test("the catchword tree should not be empty") {
    val compOntManag = new CompOntologyManager()
    val mapper = new Ont2CompetenceTree(compOntManag, List.empty.asJava, List.empty.asJava, "4", false)
    val result = mapper.getCatchwordXMLTree
    result should not be ('empty)
  }

  test("the singletondao should persist without error") {
    val compOntManag = new CompOntologyManager()
    compOntManag.begin()
    val studentRole = new StudentRole(compOntManag)
    studentRole.persist(true)
    studentRole.persist(false).getIndividual() should not be null
    studentRole.persist(false).getOntclass() should not be null
    val teacherRole = new TeacherRole(compOntManag)
    teacherRole.persist(true)
    teacherRole.persist(false).getIndividual() should not be null
    teacherRole.persist(false).getOntclass() should not be null
    compOntManag.close()
    showResult
  }

  test("the regular dao should persist without error") {
    val compOntManag = new CompOntologyManager()
    compOntManag.begin()
    val teacherRole = new TeacherRole(compOntManag)
    val user = new User("me", teacherRole, compOntManag)
    user.persist
    user.exists should not be false
    user.delete
    user.exists should not be true
    compOntManag.close()
    showResult
  }

  test("if a dao is linked the link should exist") {
    val compOntManag = new CompOntologyManager()
    compOntManag.begin()
    val teacherRole = new TeacherRole(compOntManag)
    val user = new User("me", teacherRole, compOntManag)
    user.persist
    user.exists should not be false
    teacherRole.hasEdge(CompObjectProperties.RoleOf, user) should not be false
    user.delete
    user.exists should not be true
    teacherRole.hasEdge(CompObjectProperties.RoleOf, user) should not be true
    user.exists should not be true
    user.persist
    teacherRole.hasEdge(CompObjectProperties.RoleOf, user) should not be false
    teacherRole.deleteEdge(CompObjectProperties.RoleOf, user)
    user.exists should not be false
    teacherRole.hasEdge(CompObjectProperties.RoleOf, user) should not be true
    compOntManag.close()
    showResult
  }

  def showResult() {
    val compOntManag = new CompOntologyManager()
    compOntManag.begin()
    val fileUtil = new CompFileUtil(compOntManag.getM())
    fileUtil.writeOntologyout()
    compOntManag.close()
  }

  //  test("A non-empty list should not be empty") {
  //    List(1, 2, 3) should not be ('empty)
  //    List("fee", "fie", "foe", "fum") should not be ('empty)
  //  }
  //
  //  test("A list's length should equal the number of elements it contains") {
  //    List() should have length (0)
  //    List(1, 2) should have length (2)
  //    List("fee", "fie", "foe", "fum") should have length (4)
  //  }
}

