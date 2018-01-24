package integration

/**
 * Created by tikhon.osipov on 16.01.2018
 */
fun main(args: Array<String>) {

    val eps = 0.01f

    fun f(x: Float): Float = 1 / (1+x)

    val a = 0f
    val b = 1f

    var h = (b-a)

    var iterations = 1

    var n = 1
    var s = (f(a) + f(b)) * (h/2f)
    var s1 = 0f

    println("Iteration $iterations")
    println("h = $h; n = $n; s1 = $s1; s = $s; |s-s1|/3 = ${Math.abs(s-s1)/3f}")
    println()

    do {
        ++iterations
        n *= 2
        h = (b-a)/n
        s1 = s
        s = f(a) + f(b)

        for(i in 1 until n) {
            s += 2 * f(a+ i*h)
        }

        s *= h/2

        println("Iteration $iterations")
        println("h = $h; n = $n; s1 = $s1; s = $s; |s-s1|/3 = ${Math.abs(s-s1)/3f}")
        println()
    } while (Math.abs(s-s1)/3f > eps)
}
