import java.io.File

var lst : MutableList<Long> = mutableListOf()
var winners : MutableSet<Int> = mutableSetOf()
var my_nums : MutableList<Int> = mutableListOf()

fun main() {
    read("input.txt")
    println(lst.sum())
}

fun read(fileName: String) {
    File(fileName).forEachLine { find_num(it) }
}

fun find_num(str : String){
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
    search_wins()
}

fun search_wins(){
    var points = 0L
    for (i in my_nums){
        if (i in winners){
            if (points == 0L){
                points = 1L
            }

            else{
                points *= 2
            }
        }
    }
    lst.add(points)
}