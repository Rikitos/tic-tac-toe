import kotlin.math.abs

fun main() {
    var emptyBoard = charArrayOf(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ')

    println("---------")
    println("| ${emptyBoard[0]} ${emptyBoard[1]} ${emptyBoard[2]} |")
    println("| ${emptyBoard[3]} ${emptyBoard[4]} ${emptyBoard[5]} |")
    println("| ${emptyBoard[6]} ${emptyBoard[7]} ${emptyBoard[8]} |")
    println("---------")


    var winningBoard = 0
    var coor1 = 0
    var coor2 = 0
    var validCoor = false
    var digit = false
    var occupation = false
    var xAmount = 0
    var oAmount = 0
    var winner = false
    var draw = false

    fun turn(): Int {
        xAmount = 0
        oAmount = 0
        for (i in 0..emptyBoard.lastIndex) {
            if (emptyBoard[i] == 'X') xAmount++
            else if (emptyBoard[i] == 'O') oAmount++
        }
        return abs(xAmount - oAmount)
    }

    fun printBoard() {
        println("---------")
        println("| ${emptyBoard[0]} ${emptyBoard[1]} ${emptyBoard[2]} |")
        println("| ${emptyBoard[3]} ${emptyBoard[4]} ${emptyBoard[5]} |")
        println("| ${emptyBoard[6]} ${emptyBoard[7]} ${emptyBoard[8]} |")
        println("---------")
    }

    fun checkHorizontally(xRo: Char): Boolean {
        var exist = false
        for (i in 0..6 step 3) {
            if (xRo == emptyBoard[i] && xRo == emptyBoard[i + 1] && xRo == emptyBoard[i + 2]) {
                winningBoard++
                exist = true
            }
        }
        return exist
    }

    fun checkUpRight(xRo: Char): Boolean {
        var exist = false
        for (i in 0..2) {
            if (xRo == emptyBoard[i] && xRo == emptyBoard[i + 3] && xRo == emptyBoard[i + 6]) {
                winningBoard++
                exist = true
            }
        }
        return exist
    }

    fun checkDiagonal(xRo: Char): Boolean {
        var exist = false
        if (xRo == emptyBoard[0] && xRo == emptyBoard[4] && xRo == emptyBoard[8]) {
            winningBoard++
            exist = true
        }
        if (xRo == emptyBoard[2] && xRo == emptyBoard[4] && xRo == emptyBoard[6]) {
            winningBoard++
            exist = true
        }
        return exist
    }

    var xUpRight = checkUpRight('X');
    var xHorizontal = checkHorizontally('X');
    var xDiagonal = checkDiagonal('X');

    var oUpRight = checkUpRight('O');
    var oHorizontal = checkHorizontally('O');
    var oDiagonal = checkDiagonal('O');

    fun checkWinner() {
        if (xUpRight || xHorizontal || xDiagonal) println("X wins")
        else if (oUpRight || oHorizontal || oDiagonal) println("O wins")
    }

    fun getCoordinates() {
        for (i in 0..2) {
            if (coor1 == 1 && coor2 == i + 1) {
                if (emptyBoard[i] != 'X' && emptyBoard[i] != 'O') {
                    if (turn() == 0) emptyBoard[i] = 'X'
                    else emptyBoard[i] = 'O'
                    occupation = false
                } else occupation = true
            } else if (coor1 == 2 && coor2 == i + 1) {
                if (emptyBoard[3 + i] != 'X' && emptyBoard[3 + i] != 'O') {
                    if (turn() == 0) emptyBoard[i + 3] = 'X'
                    else emptyBoard[i + 3] = 'O'
                    occupation = false
                } else occupation = true
            } else if (coor1 == 3 && coor2 == i + 1) {
                if (emptyBoard[6 + i] != 'X' && emptyBoard[6 + i] != 'O') {
                    if (turn() == 0) emptyBoard[i + 6] = 'X'
                    else emptyBoard[i + 6] = 'O'
                    occupation = false
                } else occupation = true
            }
        }
    }

    do {
        do {
            print("Enter the coordinates: ")
            val (cor1, cor2) = readLine()!!.trim().split(" ")
            for (i in 0..cor1.lastIndex) {
                if (cor1[i].isDigit()) {
                    coor1 = cor1[i].toString().toInt()
                    digit = true
                } else digit = false
            }
            for (i in 0..cor2.lastIndex) {
                if (cor2[i].isDigit()) {
                    coor2 = cor2[i].toString().toInt()
                    digit = true
                } else digit = false
            }
            if (digit && coor1 in 1..3 && coor2 in 1..3) {
                getCoordinates()
                if (!occupation) {
                    validCoor = true
                }
            } else if (digit) {
                println("Coordinates should be from 1 to 3!")
            }
            if (digit && coor1 in 1..3 && coor2 in 1..3 && occupation) println("This cell is occupied! Choose another one!")
            if (!digit) println("You should enter numbers")
            xUpRight = checkUpRight('X')
            xHorizontal = checkHorizontally('X')
            xDiagonal = checkDiagonal('X')
            oUpRight = checkUpRight('O');
            oHorizontal = checkHorizontally('O');
            oDiagonal = checkDiagonal('O');
            if (xUpRight || xHorizontal || xDiagonal || oUpRight || oHorizontal || oDiagonal) winner = true
            if (!occupation) printBoard()
            checkWinner()
            if (!emptyBoard.contains(' ') && !winner) {
                draw = true
                println ("Draw")
            }
        } while (!validCoor && !draw)
    } while (!winner && !draw)
}