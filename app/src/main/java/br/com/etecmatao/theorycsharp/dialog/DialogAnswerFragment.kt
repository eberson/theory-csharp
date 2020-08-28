package br.com.etecmatao.theorycsharp.dialog

import android.graphics.Point
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.DialogFragment
import br.com.etecmatao.theorycsharp.R
import br.com.etecmatao.theorycsharp.model.Answer
import com.airbnb.lottie.LottieAnimationView


class DialogAnswerFragment: DialogFragment() {

    private lateinit var animationView: LottieAnimationView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.answer_layout, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        animationView = view.findViewById(R.id.animationView)

        dialog?.setTitle(arguments?.getString("title"))

        setAnimationView()

        val btnOk = view.findViewById<Button>(R.id.btnOk)
        btnOk.setOnClickListener{ onOkClick() }
    }

    private fun onOkClick(){
        dismiss()
    }

    private fun setAnimationView(){
        val answer = arguments!!.getParcelable<Answer>("answer")!!

        val animation = if (answer.correct) {
            "congratulations.json"
        } else {
            "wrong.json"
        }

        animationView.pauseAnimation()
        animationView.setAnimation(animation)
        animationView.playAnimation()
    }

    override fun onResume() {
        super.onResume()

        val window: Window? = dialog!!.window
        val size = Point()

        val display = window?.windowManager?.defaultDisplay
        display?.getSize(size)

        val width: Int = size.x
        val height: Int = size.y

        window?.setLayout((width * 0.90).toInt(), (height * 0.75).toInt())
        window?.setGravity(Gravity.CENTER)
    }

    companion object{
        fun newInstance(answer: Answer, title: String):DialogFragment {
            val dialog = DialogAnswerFragment()

            val args = Bundle()
            args.putParcelable("answer", answer)
            args.putString("title", title)

            dialog.arguments = args
            dialog.isCancelable = false

            return dialog
        }
    }
}