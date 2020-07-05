# An evening of Scala


```scala
val x = 5
// x: Int = 5
x
// res0: Int = 5
case class Person(name: String, age: Int
                 )
val p1 = Person("Fred", 25
               )
// p1: Person = Person("Fred", 25)
p1
// res1: Person = Person("Fred", 25)
sealed trait Pet {
  val name: String
  val age: Int
}
case class Cat(name: String, age: Int) extends Pet
case class Dog(name: String, age: Int) extends Pet
Cat("Garfield", 20)
// res2: Cat = Cat("Garfield", 20)
Dog("Scooby", 10)
// res3: Dog = Dog("Scooby", 10)
```
