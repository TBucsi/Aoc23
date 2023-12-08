import java.io.File

var steps : String = ""
var start : String = "AAA"
var end : String = "ZZZ"
var dic : MutableMap<String, List<String>> = mutableMapOf()
var step = 0

fun main() {
    read("input.txt")
    get_to_end()
    println(step)
}

fun read(fileName: String) {
    File(fileName).forEachLine {parse(it)}
}

fun parse(str : String){
    if (steps == ""){
        steps = str
    }

    else{
        if (str != ""){
            val st = str.split(" ")
            val lst = listOf(st[2].substring(1, 4), st[3].substring(0,3))
            dic[st[0]] = lst
        }
    }
}

fun get_to_end(){
    var standing = start
    var index = 0
    while (standing != end){
        if (index == steps.length){
            index = 0
        }

        if (steps[index] == 'L'){
            standing = dic[standing]!![0]
        }

        else{
            standing = dic[standing]!![1]
        }

        step += 1
        index += 1
    }
}
