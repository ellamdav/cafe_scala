import org.scalatest._

import scala.collection.immutable.HashMap

class BillCalculatorSpec extends FunSpec with Matchers {

  val menu = HashMap(
    "Cola"            -> 0.50,
    "Coffee"          -> 1.00,
    "Cheese Sandwich" -> 2.00,
    "Steak Sandwich"  -> 4.50
  )
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
        val order = List("Cola")
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

      it("should return the total value of two different items") {
        val order = List("Cola", "Coffee")
        billCalculator.calculateBillTotal(order) should equal (1.50)
      }

      it("should return the total value of three different items") {
        val order = List("Cola", "Coffee", "Cheese Sandwich")
        billCalculator.calculateBillTotal(order) should equal (3.50)
      }

      it("should return the total value of four different items") {
        val order = List("Cola", "Coffee", "Cheese Sandwich", "Steak Sandwich")
        billCalculator.calculateBillTotal(order) should equal (8.00)
      }

      it("should throw if an unknown item is encountered") {
        val order = List("Latte")
        the [NoSuchElementException] thrownBy {
          billCalculator.calculateBillTotal(order)
        } should have message "key not found: Latte"
      }
    }
  }

}

