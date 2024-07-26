package ikamjotmarcus.comp304.sec402.lab4.group12.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import ikamjotmarcus.comp304.sec402.lab4.group12.LibraryApplication
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.ktx.auth
//import com.google.firebase.ktx.Firebase
import ikamjotmarcus.comp304.sec402.lab4.group12.R
import ikamjotmarcus.comp304.sec402.lab4.group12.viewModel.StudentViewModel
import ikamjotmarcus.comp304.sec402.lab4.group12.viewModel.StudentViewModelFactory

class StudentLoginActivity : AppCompatActivity() {

    private lateinit var etStudentID: EditText
    private lateinit var etStudentPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var studentId: String

    private lateinit var sharedPref: SharedPreferences

    private val studentViewModel: StudentViewModel by viewModels {
        StudentViewModelFactory((application as LibraryApplication).studentRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_login)

        sharedPref = getSharedPreferences(getString(R.string.pref_file_key), Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        etStudentID = findViewById(R.id.tv_profile_student_id)
        etStudentPassword = findViewById(R.id.et_student_password)
        btnLogin = findViewById(R.id.btn_login)

        btnLogin.setOnClickListener {
            studentId = etStudentID.text.toString().trim()
            val password = etStudentPassword.text.toString().trim()
            studentViewModel.signIn(studentId, password)

            editor.putString(getString(R.string.pref_student_id), studentId)
            editor.apply()
        }

        studentViewModel.isSignedIn.observe(this) { isSignedIn ->
            if (isSignedIn) {
                Log.i(
                    getString(R.string.log_tag_auth_success),
                    getString(R.string.log_msg_auth_success)
                )
                Toast.makeText(
                    this,
                    getString(R.string.toast_text_auth_success),
                    Toast.LENGTH_SHORT
                ).show()
                Toast.makeText(
                    this,
                    getString(R.string.toast_text_welcome_student_dashboard),
                    Toast.LENGTH_SHORT
                )
                    .show()



                val intent = Intent(this, StudentDashboardActivity::class.java)
                startActivity(intent)
            } else {
                Log.i(getString(R.string.log_tag_auth_fail), getString(R.string.log_msg_auth_fail))
                Toast.makeText(this, getString(R.string.toast_text_auth_fail), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
