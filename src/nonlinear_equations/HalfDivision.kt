package nonlinear_equations

/**
 * Created by tikhon.osipov on 24.01.2018
 */
fun main(args: Array<String>) {

    fun f(x: Float): Float = x*x*x + x - 1

    val eps = 0.01f

    var a = 0f
    var b = 1f

    var c = (a + b)/2f

    var iterations = 1

    println("Iteration $iterations -------")
    println("a = $a, b = $b; f(a) = ${f(a)}, f(b) = ${f(b)}; c = ($a + $b) / 2 = $c; f(c) = ${f(c)}")
    println()

    while(b-a > eps) {
        ++iterations
        if(f(c)*f(b) < 0) a = c else b = c
        c = (a + b)/2f

        println("Iteration $iterations -------")
        println("a = $a, b = $b; f(a) = ${f(a)}, f(b) = ${f(b)}; c = ($a + $b) / 2 = $c; f(c) = ${f(c)}")
        println()
    }
}
