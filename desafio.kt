// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

import java.util.UUID

data class Usuario(
    var name: String,
    val email: String,
    var isPro: Boolean = false,
    val formacoes: MutableSet<Formacao> = mutableSetOf<Formacao>(),
) {
    val id: String = UUID.randomUUID().toString()

    fun matricular(formacao: Formacao): Boolean {
        return if(isPro) {
            formacoes.add(formacao)
        } else {
            formacoes.clear()
            formacoes.add(formacao)
        }
    }

    override fun equals(other: Any?): Boolean = other is Usuario && other.id == this.id
    override fun toString(): String = with(this) {
        "Usuario(id=$id, name=$name, email=$email, isPro=$isPro, formacoes=$formacoes)"
    }
    override fun hashCode(): Int {
        return with(this) {
          31 * id.hashCode() + name.hashCode() + email.hashCode() + isPro.hashCode()
        }
    }
}

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

data class ConteudoEducacional(var nome: String, val duracao: Int = 60)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()
    
    fun matricular(usuario: Usuario) {
        TODO("Utilize o parâmetro $usuario para simular uma matrícula (usar a lista de $inscritos).")
    }
}

fun main() {
    TODO("Analise as classes modeladas para este domínio de aplicação e pense em formas de evoluí-las.")
    TODO("Simule alguns cenários de teste. Para isso, crie alguns objetos usando as classes em questão.")
}
