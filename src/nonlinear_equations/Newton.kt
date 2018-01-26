package nonlinear_equations

import java.util.*

/**
 * Created by tikhon.osipov on 16.01.2018
 */
fun main(args: Array<String>) {

    val f = { x: Float -> x - Math.log((x+2f).toDouble()).toFloat() }
    val f1 = { x: Float -> 1f - 1/(x+2f) }
    val f2 = { x: Float -> 1/((x+2f)*(x+2f)) }

    //отделить корень
    //вычислить первую и вторую производные функции f(x)
    //выбор заданной точки: f(x0) * f''(x0) > 0

    findX0(-1.99f, -1f,
            f = f,
            f_2 = f2)

    val sc = Scanner(System.`in`)

    println("Please specify x0:")
    val x0 = sc.nextFloat()

    val eps = 0.001f

    nonlinearNewton(x0, eps,
            f = f,
            f_1 = f1)
}

fun nonlinearNewton(x0: Float, eps: Float, f: (Float) -> Float, f_1: (Float) -> Float) {
    var iterations = 1

    var x = x0
    var nextX = nextX(x, f, f_1)

    println("Iteration $iterations -------")
    println("x = $x, f(x) = ${f(x)}; nextX = $nextX, f(nextX) = ${f(nextX)}")
    println()

    while(!shouldStop(x, nextX, eps)) {
        ++iterations
        x = nextX
        nextX = nextX(x, f, f_1)

        println("Iteration $iterations -------")
        println("x = $x, f(x) = ${f(x)}; nextX = $nextX, f(nextX) = ${f(nextX)}")
        println()
    }

    println("END: nextX = $nextX, f(nextX) = ${f(nextX)}")
}

fun nextX(x: Float, f: (Float) -> Float, f_1: (Float) -> Float): Float = x - f(x) / f_1(x)

/**
 * выводит допустимые значения начального значения x0 в заданном диапазоне [a, b]
 */
fun findX0(a: Float, b: Float, f: (Float) -> Float, f_2: (Float) -> Float) {
    val max = Math.max(a, b)
    val min = Math.min(a, b)
    val h = Math.abs(max - min) / 1000

    var x0 = min
    if(f(x0)*f_2(x0) > 0) {
        println("x0 = $x0 suits to f($x0)*f''($x0) [${f(x0)} * ${f_2(x0)} = ${f(x0)*f_2(x0)}] > 0")
    }
    while(x0 <= max) {
        x0 += h
        if(f(x0)*f_2(x0) > 0) {
            println("x0 = $x0 suits to f($x0)*f''($x0) [${f(x0)} * ${f_2(x0)} = ${f(x0)*f_2(x0)}] > 0")
        }
    }
}

fun shouldStop(x: Float, nextX: Float, eps: Float): Boolean = Math.abs(nextX - x) <= eps
