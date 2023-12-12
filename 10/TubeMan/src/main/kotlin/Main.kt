import java.io.File

var contain : MutableList<MutableList<String>> = mutableListOf()
var loops : MutableList<List<Int>> = mutableListOf()
var x = 0
var y = 0
var index = 0
var full = 0

fun main() {
    read("input.txt")
    find_max()
    println(full)
    for (i in contain){
        println(i)
    }
    println(full)
}

fun read(fileName: String) {
    File(fileName).forEachLine {parse(it, index)
        index += 1}
}

fun parse(str : String, ind : Int){
    var st = str.split("").toMutableList()
    st.removeAt(0)
    st.removeAt(st.lastIndex)
    if ("S" in st){
        x = index
        y = st.indexOf("S")
    }
    contain.add(st)
}

fun find_max(){
    val lst = find_pipes()
    var bx = x
    var by = y
    var x1 = lst[0]
    var y1  = lst[2]
    loops.add(listOf(x1,y1))

    while (x1 != x || y1 != y){
        val lsd = find_next_step(contain[x1][y1], x1, y1)
        val next_ones = reset(lsd, bx, by, x1, y1)
        bx = next_ones[0]
        by = next_ones[1]
        x1 = next_ones[2]
        y1 = next_ones[3]
        loops.add(listOf(x1,y1))
    }
    clean_up()
    count()

}

fun count(){
    for (i in 0..<contain.size) {
        var inside = false
        var last = ""
        for (j in 0..<contain[i].size) {
            if (contain[i][j] == "S"){
                inside = !inside
            }
            if (contain[i][j] == "|"){
                inside = !inside
            }
            else if (contain[i][j] == "F" || contain[i][j] == "L"){
                last = contain[i][j]
            }
            else if ((contain[i][j] == "7" && last == "F") || (contain[i][j] == "J" && last == "L")){
                last = ""
            }

            else if ((contain[i][j] == "J" && last == "F") || (contain[i][j] == "7" && last == "L")){
                inside = !inside
            }
            else if (contain[i][j] == " " && inside){
                full += 1
            }

        }
    }
}

fun clean_up(){
    for (i in 0..<contain.size){
        for (j in 0..<contain[i].size){
            if (listOf(i,j) !in loops){
                contain[i][j] = " "
            }
        }
    }
}

fun reset(lst : List<Int>, bx : Int, by : Int, x : Int, y : Int): List<Int>{
    var rbx = 0
    var rby = 0
    var rx = 0
    var ry = 0

    if (bx == lst[0] && by == lst[1]){
        rbx = x
        rx = lst[2]
        rby = y
        ry = lst[3]
    }
    else{
        rbx = x
        rx = lst[0]
        rby = y
        ry = lst[1]
    }
    return listOf(rbx, rby, rx, ry)
}

fun find_next_step(input : String, x : Int, y : Int): List<Int>{
    if (input == "|"){
        return listOf(x + 1, y, x - 1, y)
    }
    if (input == "-"){
        return listOf(x, y + 1, x, y - 1)
    }
    if (input == "L"){
        return listOf(x - 1, y, x, y + 1)
    }
    if (input == "J"){
        return listOf(x - 1, y, x, y - 1)
    }
    if (input == "7"){
        return listOf(x + 1, y, x, y - 1)
    }
    if (input == "F"){
        return listOf(x + 1, y, x, y + 1)
    }
    return listOf()
}

fun find_pipes(): List<Int>{
    var x1 = 0
    var y1 = 0
    var x2 = 0
    var y2 = 0
    if (x - 1 > 0 && (contain[x-1][y] == "7" || contain[x-1][y] == "|" || contain[x-1][y] == "F")){
        x1 = x-1
        y1 = y
    }

    if (y - 1 > 0 && (contain[x][y-1] == "-" || contain[x][y-1] == "L" || contain[x][y-1] == "F")){
        if (x1 == 0){
            x1 = x
            y1 = y-1
        }
        else{
            x2 = x
            y2 = y-1
        }
    }

    if (y + 1 < contain[x].size && (contain[x][y+1] == "-" || contain[x][y+1] == "7" || contain[x][y+1] == "J")){
        if (x1 == 0){
            x1 = x
            y1 = y+1
        }
        else{
            x2 = x
            y2 = y+1
        }
    }

    if (x + 1 < contain.size && (contain[x+1][y] == "|" || contain[x+1][y] == "L" || contain[x+1][y] == "J")){
        if (x2 == 0){
            x2 = x+1
            y2 = y
        }
    }

    return listOf(x1, x2, y1, y2)
}