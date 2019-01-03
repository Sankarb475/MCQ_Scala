//why the output type is java.io.serializable

scala> val c = List(Some("one"),None,Some(None))
c: List[Option[java.io.Serializable]] = List(Some(one), None, Some(None))

scala> val c = List(Some("one"),None)
c: List[Option[String]] = List(Some(one), None)

//because if the elements are not of same type, the resultant type will be nearest common super type which is 
//java.io.Serializable in this case
