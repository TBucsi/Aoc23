import java.io.File

var all : Int = 0
var rows : MutableList<String> = mutableListOf()
var cols : MutableList<String> = mutableListOf()

fun main() {
    read("input.txt")
    println(all)
}

fun read(fileName: String) {
    File(fileName).forEachLine {parse(it)}
}

fun parse(str : String){
    if (str != "") {
        rows.add(str)
    }

    else{
        for (i in 0..<rows[0].length){
            var c = ""
            for (j in 0..<rows.size){
                c += rows[j][i]
            }
            cols.add(c)
        }
        mirror(rows, 1)
        mirror(cols, 0)
        rows.clear()
        cols.clear()
    }
}

fun mirror(r : MutableList<String>, c : Int){
    println(r)
    for (i in 0..<r.size - 1){
        if (r[i] == r[i + 1]){
            var x1 = i
            var x2 = i + 1
            var count = true
            while (x1 > 0 && x2 < r.size-1){
                x1 -= 1
                x2 += 1
                if (r[x1] != r[x2]){
                    count = false
                }
            }
            if (count){
                all += (100 * c) * (i + 1)
            }
            if (count && c == 0){
                all += i + 1
            }
        }
    }
}

