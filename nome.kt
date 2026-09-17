fun main() {
    val nomes = listOf("Maria", "Eduarda", "Márcio", "Lucas")
    val sobrenomes = listOf("Ramos", "Silva", "Souza", "Santos")

    val nomeSorteado = nomes.random()
    val sobrenomeSorteado = sobrenomes.random()

    val nomeCompleto = "$nomeSorteado $sobrenomeSorteado"
    println("Nome gerado: $nomeCompleto")
}