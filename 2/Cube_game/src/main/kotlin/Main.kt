import java.io.File

val dic :Map<String, Int> = mapOf("red" to 12, "green" to 13, "blue" to 14)
var lst : MutableList<Int> = mutableListOf()

fun main() {
    var num = 0
    readFileLineByLineUsingForEachLine("input.txt")

    println(lst.sum())
}

fun readFileLineByLineUsingForEachLine(fileName: String) {
    File(fileName).forEachLine { find_num(it) }
}

fun find_num(str : String){
    var s = str.replace(",", "")
    s = s.replace(";", "")
    s = s.replace(":", "")

    val st = s.split(" " )
    var good = true

    for (i in 2..<st.size){
        if (st[i] in dic.keys){
            if (Integer.parseInt(st[i - 1]) > dic[st[i]]!!){
                good = false
            }
        }
    }

    if (good){
        lst.add(Integer.parseInt(st[1]))
    }

}