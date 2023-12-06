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
    for (i in 0..<sed.size step 2){
        var seds : MutableList<Long> = mutableListOf()
        var seeds : MutableList<Long> = mutableListOf()
        seds.add(sed[i])
        seds.add(sed[i+1])
        for (category in seeeeeed){
            for (ranges in 0..<seds.size step 2) {
                var start = seds[ranges]
                var num = seds[ranges + 1]
                while (num > 0) {
                    for (range in category) {
                        if (start >= range[1] && start < range[1] + range[2]) {
                            seeds.add(range[0] + (start - range[1]))
                            if (seeds[seeds.lastIndex] + num <= range[1] + range[2]) {
                                seeds.add(num)
                                num = 0
                            }

                            else{
                                seeds.add((range[1] + range[2]) - start)
                                start = range[1] + range[2]
                                num -= seeds.lastIndex
                            }
                        }
                    }
                    seeds.add(start)
                    seeds.add(num)
                    num = 0
                }
            }
            seds.clear()
            seds.addAll(seeds)
            seeds.clear()
        }
        var mini = Long.MAX_VALUE
        for (i in 0..<seds.size step 2){
            if (seds[i] < mini){
                mini = seds[i]
            }
        }
        lst.add(mini)
    }
}