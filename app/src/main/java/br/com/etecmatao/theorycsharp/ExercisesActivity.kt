package br.com.etecmatao.theorycsharp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import br.com.etecmatao.theorycsharp.fragments.ExerciseFragment
import br.com.etecmatao.theorycsharp.model.Answer
import br.com.etecmatao.theorycsharp.model.Exercise
import br.com.etecmatao.theorycsharp.model.Student
import com.google.firebase.database.FirebaseDatabase

class ExercisesActivity : AppCompatActivity(), ExercisesNavigation {

    private val exercisesNumPages = 3

    val questions: MutableList<Exercise> = mutableListOf(
        Exercise(
            question = "Qual a saída para o código:\nint x = 0;\nx = x++;\nConsole.WriteLine(x);",
            answerA = Answer("0", true),
            answerB = Answer("1"),
            answerC = Answer("2"),
            answerD = Answer("3")
        ),
        Exercise(
            question = "Qual a saída para o código:\nint x = 0;\nx = ++x;\nConsole.WriteLine(x);",
            answerA = Answer("0"),
            answerB = Answer("1", true),
            answerC = Answer("2"),
            answerD = Answer("3")
        ),
        Exercise(
            question = "Qual a saída para o código:\nint x = 0;\nx += 2;\nConsole.WriteLine(x);",
            answerA = Answer("0"),
            answerB = Answer("1"),
            answerC = Answer("2", true),
            answerD = Answer("3")
        )
    )

    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: ExercisePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercises)

        adapter = ExercisePagerAdapter(this)

        viewPager = findViewById(R.id.exercises_pager)
        viewPager.adapter = adapter

//        val database = FirebaseDatabase.getInstance().reference
//
//        questions.forEach {
//            database.child("exercises")
//                .child(it.id)
//                .setValue(it)
//        }
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0){
            super.onBackPressed()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    override fun next() {
        if (viewPager.currentItem < exercisesNumPages - 1){
            viewPager.currentItem = viewPager.currentItem + 1
        }
    }

    override fun previous() {
        if (viewPager.currentItem > 0){
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    private inner class ExercisePagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa){
        override fun getItemCount(): Int = exercisesNumPages
        override fun createFragment(position: Int): Fragment = ExerciseFragment(
            questions[position],
            this@ExercisesActivity
        )
    }
}
