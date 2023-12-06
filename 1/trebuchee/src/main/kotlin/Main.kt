import java.io.File

var lst : MutableList<Int> = mutableListOf()

fun main(args: Array<String>) {
    var num = 0
    readFileLineByLineUsingForEachLine("input.txt")
    for (i in lst){
        num += i
    }

    println(num)
}

fun readFileLineByLineUsingForEachLine(fileName: String) {
    File(fileName).forEachLine { find_num(it) }
}

fun find_num(str : String){
    var first = -1
    var last = -1
    for (i in str){
        if (i in '0'..'9'){
            if (first == -1){
                first = i.digitToInt()

            }
            else{
                last = i.digitToInt()
            }
        }
    }

    if (last == -1){
        lst.add((first * 10) + first)
    }

    else{
        lst.add((first * 10) + last)
    }
}