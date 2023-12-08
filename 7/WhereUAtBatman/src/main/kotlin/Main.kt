import java.io.File
import kotlin.math.min

var cards : Map<String, Int> = mapOf("A" to 13, "K" to 12, "Q" to 11, "J" to 0, "T" to 9, "9" to 8, "8" to 7, "7" to 6,
    "6" to 5, "5" to 4, "4" to 3, "3" to 2, "2" to 1)
var dic : MutableMap<String, Int> = mutableMapOf()
var one : MutableList<String> = mutableListOf()
var two : MutableList<String> = mutableListOf()
var three : MutableList<String> = mutableListOf()
var four : MutableList<String> = mutableListOf()
var five : MutableList<String> = mutableListOf()
var num_of_cards : Map<Int, MutableList<String>> = mutableMapOf(1 to one, 2 to two, 3 to three, 4 to four, 5 to five)
var sum = 0
var rank = 0
val compareing : Comparator<String> = object : Comparator<String> {
    override fun compare(s1: String, s2: String): Int {
        for (i in 0..<s1.length){
            if (cards[s1[i].toString()]!! < cards[s2[i].toString()]!!){
                return 1
            }

            else if (cards[s1[i].toString()]!! > cards[s2[i].toString()]!!){
                return -1
            }
        }
        return 0
    }
}


fun main() {
    read("input.txt")
    sort_one()
    sort_t(4)
    sort_t(3)
    sort_else()
    println(sum)
    println(rank)
}

fun read(fileName: String) {
    File(fileName).forEachLine {parse(it)
        rank += 1}
}

fun parse(str : String){
    val st = str.split(" ")
    dic[st[0]] = Integer.parseInt(st[1])
    var kinds : MutableSet<Char> =  mutableSetOf()

    for (i in st[0]){
        kinds.add(i)
    }

    if (kinds.contains('J')){
        kinds.remove('J')
    }
    if (kinds.size == 0){
        one.add(st[0])
    }
    else {
        num_of_cards[kinds.size]?.add(st[0])
    }
}

fun sort_one() {
    one.sortWith(compareing)
    for (i in one) {
        sum += dic[i]!! * rank
        rank -= 1
    }
}

fun sort_t(which : Int){
    var big_one = 3
    var bruh = two

    if (which == 4) {
        big_one = 4
    }

    if (big_one == 3){
        bruh.clear()
        bruh = three
    }
    var fourk : MutableList<String> = mutableListOf()
    var house : MutableList<String> = mutableListOf()
    var numbers : MutableMap<Char, Int> = mutableMapOf()
    for (i in bruh){
        for (j in i){
            if (j in numbers.keys){
                numbers[j] = numbers[j]!! + 1
            }

            else{
                numbers.put(j,1)
            }
        }
        var big = 0
        var bigger = ' '
        for (j in numbers.keys){
            if (numbers[j]!! > big && j != 'J'){
                big = numbers[j]!!
                bigger = j
            }

            if (numbers[j]!! == big && j != 'J' && cards[j.toString()]!! > cards[bigger.toString()]!!){
                    big = numbers[j]!!
                    bigger = j
                }


        }
        if ('J' in numbers.keys) {
            numbers[bigger] = numbers['J']?.let { numbers[bigger]?.plus(it) }!!
            println(i)
            println(numbers)
        }

        if (big_one in numbers.values){
            fourk.add(i)
        }
        else{
            house.add(i)
        }
        numbers.clear()
    }
    fourk.sortWith(compareing)

    for (i in fourk){
        sum += dic[i]!! * rank
        rank -= 1
    }

    house.sortWith(compareing)

    for(i in house) {
        sum += dic[i]!! * rank
        rank -= 1
    }
}

fun sort_else(){
    four.sortWith(compareing)
    for (i in four){
        sum += dic[i]!! * rank
        rank -= 1
    }
    five.sortWith(compareing)
    for (i in five){
        sum += dic[i]!! * rank
        rank -= 1
    }

}