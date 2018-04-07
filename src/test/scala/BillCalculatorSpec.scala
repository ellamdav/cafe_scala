import org.scalatest._

import scala.collection.immutable.HashMap

class BillCalculatorSpec extends FunSpec with Matchers {

  val menu = HashMap(
    "Cola"            -> BigDecimal(0.50),
    "Coffee"          -> BigDecimal(1.00),
    "Cheese Sandwich" -> BigDecimal(2.00),
    "Steak Sandwich"  -> BigDecimal(4.50)
  )
  val billCalculator = BillCalculator(menu)

  describe("A BillCalculator") {

    describe("Constructor") {
      it("should accept a menu and return a bill calculator") {
        billCalculator shouldBe a[BillCalculator]
      }

      it("should throw if the menu is empty") {
        val menu = new HashMap[String, BigDecimal]
        the [IllegalArgumentException] thrownBy {
          BillCalculator(menu)
        } should have message "requirement failed: Menu must not be empty"
      }
    }

    describe("calculateBillTotal") {
      it("should accept order items as a list of strings, and return a total amount") {
        val order = List("Cola")
        billCalculator.calculateBillTotal(order) shouldBe a[BigDecimal]
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
        billCalculator.calculateBillTotal(order) should equal (3.85)
      }

      it("should return the total value of four different items") {
        val order = List("Cola", "Coffee", "Cheese Sandwich", "Steak Sandwich")
        billCalculator.calculateBillTotal(order) should equal (8.80)
      }

      it("should throw if an unknown item is encountered") {
        val order = List("Latte")
        the [NoSuchElementException] thrownBy {
          billCalculator.calculateBillTotal(order)
        } should have message "key not found: Latte"
      }

      describe("Service charges") {
        describe("When order includes only drinks") {
          it("does not add a service charge") {
            val order = List("Cola", "Coffee", "Coffee")
            billCalculator.calculateBillTotal(order) should equal (2.50)
          }
        }

        describe("When an order includes food") {
          it("adds a 10% service charge") {
            val order = List("Cheese Sandwich")
            billCalculator.calculateBillTotal(order) should equal (2.20)
          }

          it("rounds the total to 2dp") {
            val menu = HashMap(
              "Cheese Sandwich" -> BigDecimal(2.99)
            )
            val billCalculator = BillCalculator(menu)
            val order = List("Cheese Sandwich")
            billCalculator.calculateBillTotal(order) should equal (3.29)
          }
        }

        describe("When an order includes hot food") {
          it("adds a 20% service charge") {
            val order = List("Steak Sandwich")
            billCalculator.calculateBillTotal(order) should equal (5.40)
          }
        }
      }
    }
  }

}

