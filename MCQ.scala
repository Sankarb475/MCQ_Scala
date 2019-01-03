//output of the below code would be??

object dummy extends App{
  def doSomething = {
    List(Some("one"),None,Some("two"), Some("three"), None, Some(None))
  }
  val check = for {
    func <- doSomething
    value <- func.filter(x => x!=None)
  } yield value
  println(check)
}

// output :: List(one, two, three)

