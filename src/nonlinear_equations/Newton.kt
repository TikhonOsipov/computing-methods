package nonlinear_equations

import java.util.*

/**
 * Created by tikhon.osipov on 16.01.2018
 */
fun main(args: Array<String>) {

    //отделить корень
    //вычислить первую и вторую производные функции f(x)
    //выбор заданной точки: f(x0) * f''(x0) > 0

    findX0(-1.99f, -1f)
    val sc = Scanner(System.`in`)

    println("Please specify x0:")
    val x0 = sc.nextFloat()

    val eps = 0.001f

    var iterations = 1

    var x = x0
    var nextX = nextX(x)

    println("Iteration $iterations -------")
    println("x = $x, f(x) = ${f(x)}; nextX = $nextX, f(nextX) = ${f(nextX)}")
    println()

    while(!shouldStop(x, nextX, eps)) {
        ++iterations
        x = nextX
        nextX = nextX(x)

        println("Iteration $iterations -------")
        println("x = $x, f(x) = ${f(x)}; nextX = $nextX, f(nextX) = ${f(nextX)}")
        println()
    }

    println("END: nextX = $nextX, f(nextX) = ${f(nextX)}")
}

//функция
//todo fill F
fun f(x: Float): Float = x - Math.log((x+2f).toDouble()).toFloat()

//производная
//todo fill F'
fun f_1(x: Float): Float = 1f - 1/(x+2f)

//вторая производная
//todo fill F''
fun f_2(x: Float): Float = 1/((x+2f)*(x+2f))

fun nextX(x: Float): Float = x - f(x) / f_1(x)

/**
 * выводит допустимые значения начального значения x0 в заданном диапазоне [a, b]
 */
fun findX0(a: Float, b: Float) {
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
