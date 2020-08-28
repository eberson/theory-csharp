package br.com.etecmatao.theorycsharp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.etecmatao.theorycsharp.viewModel.StudentViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var vm: StudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        vm = ViewModelProvider(this).get(StudentViewModel::class.java)
        vm.currentStudent.observe(this, Observer { student ->
            student?.let { txtStudentName.text = it.completeName() }
        })

        vm.currentStudent.observe(this, Observer {
            it?.let {
                Log.i("MAIN", it.completeName())
            }
        })
    }

    override fun onResume() {
        super.onResume()

        if (auth.currentUser == null){
            goToLogin()
            return
        }

        vm.loadCurrentStudent()
    }

    private fun goToLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_menu_exit -> {
                auth.signOut()
                goToLogin()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}