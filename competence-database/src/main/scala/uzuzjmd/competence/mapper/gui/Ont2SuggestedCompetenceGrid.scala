package uzuzjmd.competence.mapper.gui

import uzuzjmd.competence.owl.dao.CourseContext
import uzuzjmd.competence.owl.dao.LearningProjectTemplate
import uzuzjmd.competence.owl.dao.LearningProjectTemplate
import uzuzjmd.competence.owl.dao.User
import uzuzjmd.competence.liferay.reflexion.SuggestedCompetenceGrid
import uzuzjmd.competence.owl.access.CompOntologyManager

object Ont2SuggestedCompetenceGrid {
  def convertToTwoDimensionalGrid(comp: CompOntologyManager, user: User, context: CourseContext, learningProjectTemplate: LearningProjectTemplate, numberOfRows: java.lang.Integer): SuggestedCompetenceGrid = {
    comp.begin()

    val includedCompetences = learningProjectTemplate.getAssociatedCompetences

    // identify most used catchwords
    val allCatchwords = includedCompetences.map(x => x.getCatchwords).flatten.groupBy(identity).mapValues(_.size).toList.sortBy(_._2).toMap.take(numberOfRows)

    // group by catchwords

    // map to triple    

    // sort by algorithm on paper

    // convert to datagrid structure for visualization

    comp.close()
    return null
  }
}