import java.io.File

var all : Int = 0
var rows : MutableList<MutableList<String>> = mutableListOf()
var c_rows : MutableMap<Int, MutableList<MutableList<String>>> = mutableMapOf()
var start = 0
var end = 0
var l = 0

fun main() {
    read("input.txt")
    for (i in 0..<1_000_000_000){
        for (j in c_rows.keys){
            if (c_rows[j] == rows){
                start = j
                end = i
                l = end - start

            }
        }
        if (l != 0){
            break
        }
        val tmp : MutableList<MutableList<String>> = mutableListOf()
        for (k in rows){
            val tnp : MutableList<String> = mutableListOf()
            tnp.addAll(k)
            tmp.add(tnp)
        }
        c_rows[i] = tmp
        tilt()
    }

    start = 1_000_000_000 - end
    start = start.mod(l)

    for (i in 0..<start){
        tilt()
    }
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

    for (i in 0..<rows.size){
        for (j in 0..<rows[i].size){
            if (rows[i][j] == "O" && j != 0){
                var indent = 1
                while (j - indent >= 0 && rows[i][j - indent] != "#" && rows[i][j - indent] != "O"){
                    rows[i][j - indent + 1] = "."
                    rows[i][j - indent] = "O"
                    indent += 1
                }
            }
        }
    }

    for (i in (0..<rows.size).reversed()){
        for (j in (0..<rows[i].size).reversed()){
            if (rows[i][j] == "O" && i != rows[i].size - 1){
                var indent = 1
                while (i + indent < rows[i].size && rows[i + indent][j] != "#" && rows[i + indent][j] != "O"){
                    rows[i + indent - 1][j] = "."
                    rows[i + indent][j] = "O"
                    indent += 1
                }
            }
        }
    }

    for (i in (0..<rows.size).reversed()){
        for (j in (0..<rows[i].size).reversed()){
            if (rows[i][j] == "O" && j != rows[i].size - 1){
                var indent = 1
                while (j + indent < rows[i].size && rows[i][j + indent] != "#" && rows[i][j + indent] != "O"){
                    rows[i][j + indent - 1] = "."
                    rows[i][j + indent] = "O"
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