package optimisation

import toSmallString

/**
 * Created by tikhon.osipov on 16.01.2018
 */
fun main(args: Array<String>) {

}

fun dichotomy(a: Double, b: Double, d: Double, iterations: Int) {
    var _a = a
    var _b = b

    var x1: Double
    var x2: Double

    for(i in 1..iterations) {

        println()
        println("Итерация $i:")

        x1 = (a+b-d)/2
        x2 = (a+b+d)/2

        println("x1 = ${x1.toSmallString()}, f(x1) = ${f(x1).toSmallString()}; x2 = ${x2.toSmallString()}, f(x2) = ${f(x2).toSmallString()}")

        if(f(x1) > f(x2)) {
            _a = x1
            println("f1 > f2: a = x1 = ${x1.toSmallString()}")
        } else {
            _b = x2
            println("f1 < f2: b = x2 = ${x2.toSmallString()}")
        }
    }

    val targetX = (_a + _b) / 2.0
    val targetF = f(targetX)

    println()
    println("x = ${targetX.toSmallString()}, f(x) = ${targetF.toSmallString()}")
}

private fun f(x: Double): Double = 1.0
