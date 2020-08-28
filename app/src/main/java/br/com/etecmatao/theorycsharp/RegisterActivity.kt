package br.com.etecmatao.theorycsharp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import br.com.etecmatao.theorycsharp.model.Credential
import br.com.etecmatao.theorycsharp.model.Student
import br.com.etecmatao.theorycsharp.service.RegisterStudentService
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun save(v:View){
        val student = Student(
            firstname = txtName.text.toString(),
            surename = txtSurname.text.toString(),
            picture = "",
            credential = Credential(
                email = txtEmail.text.toString(),
                password = txtPassword.text.toString()
            ),
            email = txtEmail.text.toString(),
            phone = txtPhone.text.toString()
        )

        RegisterStudentService().save(student)
    }
}
