import java.io.File
import kotlin.math.min

var lst : MutableList<Int> = mutableListOf()
var time : MutableList<Int> = mutableListOf()
var records : MutableList<Int> = mutableListOf()

fun main() {
    read("input.txt")
    for (i in 0..<time.size){
        calculate(i)
    }

    var bruh = 1

    for (i in lst){
        bruh *= i
    }

    println(bruh)
}

fun read(fileName: String) {
    File(fileName).forEachLine {parse(it) }
}

fun parse(str : String){
    var st = str.split("\\s+".toRegex())
    if (st[0] == "Time:"){
        for (i in 1..<st.size){
            time.add(Integer.parseInt(st[i].trim()))
        }
    }

    if (st[0] == "Distance:"){
        for (i in 1..<st.size){
            records.add(Integer.parseInt(st[i].trim()))
        }
    }
}

fun calculate(index : Int){
    val tim = time[index]
    val rec = records[index]
    var count = 0
    for (i in 0..<tim){
        if ((i * (tim - i)) > rec){
            println(rec)
            println((i * (tim - (i + 1))))
            count += 1
        }
    }
    lst.add(count)
}