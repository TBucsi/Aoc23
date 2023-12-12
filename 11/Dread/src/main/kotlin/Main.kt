import java.io.File
import kotlin.math.abs

val galaxy : MutableList<MutableList<String>> = mutableListOf()
var where : MutableList<List<Int>> = mutableListOf()
var distance : Int = 0

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
    val rows : MutableList<Int> = mutableListOf()
    val columns : MutableList<Int> = mutableListOf()
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

    put_empty(rows, columns)
}

fun put_empty(r : MutableList<Int>, c : MutableList<Int>){
    val insert : MutableList<String> = mutableListOf()
    for (i in 0..<galaxy[0].size){
        insert.add(".")
    }
    var addage = 0

    for (i in r){
        galaxy.add(i + addage, insert)
        addage += 1
    }

    addage = 0
    for (i in c){
        for (j in 0..<galaxy.size) {
            galaxy[j].add(i + addage, ".")
        }
        addage += 1
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
    for (i in galaxy){
        println(i)
    }
    find_g()
    for (i in 0..<where.size){
        for (j in i+1..<where.size){
            distance += abs(where[j][0] - where[i][0]) + abs(where[j][1] - where[i][1])
        }
    }
}