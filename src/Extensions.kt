/**
 * Created by tikhon.osipov on 16.01.2018.
 */

fun Double.toSmallString(range: Int = 3): String {

    val string = this.toString()
    val dotIndex = string.indexOf(".")

    return try {
        string.substring(0, dotIndex + 1 + range)
    } catch (e: StringIndexOutOfBoundsException) {
        string
    }
}
