import java.io.File

var all : Int = 0
var start = 0


fun main() {
    read("input.txt")
    println(all)
}

fun read(fileName: String) {
    File(fileName).forEachLine {parse(it)}
}

fun parse(str : String){
    var st = str.split(",").toMutableList()
    for (i in st){
        hash(i.trim())
    }
}

fun hash(str: String){
    start = 0
    for (i in str){
        start += i.code
        start *= 17
        start = start.mod(256)
    }
    all += start
}