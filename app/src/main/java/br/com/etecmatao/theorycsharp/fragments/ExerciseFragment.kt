package br.com.etecmatao.theorycsharp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.etecmatao.theorycsharp.ExercisesNavigation
import br.com.etecmatao.theorycsharp.R
import br.com.etecmatao.theorycsharp.dialog.DialogAnswerFragment
import br.com.etecmatao.theorycsharp.model.Answer
import br.com.etecmatao.theorycsharp.model.Exercise


class ExerciseFragment(
    private val exercise: Exercise,
    private val navigator: ExercisesNavigation
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val view = inflater.inflate(R.layout.exercise_fragment, container, false)

        val txtTitle = view.findViewById<TextView>(R.id.title)
        val txtQuestion = view.findViewById<TextView>(R.id.question)
        val txtAnswerA = view.findViewById<TextView>(R.id.answerA)
        val txtAnswerB = view.findViewById<TextView>(R.id.answerB)
        val txtAnswerC = view.findViewById<TextView>(R.id.answerC)
        val txtAnswerD = view.findViewById<TextView>(R.id.answerD)

        val btnPrevious = view.findViewById<Button>(R.id.btnPrevious)
        val btnNext = view.findViewById<Button>(R.id.btnNext)

        btnPrevious.setOnClickListener { navigator.previous() }
        btnNext.setOnClickListener { navigator.next() }

        txtTitle.text = getString(R.string.txt_exercise_title, exercise.id)
        txtQuestion.text = exercise.question

        configureAnswer(txtAnswerA, R.string.txt_answer_a, exercise.answerA)
        configureAnswer(txtAnswerB, R.string.txt_answer_b, exercise.answerB)
        configureAnswer(txtAnswerC, R.string.txt_answer_c, exercise.answerC)
        configureAnswer(txtAnswerD, R.string.txt_answer_d, exercise.answerD)

        return view
    }

    private fun configureAnswer(textView: TextView, messageId: Int, answer: Answer){
        textView.text = getString(messageId, answer.text)
        textView.tag = answer.text
        textView.isClickable = true
        textView.isFocusable = true
        textView.setOnClickListener(AnswerClick())
    }

    private inner class AnswerClick : View.OnClickListener{
        override fun onClick(v: View?) {
            val textView = v as TextView

            val selectedAnswer = textView.tag

            val answer = exercise.possibleAnswers().first { it.text == selectedAnswer }

            val fm = activity!!.supportFragmentManager

            DialogAnswerFragment.newInstance(
                answer,
                getString(R.string.title_answer_dialog)
            ).show(fm, "fragment_answer")

//            val message = if (answer.correct){
//                getString(R.string.msg_answer_correct)
//            } else {
//                getString(R.string.msg_answer_wrong)
//            }
//
//            Toast.makeText(
//                activity,
//                message,
//                Toast.LENGTH_SHORT
//            ).show()
        }
    }
}