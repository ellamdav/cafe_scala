import org.scalatest._

import scala.collection.immutable.HashMap

class BillCalculatorSpec extends FunSpec with Matchers {

  describe("A BillCalculator") {

    describe("Constructor") {
      it("should accept a menu") {
        val menu = HashMap("Cookie" -> 0.25)
        val billCalculator = BillCalculator(menu)
        billCalculator shouldBe a[BillCalculator]
      }

      it("should throw if the menu is empty") {
        val menu = new HashMap[String, Double]
        the [IllegalArgumentException] thrownBy {
          BillCalculator(menu)
        } should have message "requirement failed: Menu must not be empty"
      }
    }
  }

}

