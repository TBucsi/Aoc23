import java.io.File
import kotlin.math.min

var lst : MutableList<Long> = mutableListOf()
var seeeeeed :MutableList<MutableList<MutableList<Long>>> = mutableListOf()
var sed : MutableList<Long> = mutableListOf()

fun main() {
    read("input.txt")
    find_min()
    println(lst.min())
}

fun read(fileName: String) {
    File(fileName).forEachLine {parse(it) }
}

fun parse(str : String){
    var st = str.split(" ")
    if (st[0] == ""){
    }

    else if (st[0] == "seeds:"){
        for (i in 1..<st.size){
            sed.add(st[i].toLong())
        }
    }

    else if (st[1] == "map:"){
        seeeeeed.add(mutableListOf())
    }

    else{
        var ints : MutableList<Long> = mutableListOf()
        for (i in st){
            ints.add(i.toLong())
        }
        seeeeeed[seeeeeed.lastIndex].add(ints)
    }
}

fun find_min(){
    for (i in sed){
        var num = i
        var next_num = 0L
        for (category in seeeeeed){
            for (range in category){
                if (num >= range[1] && num < range[1] + range[2]){
                    next_num = range[0] + (num - range[1])
                }
            }
            if (next_num != 0L){
                num = next_num
            }

        }
        lst.add(num)
    }
}