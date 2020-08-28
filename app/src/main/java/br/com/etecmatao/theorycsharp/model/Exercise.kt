package br.com.etecmatao.theorycsharp.model

import java.util.*

data class Exercise(
    var id: String = UUID.randomUUID().toString(),
    var question: String,
    var answerA: Answer,
    var answerB: Answer,
    var answerC: Answer,
    var answerD: Answer
) {
    fun possibleAnswers():Array<Answer>{
        return arrayOf(
            answerA,
            answerB,
            answerC,
            answerD
        )
    }

    fun validate(): Boolean {
        //pegamos uma lista com todas as respostas possiveis para a pergunta
        val answers = possibleAnswers()
        // contamos quantas dessas perguntas estão marcadas como correta
        val count = answers.count { it.correct }

        return  count == 1
    }
}