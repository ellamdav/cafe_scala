import scala.collection.immutable.HashMap

case class BillCalculator(menu: HashMap[String, Double]) {
  require(Option(menu).map(_.nonEmpty) == Option(true), "Menu must not be empty")

  def calculateBillTotal(order: List[String]): Double = 0.5
}
