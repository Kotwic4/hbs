package pl.edu.agh.hbs.model.skill.flocking.instantAction

import pl.edu.agh.hbs.model
import pl.edu.agh.hbs.model.skill.Action
import pl.edu.agh.hbs.model.skill.basic.modifier.{ModPosition, ModSpecies}
import pl.edu.agh.hbs.model.skill.common.modifier.{ModNeighbour, ModVelocity}
import pl.edu.agh.hbs.model.skill.flocking.modifier.ModAlignmentVelocityParameters
import pl.edu.agh.hbs.model.{ModifierBuffer, StepOutput}

object ActAlignmentVelocity extends Action {

  override def action(modifiers: ModifierBuffer): StepOutput = {
    val velocity = modifiers.getFirst[ModVelocity]("standard").velocity
    val position = modifiers.getFirst[ModPosition].position
    val maxDistance = modifiers.getFirst[ModAlignmentVelocityParameters].maxDistance
    val alignmentFactor = modifiers.getFirst[ModAlignmentVelocityParameters].alignmentFactor
    val species = modifiers.getFirst[ModSpecies].species
    val neighbours = modifiers.getAll[ModNeighbour]
      .filter(m => (species.species.getClass.isAssignableFrom(m.species.species.getClass)
        || m.species.species.getClass.isAssignableFrom(species.species.getClass) &&
        m.position.distance(position) < maxDistance))

    //alignment / schooling
    val alignmentVelocity = if (neighbours.nonEmpty) {
      val perceivedVelocity = neighbours.foldLeft(model.Vector())(_ + _.velocity) / neighbours.size
      (perceivedVelocity - velocity) * alignmentFactor
    } else model.Vector()

    modifiers.update(ModVelocity(alignmentVelocity, "alignment"))
    new StepOutput()
  }
}
