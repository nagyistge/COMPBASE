package uzuzjmd.competence.owl.access.transactions

import uzuzjmd.competence.owl.access.CompOntologyManager

/**
 * @author dehne
 */
trait TDBWriteTransactional[A, T] {
    val comp = new CompOntologyManager
    
    def execute(f: A => T, g: A ) : T = {
      comp.begin 
      comp.getM.enterCriticalSection(false)
      try {
        f(g)
      } finally {
        comp.getM.leaveCriticalSection()
        comp.end()
      }
    }
    
    
}