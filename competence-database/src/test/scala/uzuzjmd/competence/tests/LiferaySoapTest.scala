package uzuzjmd.competence.tests
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import com.liferay.portlet.social.service.http.SocialActivityServiceSoapProxy
import org.scalatest.junit.JUnitRunner
import scala.collection.JavaConverters._
import com.liferay.portlet.social.service.http.SocialActivityServiceSoapServiceLocator
import com.liferay.portlet.social.service.http.Portlet_Social_SocialActivityServiceSoapBindingStub
import org.apache.axis.client.Stub
import com.liferay.portal.service.http.UserServiceSoapProxy
import de.unipotsdam.elis.service.http.EvidenceServiceSoapProxy

@RunWith(classOf[JUnitRunner])
class LiferaySoapTest extends FunSuite with ShouldMatchers {

  //  test("if liferay interface is called no error should occur") {
  //
  //    System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Log4JLogger")
  //    System.setProperty("org.apache.commons.logging.LogFactory", "org.apache.commons.logging.impl.LogFactoryImpl")
  //
  //    // "http://localhost:8080/api/axis/Portlet_Social_SocialActivityService?wsdl"
  //
  //    val endpoint = new SocialActivityServiceSoapProxy("http://localhost:8080/api/axis/Portlet_Social_SocialActivityService?wsdl")
  //    endpoint.getSocialActivityServiceSoap().asInstanceOf[Stub].setUsername("elistest")
  //    endpoint.getSocialActivityServiceSoap().asInstanceOf[Stub].setPassword("eLiS14")
  //    val result = endpoint.getGroupUsersActivities(11204, -1, -1)
  //    val resultList = Nil ++ result
  //    resultList should not be ('empty)
  //    resultList.toList.head.getType()
  //    resultList.toList.foreach(x => println(x.getExtraData() + "," + x.getUserId() + "," + x.getType()))
  //
  //  }
  //
  //  test("if liferay group interface is called no error should occur") {
  //    val endpoint = new UserServiceSoapProxy("http://localhost:8080/api/axis/Portal_UserService?wsdl")
  //    endpoint.getUserServiceSoap().asInstanceOf[Stub].setUsername("elistest")
  //    endpoint.getUserServiceSoap().asInstanceOf[Stub].setPassword("eLiS14")
  //    val result = endpoint.getGroupUsers(11204)
  //    val resultList = Nil ++ result
  //    result.toList.foreach(x => println(x.getLastName()))
  //  }

  test("if liferay group activities are requested there should be no empty result  ") {

    System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Log4JLogger")
    System.setProperty("org.apache.commons.logging.LogFactory", "org.apache.commons.logging.impl.LogFactoryImpl")

    val endpoint = new EvidenceServiceSoapProxy("http://127.0.0.1:8080/competence-portlet/api/axis/Plugin_UPServices_EvidenceService?wsdl")
    endpoint.getEvidenceServiceSoap().asInstanceOf[Stub].setUsername("elistest")
    endpoint.getEvidenceServiceSoap().asInstanceOf[Stub].setPassword("eLiS14")
    val result = endpoint.getGroupEvidences(10184l)
    val resultList = Nil ++ result
    resultList should not be ('empty)
    resultList.toList.foreach(x => println(x.getTitle() + " hat gemacht: " + x.getUserName()))
  }

  //  test("The CompetenceTree should not be empty") {
  //    val compOntManag = new CompOntologyManager()
  //
  //    compOntManag.begin()
  //    compOntManag.getM().validate()
  //    compOntManag.close()
  //
  //    val mapper = new Ont2CompetenceTree(compOntManag, List.empty.asJava, List.empty.asJava, "4", false)
  //    val competenceTree = mapper.getComptenceTree
  //    competenceTree should not be ('empty)
  //
  //  }
  //
  //  test("The filtered CompetenceTree should not be empty") {
  //    val compOntManag = new CompOntologyManager()
  //
  //    compOntManag.begin()
  //    compOntManag.getM().validate()
  //    compOntManag.close()
  //
  //    val catchwords = "Kooperation" :: "Diagnostik" :: List.empty
  //    val operators = "bewerten" :: "kooperieren" :: List.empty
  //    val mapper = new Ont2CompetenceTree(compOntManag, catchwords.asJava, operators.asJava, "4", false)
  //    val competenceTree = mapper.getComptenceTree
  //    competenceTree should not be ('empty)
  //
  //  }

}

