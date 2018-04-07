import org.scalatest._

import scala.collection.immutable.HashMap

class BillCalculatorSpec extends FunSpec with Matchers {

  val menu = HashMap("Cola" -> 0.50)
  val billCalculator = BillCalculator(menu)

  describe("A BillCalculator") {

    describe("Constructor") {
      it("should accept a menu and return a bill calculator") {
        billCalculator shouldBe a[BillCalculator]
      }

      it("should throw if the menu is empty") {
        val menu = new HashMap[String, Double]
        the [IllegalArgumentException] thrownBy {
          BillCalculator(menu)
        } should have message "requirement failed: Menu must not be empty"
      }
    }

    describe("calculateBillTotal") {
      it("should accept order items as a list of strings, and return a total amount") {
        val order = List("Whatever")
        billCalculator.calculateBillTotal(order) shouldBe a[java.lang.Double] // TODO why java.lang.Double ?
      }

      it("should return the value of a single item") {
        val order = List("Cola")
        billCalculator.calculateBillTotal(order) should equal (0.50)
      }

      it("should return the total value of two items") {
        val order = List("Cola", "Cola")
        billCalculator.calculateBillTotal(order) should equal (1.00)
      }
    }
  }

}

