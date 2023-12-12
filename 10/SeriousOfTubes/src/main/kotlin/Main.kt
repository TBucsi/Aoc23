import java.io.File

var contain : MutableList<MutableList<String>> = mutableListOf()
var x = 0
var y = 0
var index = 0

fun main() {
    read("input.txt")
    find_max()
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
    var lst = find_pipes()
    var steps = 1
    var bx1 = x
    var bx2 = x
    var by1 = y
    var by2 = y
    var x1 = lst[0]
    var x2 = lst[1]
    var y1 = lst[2]
    var y2 = lst[3]

    while (x1 != x2 || y1 != y2){
        steps += 1
        val lst1 = find_next_step(contain[x1][y1], x1, y1)
        val lst2 = find_next_step(contain[x2][y2], x2, y2)
        var rl = reset(lst1, bx1, by1, x1, y1)
        bx1 = rl[0]
        by1 = rl[1]
        x1 = rl[2]
        y1 = rl[3]
        var rl2 = reset(lst2, bx2, by2, x2, y2)
        bx2 = rl2[0]
        by2 = rl2[1]
        x2 = rl2[2]
        y2 = rl2[3]

    }

    println(steps)
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