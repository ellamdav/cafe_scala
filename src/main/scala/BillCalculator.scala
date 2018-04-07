import scala.collection.immutable.HashMap

case class BillCalculator(menu: HashMap[String, BigDecimal]) {
  require(Option(menu).map(_.nonEmpty) == Option(true), "Menu must not be empty")

  def calculateBillTotal(order: List[String]): BigDecimal = {
    val totalBeforeService = orderTotal(order)
    val servicePercent = serviceApplicableFor(order)
    totalBeforeService * (1 + servicePercent)
  }

  private def orderTotal(order: List[String]): BigDecimal = {
    (order map menu).sum
  }

  private def serviceApplicableFor(order: List[String]): BigDecimal = {
    if (order.contains("Cheese Sandwich")) 0.1 else 0.0
  }
}
