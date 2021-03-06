package spreadsheet.interpreter

import spreadsheet.CellId

sealed trait Expression
sealed trait Value {
  def toStr: String
  override def toString()   = toStr
}
case class NumberValue(value: Double) extends AnyRef with Expression with Value {
  def +(other: NumberValue)  = NumberValue(value + other.value)
  def -(other: NumberValue)  = NumberValue(value - other.value)
  def *(other: NumberValue)  = NumberValue(value * other.value)
  def /(other: NumberValue)  = NumberValue(value / other.value)

  override def toStr   = value.toString
}
case class StringValue(value: String) extends AnyRef with Expression with Value {
  override def toStr   = value.toString
}
case class CellReference(cellId: CellId) extends AnyRef with Expression
sealed trait BinaryOp extends Expression {
  def left: Expression
  def right: Expression
}
case class Add(left: Expression, right: Expression) extends BinaryOp
case class Substract(left: Expression, right: Expression) extends BinaryOp
case class Multiply(left: Expression, right: Expression) extends BinaryOp
case class Divide(left: Expression, right: Expression) extends BinaryOp

case class CellsRange(start: CellId, end: CellId) {
  require(start <= end)
}
case class SumInRange(range: CellsRange) extends Expression
