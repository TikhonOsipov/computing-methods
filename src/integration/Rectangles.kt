package integration

/**
 * Created by tikhon.osipov on 16.01.2018
 */
fun main(args: Array<String>) {

    val eps = 0.01f
    val a = 0f
    val b = 1f
    rectangles(a, b, eps, type = RectangleType.RIGHT, f = { x -> 1 / (1 + x) })

}

enum class RectangleType {
    LEFT, MID, RIGHT
}

fun rectangles(a: Float, b: Float, eps: Float, type: RectangleType, f: (Float) -> Float) {

    println("Rectangles: $type")

    println("f(x) = 1 / (1+x); a = $a, b = $b")
    println()

    var h = (b-a)

    var iterations = 1

    var n = 1
    var s = h * f(a+b) / 2f
    var s1 = 0f

    println("Iteration $iterations -------")
    println("h = $h; n = $n; s1 = $s1; s = $s; |s-s1|/3 = ${Math.abs(s-s1)/3f}")
    println()

    do {
        ++iterations
        n *= 2
        h = (b-a)/n
        s1 = s
        s = 0f

        when(type) {
            RectangleType.LEFT -> {
                for (k in 0 until n) {
                    s += f(a + k*h)
                }
            }
            RectangleType.MID -> {
                for (k in 0 until n) {
                    s += f(a + h/2 + k*h)
                }
            }
            RectangleType.RIGHT -> {
                for (k in 1 .. n) {
                    s += f(a + k*h)
                }
            }
        }

        s *= h

        println("Iteration $iterations -------")
        println("n = n*2; h = (b-a) / n; s1 = s; s = f(a) + f(b)")
        println("n = ${n/2}*2 = $n; h = (b-a) / n = ($b - $a) / $n = ${b-a} / $n = $h; s1 = s = $s1")
        println("n = $n; h = $h; s1 = $s1; s = $s; |s-s1|/3 = ${Math.abs(s-s1)/3f}")
        println()

        println("i from 1 to ${n-1}:")
        for(i in 1 until n) {
            println("s = s + 2 * f(a + i*h) = $s + 2 * f($a + $i * $h) = $s + 2 * f(${a + i*h}) = $s + 2 * ${f(a + i*h)}")
            s += 2 * f(a + i*h)
            println("> s = $s")
        }

        println()
        println("s = s * h/2 = $s * $h / 2 = $s * ${h/2}")
        s *= h/2
        println(">> s = $s")
        println()
        println()

    } while (Math.abs(s-s1)/3f > eps)

    println("END: square = $s")
}
