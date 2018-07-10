package pl.edu.agh.hbs.model.propagation

import pl.edu.agh.hbs.model.skill.basic.modifier.position.Position

class CirclePropagation(val center: Position, val radius: Double) extends Propagation {

  override def isCovered(position: Position): Boolean = center.distance(position) < radius

}
