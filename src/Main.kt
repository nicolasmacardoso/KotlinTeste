data class Requisito (
    val msgErr: String,
    val condicao: (String) -> Boolean
)

fun main() {
    val palavraObrigatoria = "nicolas"

    val listaDeRegras = listOf(
        Requisito("Mínimo de 5 caracteres") {
            it.length >= 5
        },
        Requisito("Deve conter pelo menos uma letra maiúscula") {
            it.any { char -> char.isUpperCase()}
        },
        Requisito("Deve conter pelo menos um número") {
            it.any { char -> char.isDigit()}
        },

        Requisito("Deve conter a palavra '$palavraObrigatoria' (sem diferenciar maiúsculas/minúsculas)") {
            it.lowercase().contains(palavraObrigatoria.lowercase())
        },

        Requisito("Deve conter o ano do hexa (2026)") {
            it.contains("2026")
        },

        Requisito("Deve conter pelo menos um emoji") {
            val emojiRegex = Regex("\\p{So}")
            emojiRegex.containsMatchIn(it)
        },

        Requisito("A soma dos números da senha deve resultar em 20") {
            val soma = it.filter { char -> char.isDigit() }
                .map { char -> char.digitToInt() }
                .sum()

            soma == 20
        },

        Requisito("Todas as vogais da senha devem ser maiúsculas") {
            val temVogalMinuscula = it.any { c -> c in "aeiou" }

            !temVogalMinuscula
        },

        Requisito("Todas as consoantes da senha devem ser minúsculas") {
            val temConsoanteMaiuscula = it.any { c -> c in "bcdfghjklmnpqrstvwxyz".uppercase()}

            !temConsoanteMaiuscula
        },

        Requisito("A senha deve ter 10 números") {
            it.filter{ char -> char.isDigit() }.length == 10
        },

        Requisito("A senhha deve ter o nome da matéria (sem acentos)") {
            it.lowercase().contains("solucoes mobile")
        }
    )

    var senhaAprovada = false

    do {
        println("\nDigite sua senha:")
        val entrada = readLine() ?: ""

        var erroEncontrado: String? = null

        for (regra in listaDeRegras) {
            if (!regra.condicao(entrada)) {
                erroEncontrado = regra.msgErr
                break
            }
        }

        if (erroEncontrado != null) {
            println("❌ ERRO: $erroEncontrado")
        } else {
            println("✅ SUCESSO! Senha aceita pelo Nicolas.")
            senhaAprovada = true
        }
    } while (!senhaAprovada)
}

/* nIcOlAs20265👍11111sOlUcOEs mObIlE */