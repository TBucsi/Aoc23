import java.io.File
import kotlin.math.sqrt
import kotlin.math.pow

var steps : String = ""
var start : MutableList<String> = mutableListOf()
var dic : MutableMap<String, List<String>> = mutableMapOf()
var all_nums : MutableMap<Int,Int> = mutableMapOf()
var step = 0

fun main() {
    read("input.txt")
    for (i in start) {
        get_to_end(i)
        val b = primes(step)
        println(step)
        for (j in b.keys){
            if (j in all_nums.keys && b[j]!! > all_nums[j]!!){
                all_nums[j] = b[j]!!
            }
            else{
                all_nums[j] = 1
            }
        }
    }
    var sum = 1.0
    for (i in all_nums.keys){
        sum *= i.toDouble().pow(all_nums[i]!!.toDouble())
    }
    println(all_nums)
    print(sum.toLong())
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

            if (st[0][2] == 'A'){
                start.add(st[0])
            }
        }
    }
}

fun get_to_end(s : String){
    var standing = s
    var index = 0
    step = 0
    while (standing[2] != 'Z'){
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

fun primes(num : Int) : MutableMap<Int, Int>{
    var pain : MutableMap<Int, Int> = mutableMapOf()
    var wnum = num
    while(wnum % 2 == 0){
        wnum /= 2
        if (2 in pain.keys){
            pain[2] = pain[2]!! + 1
        }
        else{
            pain[2] = 1
        }
    }

    val sq = sqrt(wnum.toDouble()).toInt()

    for (i in 3..sq){
        while (wnum % i == 0){
            if (i in pain.keys){
                pain[i] = pain[i]!! + 1
            }
            else{
                pain[i] = 1
            }
            wnum /= i
        }

    }

    if (wnum > 2){
        pain[wnum] = 1
    }

    return pain

}
