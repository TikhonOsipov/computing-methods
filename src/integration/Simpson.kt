package integration

/**
 * Created by tikhon.osipov on 16.01.2018
 */
fun main(args: Array<String>) {

    println("Simpson")
    println()

    simpson(0f, 1f, 0.01f, { x: Float -> 1 / (1+x) })
}

fun simpson(a: Float, b: Float, eps: Float, f: (Float) -> Float) {

    println("f(x) = 1 / (1+x); a = $a, b = $b")
    println()

    var iterations = 1

    var n = 2
    var h = (b-a)/n
    var s = ( f(a) + 4 * f( (a+b)/2 + f(b)) ) * (h/3f)
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

        var c = 4
        var x = a

        for (k in 1 until n) {
            x += h
            s += c * f(x)
            c = 6-c
        }

        s *= (h/3f)

        println("Iteration $iterations -------")
        println("n = n*2; h = (b-a) / n; s1 = s; s = f(a) + f(b)")
        println("n = ${n/2}*2 = $n; h = (b-a) / n = ($b - $a) / $n = ${b-a} / $n = $h; s1 = s = $s1")
        println("n = $n; h = $h; s1 = $s1; s = $s; |s-s1|/3 = ${Math.abs(s-s1)/3f}")
        println()

        /*println("i from 1 to ${n-1}:")
        for(i in 1 until n) {
            println("s = s + 2 * f(a + i*h) = $s + 2 * f($a + $i * $h) = $s + 2 * f(${a + i*h}) = $s + 2 * ${f(a + i*h)}")
            s += 2 * f(a + i*h)
            println("> s = $s")
        }*/

        /*println()
        println("s = s * h/2 = $s * $h / 2 = $s * ${h/2}")
        s *= h/2
        println(">> s = $s")
        println()
        println()*/

    } while (Math.abs(s-s1)/ 15f > eps)

    println("END: square = $s")
}
