import org.scalatest._

import scala.collection.immutable.HashMap

class BillCalculatorSpec extends FlatSpec with Matchers {

  "A BillCalculator" should "accept a menu" in {
    val menu = new HashMap[String, Float]
    val billCalculator = BillCalculator(menu)
    billCalculator shouldBe a [BillCalculator]
  }

}

