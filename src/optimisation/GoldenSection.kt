package optimisation

import toSmallString

/**
 * Created by tikhon.osipov on 16.01.2018
 */
fun main(args: Array<String>) {
    //println("Hello World!")
    goldenSection(a = 0.0, b = 1.0, iterations = 5)
}

//минимум функции
private fun goldenSection(a: Double, b: Double, iterations: Int) {
    var _a = a
    var _b = b

    val k1 = (3 - Math.sqrt(5.0)) / 2
    val k2 = (Math.sqrt(5.0) - 1) / 2

    var x1 = _a + k1 * (_b-_a)
    var x2 = _a + k2 * (_b-_a)

    var f1 = function(x1)
    var f2 = function(x2)

    println("x1 = ${x1.toSmallString()}, f(x1) = ${f1.toSmallString()}; x2 = ${x2.toSmallString()}, f(x2) = ${f2.toSmallString()}")

    println()
    println("Итерации")

    for (i in 1..iterations) {
       if(f1 < f2) {
           println()
           println("Итерация $i")

           _a = x1
           println("f1 = ${f1.toSmallString()} < f2 = ${f2.toSmallString()}: a = x1, x1 = x2, x2 = a + k2 * (b-a): a = ${x1.toSmallString()}, x1 = ${x2.toSmallString()}, x2 = ${_a.toSmallString()} + ${k2.toSmallString()} * (${_b.toSmallString()}-${_a.toSmallString()})")
           x1 = x2
           x2 = _a + k2 * (_b-_a)

           f1 = f2
           f2 = function(x2)

           println("x2 = ${x2.toSmallString()}, f2 = ${f2.toSmallString()}")
       } else {

           _b = x2
           println("f1 = ${f1.toSmallString()} >= f2 = ${f2.toSmallString()}: b = x2, x2 = x1, x1 = a + k1 * (b-a): b = ${x2.toSmallString()}, x2 = ${x2.toSmallString()}, x1 = ${_a.toSmallString()} + ${k1.toSmallString()} * (${_b.toSmallString()}-${_a.toSmallString()})")
           x2 = x1
           x1 = _a + k1 * (_b-_a)

           f2 = f1
           f1 = function(x1)

           println("x1 = ${x1.toSmallString()}, f1 = ${f1.toSmallString()}")
       }
    }

    val xTarget = (_a + _b) / 2.0
    val fTarget = function(xTarget)

    println()
    println("Результат:")
    println("x = ${xTarget.toSmallString()}, f(x) = ${fTarget.toSmallString()}")
}

private fun function(x: Double): Double = 2 * x * x - 3 * Math.exp(-x)
