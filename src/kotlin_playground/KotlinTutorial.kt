package kotlin_playground

fun printName(name: String) {
    println("My name is $name")
}

fun main() {
    /*var nameArr = arrayOf("Prakash", "Rohan", "Rahul")
    var nameArr2 = arrayListOf<String>("Prakash1", "Rohan1", "Rahul1")*/

    /*nameArr2.forEachIndexed { index, it ->
        printName(it)
    }*/

    val map = mapOf<Int, Int>(1 to 1, 2 to 3 , 3 to 4)
    map.entries.forEach {
        println("key ${it.key} value ${it.value}")
    }




}



