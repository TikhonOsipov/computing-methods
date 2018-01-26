package integration

/**
 * Created by tikhon.osipov on 26/01/2018
 */
fun main(args: Array<String>) {


    var y = 1f
    var nextY = 0f
    var h = 0.5f

    fun f(x: Float, y: Float): Float = x*x

    fun k1(x: Float, y: Float): Float {
        return f(x, y)
    }

    fun k2(x: Float, y: Float): Float {
        return f(x + h/2, y + h*k1(x,y) /2)
    }

    fun k3(x: Float, y: Float): Float {
        return f(x + h/2, y + h*k2(x,y) /2)
    }

    fun k4(x: Float, y: Float): Float {
        return f(x + h, y + h*k3(x,y))
    }

    var x = 0f

    nextY = y + (h/6) * (k1(x,y) + 2*k2(x,y) + 2*k3(x,y) + k4(x,y))

    for(i in 0 .. 10) {
        y = nextY
        nextY = y + (h/6) * (k1(x,y) + 2*k2(x,y) + 2*k3(x,y) + k4(x,y))

        println("nextY: $nextY")
    }

}
