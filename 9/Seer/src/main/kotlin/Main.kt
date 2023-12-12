import java.io.File

var lst : MutableList<Int> = mutableListOf()
var contain : MutableList<MutableList<Int>> = mutableListOf()

fun main() {
    read("input.txt")
    println(lst.sum())
}

fun read(fileName: String) {
    File(fileName).forEachLine {parse(it)}
}

fun parse(str : String){
    val st = str.split(" ")
    var sent : MutableList<Int> = mutableListOf()
    st.forEach { sent.add(Integer.parseInt(it.trim())) }
    find_num(sent)
}

fun find_num(start : MutableList<Int>){
    var check_set : MutableSet<Int> = mutableSetOf()
    check_set.addAll(start)
    contain.clear()
    contain.add(start)
    var work : MutableList<Int> = contain[contain.lastIndex]

    while (check_set.size != 1){
        var step : MutableList<Int> = mutableListOf()
        for (i in 0..<work.size-1){
            step.add(work[i+1] - work[i])
        }
        check_set.clear()
        check_set.addAll(step)
        contain.add(step)
        work = contain[contain.lastIndex]
    }
    var num = 0
    println(contain)
    for (i in contain){
        num += i[i.lastIndex]
    }
    lst.add(num)
}
