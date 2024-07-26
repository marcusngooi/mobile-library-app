package ikamjotmarcus.comp304.sec402.lab4.group12.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentSignUpActivity : AppCompatActivity() {

    private lateinit var etStudentID: EditText
    private lateinit var etStudentPassword: EditText
    private lateinit var etStudentFirstName: EditText
    private lateinit var etStudentLastName: EditText
    private lateinit var btnSignUp: Button
    private lateinit var tvMainPage: TextView

    private val studentViewModel: StudentViewModel by viewModels {
        StudentViewModelFactory((application as LibraryApplication).studentRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_sign_up)

        tvMainPage = findViewById(R.id.text_main_page)
        etStudentID = findViewById(R.id.tv_profile_student_id)
        etStudentPassword = findViewById(R.id.et_student_password)
        etStudentFirstName = findViewById(R.id.tv_profile_first_name)
        etStudentLastName = findViewById(R.id.tv_profile_last_name)

        btnSignUp = findViewById(R.id.btn_signup)



        btnSignUp.setOnClickListener {
            val studentId = etStudentID.text.toString()
            val firstName = etStudentFirstName.text.toString()
            CoroutineScope(Dispatchers.Main).launch {
                if (studentViewModel.isStudentExist(studentId)) {
                    Toast.makeText(this@StudentSignUpActivity, getString(R.string.toast_text_book_student_created_already), Toast.LENGTH_SHORT).show()
                }
                else {
                    studentViewModel.signUp(
                        studentId,
                        firstName,
                        etStudentLastName.text.toString(),
                        etStudentPassword.text.toString()
                    )
                    val intent = Intent(this@StudentSignUpActivity, StudentLoginActivity::class.java)
                    startActivity(intent)

                }
            }
        }

        tvMainPage.setOnClickListener {
            val intent = Intent(this@StudentSignUpActivity, StudentLoginActivity::class.java)
            startActivity(intent)
        }
    }
}