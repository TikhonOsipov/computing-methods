package integration

/**
 * Created by tikhon.osipov on 16.01.2018
 */
fun main(args: Array<String>) {

    val eps = 0.01f

    fun f(x: Float): Float = 1 / (1+x)

    val a = 0f
    val b = 1f

    println("f(x) = 1 / (1+x); a = $a, b = $b")
    println()

    var h = (b-a)

    var iterations = 1

    var n = 1
    var s = (f(a) + f(b)) * (h/2f)
    var s1 = 0f

    println("Iteration $iterations -------")
    println("h = $h; n = $n; s1 = $s1; s = $s; |s-s1|/3 = ${Math.abs(s-s1)/3f}")
    println()

    do {
        ++iterations
        n *= 2
        h = (b-a)/n
        s1 = s
        s = f(a) + f(b)

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
