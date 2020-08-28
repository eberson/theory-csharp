package br.com.etecmatao.theorycsharp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.etecmatao.theorycsharp.model.Student
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(application: Application) : AndroidViewModel(application) {
    val currentStudent: MutableLiveData<Student> = MutableLiveData()

    fun loadCurrentStudent() = viewModelScope.launch(Dispatchers.IO) {
        val currentUser = FirebaseAuth.getInstance().currentUser

        currentUser?.let { user ->
            val database = FirebaseDatabase.getInstance()

            database.getReference("students")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        currentStudent.value = snapshot.children
                            .map { it.getValue(Student::class.java)!! }
                            .first { it.email == user.email }
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }
                })
        }
    }
}