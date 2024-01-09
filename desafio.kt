// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

import java.util.UUID

open class Ideable {
    val id: String = UUID.randomUUID().toString()

    override fun equals(other: Any?): Boolean {
        return other is Ideable &&
            other::class.qualifiedName == this::class.qualifiedName &&
            other.id == this.id
    }
}

data class Usuario(
    var nome: String,
    val email: String,
    var ehPro: Boolean = false,
    val formacoes: MutableSet<Formacao> = mutableSetOf<Formacao>(),
) : Ideable() {
    fun matricular(formacao: Formacao): Boolean {
        return if(ehPro) {
            formacoes.add(formacao)
        } else {
            formacoes.clear()
            formacoes.add(formacao)
        }
    }

    override fun toString(): String = with(this) {
        "Usuario(id=$id, nome=$nome, email=$email, ehPro=$ehPro)"
    }
    override fun hashCode(): Int {
        return with(this) {
          31 * id.hashCode() + nome.hashCode() + email.hashCode() + ehPro.hashCode()
        }
    }
}

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

data class ConteudoEducacional(
    var nome: String,
    val duracao: Int = 60,
    val nivel: Nivel,
    val formacoes: MutableSet<Formacao> = mutableSetOf<Formacao>(),
) : Ideable() {
    val durationInHours: String
        get() {
            val result = Math.ceil(duracao/60.toDouble()).toInt()
            return "${result} hora${if(result > 1) "s" else ""}"
        }

    fun addFormacao(formacao: Formacao): Boolean = formacoes.add(formacao)

    override fun toString(): String = with(this) {
        "ConteudoEducacional(id=$id, nome=$nome, duracao=$duracao, nivel=$nivel)"
    }
    override fun hashCode(): Int {
        return with(this) {
            31 * id.hashCode() + nome.hashCode() + duracao.hashCode() + nivel.hashCode()
        }
    }
}

data class Formacao(
    val nome: String,
    val nivel: Nivel,
    var conteudos: MutableSet<ConteudoEducacional>,
) : Ideable() {
    val inscritos = mutableSetOf<Usuario>()

    fun matricular(usuario: Usuario): Boolean {
        inscritos.add(usuario)
        return usuario.matricular(this)
    }

    fun addConteudo(conteudo: ConteudoEducacional): Boolean {
        conteudos.add(conteudo)
        return conteudo.addFormacao(this)
    }

    fun listarInscritos() {
        inscritos.forEach(::println)
    }

    fun listarConteudos() {
        conteudos.forEach(::println)
    }

    override fun toString(): String = with(this) {
        "Formacao(id=$id, nome=$nome, nivel=$nivel)"
    }
    override fun hashCode(): Int {
        return with(this) {
          31 * id.hashCode() + nome.hashCode() + nivel.hashCode()
        }
    }
}

fun main() {
    val usuario: Usuario = Usuario("Matheus", "matheus@mail.com")
    with(usuario) {
        println(this)
        println(id)
        println(nome)
        println(email)
        println(ehPro)
    }

    println()

    val conteudoEducacional = ConteudoEducacional("Introdução", 59, Nivel.BASICO)
    with(conteudoEducacional) {
        println(this)
        println(id)
        println(nome)
        println(duracao)
        println(nivel)
        println(durationInHours)
    }

    println()

    val outroConteudoEducacional: ConteudoEducacional = ConteudoEducacional("Laços e Condicionais", 90, Nivel.INTERMEDIARIO)
    val formacao: Formacao = Formacao("Formação Kotlin Backend", Nivel.INTERMEDIARIO, mutableSetOf<ConteudoEducacional>())
    with(formacao) {
        println(this)
        println(id)
        println(nome)
        println(nivel)
        println("Listando conteúdos:")
        listarConteudos()
        addConteudo(conteudoEducacional)
        addConteudo(outroConteudoEducacional)
        println("Listando conteúdos:")
        listarConteudos()
        println("Listando inscritos:")
        listarInscritos()
        matricular(usuario)
        println("Listando inscritos:")
        listarInscritos()
    }

    println("Listando formações (usuário):")
    println(usuario.formacoes)

    println("Listando formações (conteúdo):")
    println(conteudoEducacional.formacoes)
}
