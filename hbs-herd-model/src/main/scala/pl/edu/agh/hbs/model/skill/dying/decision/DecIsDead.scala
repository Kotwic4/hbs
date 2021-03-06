package pl.edu.agh.hbs.model.skill.dying.decision

import pl.edu.agh.hbs.model.ModifierBuffer
import pl.edu.agh.hbs.model.skill.basic.modifier.ModLifeStatus
import pl.edu.agh.hbs.model.skill.dying.action.ActIsDead
import pl.edu.agh.hbs.model.skill.{Action, Decision}

object DecIsDead extends Decision {

  override val actions: List[Action] = List(ActIsDead)

  override def priority: Int = Int.MaxValue

  override def decision(modifiers: ModifierBuffer): Boolean = !modifiers.getFirst[ModLifeStatus].alive

}
