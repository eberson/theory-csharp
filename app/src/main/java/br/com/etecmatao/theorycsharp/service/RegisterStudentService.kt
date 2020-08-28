package br.com.etecmatao.theorycsharp.service

import android.util.Log
import br.com.etecmatao.theorycsharp.model.Student
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterStudentService {
    fun save(student: Student) {
        val database = FirebaseDatabase.getInstance().reference

        val students = database.child("students")

        val task = students.child(student.id).setValue(student)

        task.addOnSuccessListener {
            Log.d("STUDENT", "student ${student.id} saved successfully")

            val auth = FirebaseAuth.getInstance()
            val credential = student.credential

            auth.createUserWithEmailAndPassword(
                credential.email,
                credential.password
            )
        }.addOnFailureListener {
            Log.e("STUDENT", it.message, it)
        }
    }


}