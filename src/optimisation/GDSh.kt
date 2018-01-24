package optimisation

/**
 * Created by tikhon.osipov on 24.01.2018
 */
fun main(args: Array<String>) {
    val eps = 0.001f
    var lambda = 0.5f
    var x = 2f
    var y = 1f
    lambda = findLambda(x, y, lambda)
    var nextX = nextX(x, y, lambda)
    var nextY = nextY(x, y, lambda)

    var iterations = 1

    println("Iteration $iterations -------")
    println("x = $x, y = $y; Q(x,y) = ${Q(x,y)}; lambda = $lambda; nextX = $nextX, nextY = $nextY; Q(nextX,nextY) = ${Q(nextX,nextY)}")
    println("Pk(nextX, nextY) = ${Pk(nextX, nextY)}; Sk(nextX, nextY) = ${Sk(nextX, nextY)}")
    println()

    while(Pk(nextX, nextY) > eps || Sk(nextX, nextY) > eps) {
        ++iterations
        x = nextX
        y = nextY

        lambda = findLambda(x, y, lambda)
        nextX = nextX(x, y, lambda)
        nextY = nextY(x, y, lambda)

        println("Iteration $iterations -------")
        println("x = $x, y = $y; Q(x,y) = ${Q(x,y)}; lambda = $lambda; nextX = $nextX, nextY = $nextY; Q(nextX,nextY) = ${Q(nextX,nextY)}")
        println("Pk(nextX, nextY) = ${Pk(nextX, nextY)}; Sk(nextX, nextY) = ${Sk(nextX, nextY)}")
        println()
    }

    println("END: nextX = $nextX, nextY = $nextY")
}

fun Q(x: Float, y: Float): Float {
    return x*x + 2f * y*y
}

// dQ/dx
fun Pk(x: Float, y: Float): Float {
    return 2*x
}

// dQ/dy
fun Sk(x: Float, y: Float): Float {
    return 4*y
}

fun nextX(x: Float, y: Float, lambda: Float): Float {
    return x - lambda * Pk(x, y)
}

fun nextY(x: Float, y: Float, lambda: Float): Float {
    return y - lambda * Sk(x, y)
}

fun converges(x: Float, y: Float, lambda: Float): Boolean {
    val nextX = nextX(x, y, lambda)
    val nextY = nextY(x, y, lambda)
    return Q(x, y) - Q(nextX, nextY) >= (lambda/2f) * (Pk(x,y)*Pk(x,y) + Sk(x,y)*Sk(x,y))
}

fun findLambda(x: Float, y: Float, lambda: Float): Float {
    return if(converges(x, y, lambda)) lambda else {
        findLambda(x, y, lambda / 2.0f)
    }
}
