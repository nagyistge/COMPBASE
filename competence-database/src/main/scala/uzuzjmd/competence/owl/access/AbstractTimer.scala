package uzuzjmd.competence.owl.access

/**
 * @author dehne
 */
abstract class AbstractTimer[INPUTTYPE, OUTPUTTYPE] {
  def convert(changes: INPUTTYPE): OUTPUTTYPE
}