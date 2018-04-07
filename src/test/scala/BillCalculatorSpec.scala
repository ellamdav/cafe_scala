import org.scalatest._

import scala.collection.immutable.HashMap

class BillCalculatorSpec extends FunSpec with Matchers {

  describe("A BillCalculator") {

    describe("Constructor") {
      it("should accept a menu") {
        val menu = new HashMap[String, Float]
        val billCalculator = BillCalculator(menu)
        billCalculator shouldBe a[BillCalculator]
      }
    }
  }

}

