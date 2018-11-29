package refactoring

object Refactoring {
  // return errors for each element of the input which is not backed up by its sublist - 1 (i.e., the last element taken away)
  //
  // eg: an input element like ["x" "y"] is valid only if ["x"] exists in the input
  def areInputElementsWellformed(allInputElements: List[List[String]]): List[String] = {
    val allInputElementsWithoutEmptyInputElements = allInputElements.filterNot(_.isEmpty)
    val inputElementsByLengthMap: Map[Int, List[List[String]]] = //eg, Map(1 -> [["x"]], 2 -> [["x" "y"]])
      allInputElementsWithoutEmptyInputElements.groupBy({ _.length })
    val inputElementsByGreaterLength: List[(Int, List[List[String]])] = //eg, [(2, [["x" "y"]]), (1, [["x"]])]
      inputElementsByLengthMap.toList.sortWith(_._1 > _._1)
    inputElementsByGreaterLength.flatMap((kv: (Int, List[List[String]])) => // TODO do I need to use the ordered list here?
      kv._2.flatMap((l: List[String]) => {
        val partialInputElements: List[List[String]] = //eg, ["x" "y" "z"] becomes [["x"] ["x" "y"]]
          l.indices.tail.map(l.slice(0, _)).toList
        partialInputElements.flatMap((inputElements: List[String]) => {
          val subinputElementsOfSameSize: Option[List[List[String]]] = inputElementsByLengthMap.get(inputElements.length)
          val isSubstep: Boolean = subinputElementsOfSameSize.map(_.contains(inputElements)).getOrElse(false)
          if (isSubstep) List.empty
          else List(s"Missing input element $inputElements to satisfy input element $l")
        })
      }))
  }
}
