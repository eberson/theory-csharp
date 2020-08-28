package br.com.etecmatao.theorycsharp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        animation.setAnimation("c-animation.json")
    }

    fun signUp(v: View){
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun signIn(v: View){
        var fields = arrayOf(txtEmail, txtPassword)

        if (fields.map { isValid(it) }.filter { !it }.any()){
            return
        }

        val auth = FirebaseAuth.getInstance()

        auth.signInWithEmailAndPassword(
            txtEmail.text.toString(),
            txtPassword.text.toString()
        ).addOnSuccessListener {
            Toast.makeText(this, "Seja bem vindo!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }.addOnFailureListener {
            Toast.makeText(
                this,
                "Não foi possível realizar o login",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun isValid(input: TextInputEditText): Boolean {
        input.error = null

        if (TextUtils.isEmpty(input.text.toString())){
            input.error = getString(R.string.msg_required_field)
            return false
        }

        return true
    }
}
