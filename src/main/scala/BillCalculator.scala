import scala.collection.immutable.HashMap

case class BillCalculator(menu: HashMap[String, BigDecimal]) {
  require(Option(menu).map(_.nonEmpty) == Option(true), "Menu must not be empty")

  def calculateBillTotal(order: List[String]): BigDecimal = {
    val totalBeforeService = orderTotal(order)
    val serviceAmount = calculateServiceAmount(totalBeforeService, order)
    roundedTotalAmount(totalBeforeService, serviceAmount)
  }

  private def orderTotal(order: List[String]): BigDecimal = {
    (order map menu).sum
  }

  private def calculateServiceAmount(totalBeforeService: BigDecimal, order: List[String]): BigDecimal = {
    val servicePercent = serviceApplicableFor(order)
    val serviceAmount = (totalBeforeService * servicePercent).min(20.00)
    serviceAmount
  }

  private def serviceApplicableFor(order: List[String]): BigDecimal = {
    order match {
      case _ if order.contains("Cheese Sandwich") => 0.1
      case _ if order.contains("Steak Sandwich")  => 0.2
      case _ => 0.0
    }
  }

  private def roundedTotalAmount(totalBeforeService: BigDecimal, serviceAmount: BigDecimal): BigDecimal = {
    val totalIncludingService = totalBeforeService + serviceAmount
    totalIncludingService.setScale(2, BigDecimal.RoundingMode.HALF_UP)
  }
}
