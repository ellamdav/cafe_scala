import scala.collection.immutable.HashMap

case class BillCalculator(menu: HashMap[String, BigDecimal]) {
  require(Option(menu).map(_.nonEmpty) == Option(true), "Menu must not be empty")

  def calculateBillTotal(order: List[String]): BigDecimal = {
    val totalBeforeService = orderTotal(order)
    val servicePercent = serviceApplicableFor(order)
    val totalIncludingService = totalBeforeService * (1 + servicePercent)
    totalIncludingService.setScale(2, BigDecimal.RoundingMode.HALF_UP)
  }

  private def orderTotal(order: List[String]): BigDecimal = {
    (order map menu).sum
  }

  private def serviceApplicableFor(order: List[String]): BigDecimal = {
    if (order.contains("Cheese Sandwich")) 0.1 else if (order.contains("Steak Sandwich")) 0.2 else 0.0
  }
}
