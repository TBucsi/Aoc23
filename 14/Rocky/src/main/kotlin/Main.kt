import java.io.File

var all : Int = 0
var rows : MutableList<MutableList<String>> = mutableListOf()

fun main() {
    read("input.txt")
    tilt()
    count()
    println(all)
}

fun read(fileName: String) {
    File(fileName).forEachLine {parse(it)}
}

fun parse(str : String){
    var st = str.split("").toMutableList()
    st.removeAt(0)
    st.removeAt(st.lastIndex)
    rows.add(st)
}

fun tilt(){
    for (i in 0..<rows.size){
        for (j in 0..<rows[i].size){
            if (rows[i][j] == "O" && i != 0){
                var indent = 1
                while (i - indent >= 0 && rows[i - indent][j] != "#" && rows[i - indent][j] != "O"){
                    rows[i - indent + 1][j] = "."
                    rows[i - indent][j] = "O"
                    indent += 1
                }
            }
        }
    }
}

fun count(){
    for (i in 0..<rows.size) {
        for (j in 0..<rows[i].size) {
            if (rows[i][j] == "O") {
                all += rows.size - i
            }
        }
    }
}