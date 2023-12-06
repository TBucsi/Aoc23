import java.io.File

var winners : MutableSet<Int> = mutableSetOf()
var my_nums : MutableList<Int> = mutableListOf()
var dic : MutableList<Int> = mutableListOf()

fun main() {
    read("input.txt")
    println(dic.sum())
}

fun read(fileName: String) {
    var index = 0
    File(fileName).forEachLine {dic.add(1) }
    File(fileName).forEachLine {find_num(it, index)
                                index += 1}

}

fun find_num(str : String, j : Int){
    winners = mutableSetOf()
    my_nums = mutableListOf()
    var wins = false
    var s = str.substring(9)
    val st = s.split(" ")
    for (i in st){
        if (i.trim() == "|"){
            wins = true
        }

        else if (wins && i != ""){
            my_nums.add(Integer.parseInt(i.trim()))
        }

        else if (i != ""){
            winners.add(Integer.parseInt(i.trim()))
        }
    }
    search_wins(j)
}

fun search_wins(j : Int){
    var points = 0
    for (i in my_nums){
        if (i in winners){
            points += 1
        }
    }

    for (i in 1..points){
        if (j+i < dic.size)
        dic[j+i] += dic[j]
    }

}