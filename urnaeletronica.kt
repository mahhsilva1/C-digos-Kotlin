import java.util.Scanner

data class Candidato(val numero: Int, val nome: String, var votos: Int = 0)

fun main() {
    val scanner = Scanner(System.`in`)

    // Configuração inicial dos candidatos
    val candidatos = listOf(
        Candidato(1, "Candidato A"),
        Candidato(2, "Candidato B"),
        Candidato(3, "Candidato C")
    )

    var votosBrancos = 0
    var votosNulos = 0
    var totalVotosRealizados = 0

    println("=== URNA ELETRÔNICA ===")
    print("Informe o número máximo de votos para esta eleição: ")
    val maxVotos = scanner.nextLine().toIntOrNull() ?: 0

    println("\nCandidatos disponíveis:")
    candidatos.forEach { println("${it.numero} -${it.nome}") }
    println("0 - Voto em Branco")
    println("Outros números - Voto Nulo")
    println("Digite 'FIM' a qualquer momento para encerrar a votação.\n")

    // Loop de votação
    while (totalVotosRealizados < maxVotos) {
        print("Voto nº ${totalVotosRealizados + 1}: ")
        val entrada = scanner.nextLine().trim()

        if (entrada.equals("FIM", ignoreCase = true)) {
            println("\nVotação encerrada manualmente.")
            break
        }

        val opcao = entrada.toIntOrNull()

        if (opcao == null) {
            println("Entrada inválida! Digite o número de um candidato, 0 para branco ou 'FIM' para sair.")
            continue
        }

        when (opcao) {
            0 -> {
                votosBrancos++
                println("Voto em BRANCO registrado.")
            }
            in 1..candidatos.size -> {
                val candidatoVotado = candidatos.first { it.numero == opcao }
                candidatoVotado.votos++
                println("Voto para ${candidatoVotado.nome} registrado.")
            }
            else -> {
                votosNulos++
                println("Voto NULO registrado.")
            }
        }

        totalVotosRealizados++
    }

    if (totalVotosRealizados == maxVotos && maxVotos > 0) {
        println("\nLimite máximo de $maxVotos votos alcançado!")
    }

    // Geração do Relatório
    println("\n==================================")
    println("      RELATÓRIO DA ELEIÇÃO       ")
    println("==================================")
    println("Total de votos apurados: $totalVotosRealizados\n")

    if (totalVotosRealizados > 0) {
        // Exibe resultados de cada candidato
        candidatos.forEach { c ->
            val pct = (c.votos.toDouble() / totalVotosRealizados) * 100
            println("${c.nome}:${c.votos} voto(s) (%.2f%%)".format(pct))
        }

        // Brancos e Nulos
        val pctBrancos = (votosBrancos.toDouble() / totalVotosRealizados) * 100
        val pctNulos = (votosNulos.toDouble() / totalVotosRealizados) * 100
        println("Votos em Branco: $votosBrancos (%.2f%%)".format(pctBrancos))
        println("Votos Nulos: $votosNulos (%.2f%%)".format(pctNulos))

        // Determinação do Vencedor (considera apenas votos válidos dos candidatos)
        val maiorVotacao = candidatos.maxOf { it.votos }
        val vencedores = candidatos.filter { it.votos == maiorVotacao }

        println("\n----------------------------------")
        if (maiorVotacao == 0) {
            println("Nenhum candidato recebeu votos.")
        } else if (vencedores.size == 1) {
            println("VENCEDOR: ${vencedores.first().nome} com${vencedores.first().votos} voto(s)!")
        } else {
            val nomesEmpatados = vencedores.joinToString { it.nome }
            println("EMPATE entre: $nomesEmpatados (ambos com$maiorVotacao votos).")
        }
    } else {
        println("Nenhum voto foi registrado nesta eleição.")
    }
    println("==================================")
}