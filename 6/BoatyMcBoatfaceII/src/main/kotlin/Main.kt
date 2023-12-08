import java.io.File
import kotlin.math.min

var time : MutableList<String> = mutableListOf()
var records : MutableList<String> = mutableListOf()
var tim : String = ""
var rec : String = ""

fun main() {
    read("input.txt")
    tim = time.joinToString(separator="") { it }
    rec = records.joinToString(separator="") { it }
    calculate(tim.toLong(), rec.toLong())

}

fun read(fileName: String) {
    File(fileName).forEachLine {parse(it) }
}

fun parse(str : String){
    var st = str.split("\\s+".toRegex())
    if (st[0] == "Time:"){
        for (i in 1..<st.size){
            time.add(st[i].trim())
        }
    }

    if (st[0] == "Distance:"){
        for (i in 1..<st.size){
            records.add(st[i].trim())
        }
    }
}

fun calculate(tim : Long, rec : Long){
    var count = 0
    for (i in 0..<tim){
        if ((i * (tim - i)) > rec){
            count += 1
        }
    }
    println(count)
}