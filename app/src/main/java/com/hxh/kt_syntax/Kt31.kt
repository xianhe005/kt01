package com.hxh.kt_syntax

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * Created by HXH on 2017/6/6 0006.
 * 类和继承
 */
fun main(args: Array<String>) {
    println(Customer("Tom"))
    println(Customer2("Tom2").customerKey)
    // Customer3("1")
    println(Customer4().customerName)
    println(Customer4("Customer4").customerName)

    // 创建类的实例
    // 要创建一个类的实例，我们就像普通函数一样调用构造函数：
    val invoice = Invoice()
    val customer = Customer("Joe Smith")
    // 注意 Kotlin 并没有 new 关键字。
    // 创建嵌套类、内部类和匿名内部类的类实例在嵌套类中有述。

    println(Foo2().x)
    println(Bar1().x)

    println(Bar2(5).count)
    println(Bar3().count)

    C().f()

    val b: Base3 = Derived4()
    println(b::class.java)
    b.f()
    val d: Derived3 = Derived4()
    println(d::class.java)
    d.f()
}

// 类
// Kotlin 中使用关键字 class 声明类
class Invoice {
}

// 类声明由类名、类头（指定其类型参数、主 构造函数等）和由大括号包围的类体构成。
// 类头和类体都是可选的； 如果一个类没有类体，可以省略花括号。
class Empty

// 构造函数
// 在 Kotlin 中的一个类可以有一个主构造函数和一个或多个次构造函数。
// 主构造函数是类头的一部分：它跟在类名（和可选的类型参数）后。
class Person5 constructor(firstName: String) {
}

// 如果主构造函数没有任何注解或者可见性修饰符，可以省略这个 constructor 关键字。
class Person6(firstName: String) {
}

// 主构造函数不能包含任何的代码。初始化的代码可以放到以 init 关键字作为前缀的初始化块（initializer blocks）中：
class Customer(name: String) {
    init {
        println("Customer initialized with value $name")
    }
}

// 注意，主构造的参数可以在初始化块中使用。它们也可以在类体内声明的属性初始化器中使用：
class Customer2(name: String) {
    val customerKey = name.toUpperCase()
}

// 事实上，声明属性以及从主构造函数初始化属性，Kotlin 有简洁的语法：
class Person7(val firstName: String, val lastName: String, var age: Int) {
    // ……
}

// 与普通属性一样，主构造函数中声明的属性可以是可变的（var）或只读的（val）。
// 如果构造函数有注解或可见性修饰符，这个 constructor 关键字是必需的，并且这些修饰符在它前面：
class Customer3 private @SuppressWarnings constructor(name: String) {
    //……
}

// 次构造函数
// 类也可以声明前缀有 constructor的次构造函数：
class Person8 {
    private val children = ArrayList<Person8>()

    constructor(parent: Person8) {
        parent.children.add(this)
    }
}

// 如果类有一个主构造函数，每个次构造函数需要委托给主构造函数，
// 可以直接委托或者通过别的次构造函数间接委托。委托到同一个类的另一个构造函数用 this 关键字即可：
class Person9(val name: String) {
    private var children = ArrayList<Person9>()

    constructor(name: String, parent: Person9) : this(name) {
        parent.children.add(this)
    }
}

// 如果一个非抽象类没有声明任何（主或次）构造函数，它会有一个生成的不带参数的主构造函数。
// 构造函数的可见性是 public。
// 如果你不希望你的类有一个公有构造函数，你需要声明一个带有非默认可见性的空的主构造函数：
class DontCreateMe private constructor() {
}

// 注意：在 JVM上，如果主构造函数的所有的参数都有默认值，编译器会生成一个额外的无参构造函数，它将使用默认值。
// 这使得 Kotlin 更易于使用像 Jackson 或者 JPA 这样的通过无参构造函数创建类的实例的库。
class Customer4(val customerName: String = "Tom")

// 继承
// 在 Kotlin 中所有类都有一个共同的超类 Any，这对于没有超类型声明的类是默认超类：
class Example // 从 Any 隐式继承

// Any 不是 java.lang.Object；尤其是，它除了 equals()、hashCode()和toString()外没有任何成员。 更多细节请查阅Java互操作性部分。
// 要声明一个显式的超类型，我们把类型放到类头的冒号之后：
open class Base(p: Int)

class Derived(p: Int) : Base(p)

