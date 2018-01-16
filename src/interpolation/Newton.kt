package interpolation

/**
 * Created by tikhon.osipov on 16.01.2018
 */
fun main(args: Array<String>) {
    val finiteDifferences = mutableListOf<MutableList<Float>>()

    val x = mutableListOf(0.0f, 0.1f, 0.2f, 0.3f, 0.4f)
    val y = mutableListOf(1.9800f, 1.9048f, 1.8187f, 1.7408f, 1.6703f)

    println("Исходные данные:")
    x.zip(y).forEachIndexed { i, pair -> println("x$i = ${pair.first}; y$i = ${pair.second}") }

    var tempDifferences = findFiniteDifferences(y)
    do {
        finiteDifferences.add(tempDifferences)
        tempDifferences = findFiniteDifferences(tempDifferences)
    } while (tempDifferences.any { it != 0.0f })

    println()
    println("Конечные разности:")
    finiteDifferences.forEach {
        println(it)
    }
}

fun findFiniteDifferences(list: MutableList<Float>): MutableList<Float> {
    val result = mutableListOf<Float>()

    if(list.size < 2) return result

    for(i in 1 until list.size) {
        result.add(list[i] - list[i-1])
    }
    return result
}
