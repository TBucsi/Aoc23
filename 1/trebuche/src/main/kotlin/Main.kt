import java.io.File
import java.util.Dictionary

var lst : MutableList<Int> = mutableListOf()
var dic : Map<String, Int> = mapOf("one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5, "six" to 6, "seven"
                             to 7, "eight" to 8, "nine" to 9)

fun main(args: Array<String>) {
    var num = 0
    var index = 1
    readFileLineByLineUsingForEachLine("input.txt")
    for (i in lst){
        num += i


        println(i)
        index+=1
    }
    println(num)
}

fun readFileLineByLineUsingForEachLine(fileName: String) {
    File(fileName).forEachLine { find_num(it) }
}

fun find_num(str : String){
    var first = -1
    var last = -1
    var index = 0
    for (i in str){
        if (index + 3 <= str.length) {
            val maybe_num: String = str.substring(index, index + 3)
            for (j in dic.keys){
                if (j == maybe_num || (j == maybe_num + "ee" && str[index+3] == 'e') || (j == maybe_num + "r" && str[index + 3] == 'r')
                    || (j == maybe_num + "e" && str[index+3] == 'e') || (j == maybe_num + "en" && str[index+3] == 'e')
                    || (j == maybe_num + "ht" && str[index + 3] == 'h') ){
                    if (first == -1){
                        first = dic[j]!!
                    }
                    else{
                        last = dic[j]!!
                    }
                }
            }
        }

        if (i in '0'..'9'){
            if (first == -1){
                first = i.digitToInt()
            }
            else{
                last = i.digitToInt()
            }
        }
        index += 1
    }

    if (last == -1){
        lst.add((first * 10) + first)
    }

    else{
        lst.add((first * 10) + last)
    }
}