// 如果该类有一个主构造函数，其基类型可以（并且必须） 用（基类型的）主构造函数参数就地初始化。
// 如果类没有主构造函数，那么每个次构造函数必须使用 super 关键字初始化其基类型，或委托给另一个构造函数做到这一点。
// 注意，在这种情况下，不同的次构造函数可以调用基类型的不同的构造函数：
class MyView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
}
// 类上的 open 标注与 Java 中 final 相反，它允许其他类从这个类继承。
// 默认情况下，在 Kotlin 中所有的类都是 final，
// 对应于 Effective Java书中的第 17 条：要么为继承而设计，并提供文档说明，要么就禁止继承。

// 覆盖方法
// 我们之前提到过，Kotlin 力求清晰显式。与 Java 不同，Kotlin 需要显式标注可覆盖的成员（我们称之为开放）和覆盖后的成员：
open class Base2 {
    open fun v() {}
    fun nv() {}
}

class Derived2() : Base2() {
    override fun v() {}
}

// Derived2.v() 函数上必须加上 override标注。
// 如果没写，编译器将会报错。如果函数没有标注 open 如 Base2.nv()，则子类中不允许定义相同签名的函数，不论加不加 override。
// 在一个 final 类中（没有用 open 标注的类），开放成员是禁止的。
// 标记为 override 的成员本身是开放的，也就是说，它可以在子类中覆盖。如果你想禁止再次覆盖，使用 final 关键字：
open class AnotherDerived() : Base2() {
    final override fun v() {}
}

// 覆盖属性
// 属性覆盖与方法覆盖类似；在超类中声明然后在派生类中重新声明的属性必须以 override 开头，并且它们必须具有兼容的类型。
// 每个声明的属性可以由具有初始化器的属性或者具有 getter 方法的属性覆盖。

open class Foo2 {
    open val x: Int get() {
        return 1
    }
}

class Bar1 : Foo2() {
    override val x: Int = 3
}

// 你也可以用一个 var 属性覆盖一个 val 属性，但反之则不行。
// 这是允许的，因为一个 val 属性本质上声明了一个 getter 方法，而将其覆盖为 var 只是在子类中额外声明一个 setter 方法。
// 请注意，你可以在主构造函数中使用 override 关键字作为属性声明的一部分。
interface Foo3 {
    val count: Int
}

class Bar2(override val count: Int) : Foo3
class Bar3 : Foo3 {
    override var count: Int = 2
}

// 覆盖规则
// 在 Kotlin 中，实现继承由下述规则规定：如果一个类从它的直接超类继承相同成员的多个实现，
// 它必须覆盖这个成员并提供其自己的实现（也许用继承来的其中之一）。
// 为了表示采用从哪个超类型继承的实现，我们使用由尖括号中超类型名限定的 super，如 super<Base>：
open class A {
    open fun f() {
        println("A")
    }

    fun a() {
        println("a")
    }
}

interface B {
    fun f() {
        println("B")
    } // 接口成员默认就是“open”的

    fun b() {
        println("b")
    }
}

class C() : A(), B {
    // 编译器要求覆盖 f()：
    override fun f() {
        super<A>.f() // 调用 A.f()
        super<B>.f() // 调用 B.f()
        println("C")
    }
}
// 同时继承 A 和 B 没问题，并且 a() 和 b() 也没问题因为 C 只继承了每个函数的一个实现。
// 但是 f() 由 C 继承了两个实现，所以我们必须在 C 中覆盖 f() 并且提供我们自己的实现来消除歧义。

// 抽象类
// 类和其中的某些成员可以声明为 abstract。 抽象成员在本类中可以不用实现。
// 需要注意的是，我们并不需要用 open 标注一个抽象类或者函数——因为这不言而喻。
// 我们可以用一个抽象成员覆盖一个非抽象的开放成员
open class Base3 {
    open fun f() {
        println("Base3.f()")
    }
}

abstract class Derived3 : Base3() {
    abstract override fun f()
}

class Derived4 : Derived3() {
    override fun f() {
        println("Derived4.f()")
    }
}

// 伴生对象
// 与 Java 或 C# 不同，在 Kotlin 中类没有静态方法。在大多数情况下，它建议简单地使用包级函数。
// 如果你需要写一个可以无需用一个类的实例来调用、但需要访问类内部的函数（例如，工厂方法），你可以把它写成该类内对象声明中的一员。
// 更具体地讲，如果在你的类内声明了一个伴生对象， 你就可以使用像在 Java/C# 中调用静态方法相同的语法来调用其成员，只使用类名作为限定符。
