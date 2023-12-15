import java.io.File

var all : Int = 0
var start = 0
val dic = MutableList(256) { mutableListOf<String>() }

fun main() {
    read("input.txt")
    counting()
    println(all)
}

fun read(fileName: String) {
    File(fileName).forEachLine {parse(it)}
}

fun parse(str : String){
    var st = str.split(",").toMutableList()
    for (i in st){
        decipher(i.trim())
    }
}

fun decipher(str : String){
    var cod = ""

    if ('-' in str){
        cod = str
        cod = cod.removeSuffix("-")
        if (cod in dic[hash(cod)]){
            val index = dic[hash(cod)].indexOf(cod)
            dic[hash(cod)].removeAt(index)
            dic[hash(cod)].removeAt(index)
        }
    }

    else if ('=' in str) {
        cod = str
        val st = cod.split("=")

        if (st[0] in dic[hash(st[0])]) {
            val index = dic[hash(st[0])].indexOf(st[0])
            dic[hash(st[0])][index + 1] = st[1]

        } else {
            dic[hash(st[0])].addAll(st.toMutableList())
        }
    }
}

fun hash(str: String) : Int{
    start = 0
    for (i in str){
        start += i.code
        start *= 17
        start = start.mod(256)
    }
    return start
}

fun counting(){
    for (i in 0..<dic.size){
        for (j in 1..<dic[i].size step(2)){
            all += (i + 1) * ((j + 1) / 2) * Integer.parseInt(dic[i][j])
        }
    }
}