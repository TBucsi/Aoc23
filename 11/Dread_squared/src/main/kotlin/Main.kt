import java.io.File
import kotlin.math.abs

val galaxy : MutableList<MutableList<String>> = mutableListOf()
var where : MutableList<List<Int>> = mutableListOf()
var distance : Long = 0
val rows : MutableList<Int> = mutableListOf()
val columns : MutableList<Int> = mutableListOf()

fun main() {
    read("input.txt")
    find_empty()
    distance()
    println(distance)
}

fun read(fileName: String) {
    File(fileName).forEachLine {parse(it)}
}

fun parse(str : String){
    var st = str.split("").toMutableList()
    st.removeAt(0)
    st.removeAt(st.lastIndex)
    galaxy.add(st)
}

fun find_empty(){
    for (i in 0..<galaxy.size){
        if ("#" !in galaxy[i]){
            rows.add(i)
        }
    }
    for (i in 0..<galaxy[0].size){
        var change = true
        for (j in 0..<galaxy.size){
            if (galaxy[j][i] == "#"){
                change = false
            }
        }
        if (change){
            columns.add(i)
        }
    }
}

fun find_g(){
    for (i in 0..<galaxy.size){
        for (j in 0..<galaxy[i].size){
            if (galaxy[i][j] == "#"){
                where.add(listOf(i,j))
                println(listOf(i,j))
            }
        }
    }
}

fun distance(){
    find_g()
    for (i in 0..<where.size){
        for (j in i+1..<where.size){
            val rm = millions(where[j][0], where[i][0], rows)
            val cm = millions(where[j][1], where[i][1], columns)
            distance += if (where[j][0] > where[i][0]){
                (where[j][0] + rm) - where[i][0]

            } else{
                (where[i][0] + rm) - where[j][0]
            }
            distance += if (where[j][1] > where[i][1]){
                (where[j][1] + cm) - where[i][1]
            } else{
                (where[i][1] + cm) - where[j][1]
            }
        }
    }
}

fun millions(from : Int, to : Int, rc : MutableList<Int>) : Long {
    var ammo = 0
    for (i in rc) {
        if (i in (from + 1)..<to || i in (to + 1)..<from) {
            ammo += 1
        }
    }
    return (ammo * 1_000_000).toLong() - (ammo * 1)
}