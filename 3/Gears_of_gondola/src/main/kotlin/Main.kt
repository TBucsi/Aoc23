import java.io.File

var lst : MutableList<Int> = mutableListOf()
var whole : MutableList<MutableList<String>> = mutableListOf()
val dic : Map<String, Int> = mapOf("0" to 0, "1" to 1, "2" to 2, "3" to 3, "4" to 4, "5" to 5, "6" to 6, "7" to 7,
    "8" to 8, "9" to 9, "." to -1)
var plus = 1
var index = 0

fun main() {
    var num = 0
    readFileLineByLineUsingForEachLine("input.txt")
    find_num(whole)
    println(lst.sum())
}

fun readFileLineByLineUsingForEachLine(fileName: String) {
    File(fileName).forEachLine { linework(it) }
}

fun linework(str : String){
    var st = str.split("").toMutableList()
    st.removeAt(0)
    st.removeAt(st.size - 1)
    whole.add(st)
}

fun find_num(ls : MutableList<MutableList<String>>){
    var sets : MutableSet<Int> = mutableSetOf()

    for (i in 0..<ls.size){
        for (j in 0..<ls[i].size){
            if (ls[i][j] == "*"){
                sets = find_nums(i,j,ls)
                if (sets.size == 2){
                    lst.add(sets.first() * sets.last())
                }
            }
        }
    }

}

fun find_nums(i : Int, j : Int, lst : MutableList<MutableList<String>>) : MutableSet<Int>{
    var bruh : MutableSet<Int> = mutableSetOf()

    for (k in -1..1){
        for (l in -1..1){
            if (i + k >= 0 && i + k < lst.size && j + l >= 0 && j + l < lst[0].size){
                if (lst[i+k][j+l] in dic.keys && lst[i+k][j+l] != "."){
                    bruh.add(get_num(i+k, j+l, lst))
                }
            }
        }
    }

    return bruh
}

fun get_num(i : Int, j : Int, lst : MutableList<MutableList<String>>) : Int{
    var j2 = j
    var zero = false

    if (j2 + 1 < lst[0].size && j2 - 1 >= 0 && lst[i][j2+1] in dic.keys && lst[i][j2 - 1] in dic.keys){
        while (j2 - 1 >= 0 && lst[i][j2 - 1] != "." && lst[i][j2 - 1] in dic.keys){
            j2 -= 1
        }
    }

    var num = Integer.parseInt(lst[i][j2])
    if (lst[i][j2 + 1] == "." || lst[i][j2 + 1] !in dic.keys){
        index = -1
        plus = -1
    }

    else{
        index = 1
        plus = 1
    }

    if (num == 0){
        zero = true
    }

    while (j2 + index >= 0 && j2 + index < lst[0].size && lst[i][j2 + index] != "." && lst[i][j2 + index] in dic.keys){
        num *= 10
        num += Integer.parseInt(lst[i][j2 + index])
        index += plus
    }

    if (plus == -1){
        num = Integer.parseInt(num.toString().reversed())
        if (zero){
            num *= 10
        }
    }

    return num
}