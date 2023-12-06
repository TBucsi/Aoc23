import java.io.File

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
    var dic : MutableMap<String, Int> = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)

    for (i in 2..<st.size){
        if (st[i] in dic.keys){
            if (Integer.parseInt(st[i - 1]) > dic[st[i]]!!){
                dic[st[i]] = Integer.parseInt(st[i - 1])
            }
        }
    }

    var ret = 1
    for (i in dic.values){
        ret *= i
    }

    lst.add(ret)
}