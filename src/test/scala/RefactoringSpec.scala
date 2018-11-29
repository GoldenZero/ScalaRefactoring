package refactoring

import org.scalatest.{ Matchers, OptionValues, WordSpec }

class RefactoringSpec extends WordSpec with Matchers with OptionValues {
  import Refactoring._

  "Function areInputElementsWellformed" should {
    "return error if first element in list does not exists by itself" in {
      val result = areInputElementsWellformed(List(List("x", "y")))
      assert(result == List("Missing input element List(x) to satisfy input element List(x, y)"))
    }

    "return errors if second and third element in list do not exist" in {
      val result = areInputElementsWellformed(List(List("x"), List("x", "y", "z"), List("x", "y", "z", "otherElement In List")))
      assert(result == List("Missing input element List(x, y) to satisfy input element List(x, y, z, otherElement In List)", "Missing input element List(x, y) to satisfy input element List(x, y, z)"))
    }

    "return errors if multiple element in list do not validate" in {
      val result = areInputElementsWellformed(List(List("a"), List("b", "a"), List("b", "c"), List("b", "c", "d"), List("a", "c", "d")))
      assert(result == List("Missing input element List(b) to satisfy input element List(b, c, d)", "Missing input element List(a, c) to satisfy input element List(a, c, d)", "Missing input element List(b) to satisfy input element List(b, a)", "Missing input element List(b) to satisfy input element List(b, c)"))
    }

    "return no errors if no element in list" in {
      val result = areInputElementsWellformed(List(List()))
      assert(result == List())
    }

    "return no errors if first element in list exist by itself" in {
      val result = areInputElementsWellformed(List(List("x"), List("x", "y")))
      assert(result == List())
    }
  }

}
