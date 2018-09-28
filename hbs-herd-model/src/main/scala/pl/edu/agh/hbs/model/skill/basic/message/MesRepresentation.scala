package pl.edu.agh.hbs.model.skill.basic.message

import pl.edu.agh.hbs.core.providers.Representation
import pl.edu.agh.hbs.model.Agent
import pl.edu.agh.hbs.model.propagation.Propagation
import pl.edu.agh.hbs.model.skill.Message
import pl.edu.agh.hbs.model.skill.basic.modifier.ModRepresentation

class MesRepresentation(override val propagation: Propagation,
                        override val senderId: String,
                        val representation: Representation) extends Message(propagation, senderId) {

  def process(agent: Agent): Unit = {
    if (propagation.shouldReceive(agent))
      agent.modifiers.update(ModRepresentation(this.representation))
  }

}
