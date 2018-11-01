package pl.edu.agh.hbs.model.skill.basic.message

import pl.edu.agh.hbs.model.propagation.Propagation
import pl.edu.agh.hbs.model.skill.Message
import pl.edu.agh.hbs.model.skill.basic.modifier.ModNeighbour
import pl.edu.agh.hbs.model.{Agent, Vector}

class MesVisibleAgent(override val propagation: Propagation,
                      val senderId: String,
                      val position: Vector,
                      val velocity: Vector) extends Message(propagation) {

  def process(agent: Agent): Unit = {
    val modVisibleAgents = agent.modifiers
      .getAll[ModNeighbour]((mod: ModNeighbour) => senderId == mod.agentId)
    agent.modifiers -- modVisibleAgents
    if (propagation.shouldReceive(agent)) {
      agent.modifiers.update(ModNeighbour(senderId, position, velocity))
    }
  }
}