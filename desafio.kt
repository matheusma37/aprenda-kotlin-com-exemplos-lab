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
        "Usuario(id=$id, nome=$nome, email=$email, ehPro=$ehPro, formacoes=$formacoes)"
    }
    override fun hashCode(): Int {
        return with(this) {
          31 * id.hashCode() + nome.hashCode() + email.hashCode() + ehPro.hashCode()
        }
    }
}

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

data class ConteudoEducacional(var nome: String, val duracao: Int = 60)

data class Formacao(
    val nome: String,
    val nivel: Nivel,
    var conteudos: List<ConteudoEducacional>,
) : Ideable() {
    val inscritos = mutableSetOf<Usuario>()

    fun matricular(usuario: Usuario): Boolean {
        inscritos.add(usuario)
        return usuario.matricular(this)
    }

    fun listarInscritos() {
        inscritos.forEach(::println)
    }

    fun listarConteudos() {
        conteudos.forEach(::println)
    }

    override fun toString(): String = with(this) {
        "Formacao(id=$id, nome=$nome, nivel=$nivel, conteudos=$conteudos, inscritos=$inscritos)"
    }
    override fun hashCode(): Int {
        return with(this) {
          31 * id.hashCode() + nome.hashCode() + nivel.hashCode()
        }
    }
}

fun main() {
    TODO("Analise as classes modeladas para este domínio de aplicação e pense em formas de evoluí-las.")
    TODO("Simule alguns cenários de teste. Para isso, crie alguns objetos usando as classes em questão.")
}
