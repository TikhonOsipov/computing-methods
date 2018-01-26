package nonlinear_equations

import java.util.*

/**
 * Created by tikhon.osipov on 16.01.2018
 */
fun main(args: Array<String>) {
    val a = -2f
    val b = -1f

    val eps = 0.01f

    fun nextXwhenLessZero(x: Float, a: Float, f: (Float) -> Float): Float = x - (f(x) / (f(x)-f(a))) * (x - a)

    fun nextXwhenOverZero(x: Float, b: Float, f: (Float) -> Float): Float = x - (f(x) / (f(b)-f(x))) * (b - x)

    val f = { x: Float -> x - Math.log((x+2f).toDouble()).toFloat() }
    val f_2 = { x: Float -> 1/((x+2f)*(x+2f)) }
    checkF_2(a, b, f = f, f_2 = f_2)

    val s = Scanner(System.`in`)

    println("Если производная < 0, введите 0; если > 0, введите 1")
    val type: Int = s.nextInt()

    println("Введите точку начального приближения:")
    val x0 = s.nextFloat()
    var iterations = 1
    var x = x0
    var nextX: Float

    when(type) {
        0 -> {
            println("Вторая производная < 0")
            println("Неподвижная точка: a = $a; начальное приближение: b = $b")

            nextX = nextXwhenLessZero(x, a, f)

            println("Итерация $iterations -------")
            println("x = $x; f(x) = ${f(x)}; nextX = $nextX; f(nextX) = ${f(nextX)}")

            while(Math.abs(x - nextX) > eps) {
                ++iterations
                x = nextX
                nextX = nextXwhenLessZero(x, a, f)
                println("Итерация $iterations -------")
                println("x = $x; f(x) = ${f(x)}; nextX = $nextX; f(nextX) = ${f(nextX)}")
            }

        }
        1 -> {
            println("Вторая производная > 0")
            println("Неподвижная точка: b = $b; начальное приближение: a = $a")

            nextX = nextXwhenOverZero(x, b, f)

            println("Итерация $iterations -------")
            println("x = $x; f(x) = ${f(x)}; nextX = $nextX; f(nextX) = ${f(nextX)}")

            while(Math.abs(x - nextX) > eps) {
                ++iterations
                x = nextX
                nextX = nextXwhenOverZero(x, b, f)
                println("Итерация $iterations -------")
                println("x = $x; f(x) = ${f(x)}; nextX = $nextX; f(nextX) = ${f(nextX)}")
            }
        }
        else -> {}
    }
}

fun checkF_2(a: Float, b: Float, f: (Float) -> Float, f_2: (Float) -> Float) {

    var i = a
    while(i < b) {
        i += 0.1f
        println("x = $i; f(x) = ${f(i)}; f''(x) = ${f_2(i)}")
    }
}
