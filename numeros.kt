import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    print("Digite a quantidade de números primos (N): ")
    val n = scanner.nextInt()

    if (n <= 0) {
        println("Por favor, digite um número inteiro maior que 0.")
        return
    }

    println("\nOs primeiros $n$ números primos são:")
    
    var contador = 0
    var numero = 2

    while (contador < n) {
        if (ehPrimo(numero)) {
            print("$numero ")
            contador++
        }
        numero++
    }
    println()
}

// Função auxiliar para verificar se um número é primo
fun ehPrimo(num: Int): Boolean {
    if (num < 2) return false
    var i = 2
    while (i * i <= num) {
        if (num % i == 0) return false
        i++
    }
    return true
}