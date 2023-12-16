import java.io.File

var all : MutableList<Int> = mutableListOf()
var change = true
var field : MutableList<MutableList<String>> = mutableListOf()
var lasers : MutableList<MutableList<Int>> = mutableListOf()
var done : MutableList<MutableList<Int>> = mutableListOf()
var clean : MutableSet<MutableList<Int>> = mutableSetOf()
var potion : MutableSet<List<Int>> = mutableSetOf()
var dict : Map<Int, Pair<Int,Int>> = mapOf(0 to Pair(0, 1), 1 to Pair(0, -1), 2 to Pair(-1, 0), 3 to Pair(1, 0))

fun main() {
    read("input.txt")
    //Part 1
    //lasers.add(mutableListOf(0, 0, 0))
    //navigate()
    //println(potion.size)

    //Part 2
    for (i in 0..<field.size) {
        lasers.add(mutableListOf(i, 0, 0))
        navigate()
        all.add(potion.size)
        change = true
        lasers = mutableListOf()
        done = mutableListOf()
        clean = mutableSetOf()
        potion = mutableSetOf()
    }
    for (i in 0..<field.size) {
        lasers.add(mutableListOf(i, field.size - 1, 1))
        navigate()
        all.add(potion.size)
        change = true
        lasers = mutableListOf()
        done = mutableListOf()
        clean = mutableSetOf()
        potion = mutableSetOf()
    }

    for (i in 0..<field.size) {
        lasers.add(mutableListOf(0, i, 3))
        navigate()
        all.add(potion.size)
        change = true
        lasers = mutableListOf()
        done = mutableListOf()
        clean = mutableSetOf()
        potion = mutableSetOf()
    }

    for (i in 0..<field.size) {
        lasers.add(mutableListOf(field.size - 1, i, 2))
        navigate()
        all.add(potion.size)
        change = true
        lasers = mutableListOf()
        done = mutableListOf()
        clean = mutableSetOf()
        potion = mutableSetOf()
    }

    println(all.max())
}

fun read(fileName: String) {
    File(fileName).forEachLine {parse(it)}
}

fun parse(str : String){
    var st = str.split("").toMutableList()
    st.removeAt(0)
    st.removeAt(st.lastIndex)
    field.add(st)
}

fun navigate(){
    while (change){
        val tmpLasers : MutableList<MutableList<Int>> = mutableListOf()
        val rmLasers : MutableList<MutableList<Int>> = mutableListOf()
        val siz = done.size

        for (i in lasers) {
            if (i !in done){
                done.add(listOf(i[0], i[1], i[2]).toMutableList())
            }

            if (listOf(i[0], i[1]) !in potion ){
                potion.add(listOf(i[0], i[1]))
            }

            if (field[i[0]][i[1]] == "/") {
                if (i[2] == 0) {
                    i[2] = 2
                } else if (i[2] == 1) {
                    i[2] = 3
                } else if (i[2] == 2) {
                    i[2] = 0
                } else if (i[2] == 3) {
                    i[2] = 1
                }
            }

            if (field[i[0]][i[1]] == "\\") {
                if (i[2] == 0) {
                    i[2] = 3
                } else if (i[2] == 1) {
                    i[2] = 2
                } else if (i[2] == 2) {
                    i[2] = 1
                } else if (i[2] == 3) {
                    i[2] = 0
                }

            }

            if (field[i[0]][i[1]] == "|") {
                if (i[2] == 0) {
                    i[2] = 2
                }
                if (i[2] == 1) {
                    i[2] = 2
                }
                if (i[0] + 1 < field.size && listOf(i[0] + 1, i[1], 3) !in done) {
                    tmpLasers.add(mutableListOf(i[0] + 1, i[1], 3))
                }
            }

            if (field[i[0]][i[1]] == "-") {
                if (i[2] == 2) {
                    i[2] = 0
                }
                if (i[2] == 3) {
                    i[2] = 0
                }
                if ( i[1] > 0 && listOf(i[0], i[1] - 1, 1) !in done) {
                    tmpLasers.add(mutableListOf(i[0], i[1] - 1, 1))
                }
            }

            i[0] += dict[i[2]]!!.first
            i[1] += dict[i[2]]!!.second

            if (i[0] < 0 || i[1] < 0 || i[0] == field.size || i[1] == field[0].size) {
                rmLasers.add(i)
            }
        }

        for (i in rmLasers){
            lasers.remove(i)
        }

        clean.addAll(lasers)
        lasers.clear()
        lasers.addAll(clean)
        clean.clear()

        if (siz == done.size && tmpLasers.size == 0){
            change = false
        }

        for (i in tmpLasers){
            if (i !in done) {
                lasers.add(i)
            }
        }

    }
}


