package ikamjotmarcus.comp304.sec402.lab4.group12.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import ikamjotmarcus.comp304.sec402.lab4.group12.R

class MainActivity : AppCompatActivity() {
    // Librarian
    private lateinit var librarianLoginPage: Button

    // Student
    private lateinit var studentLoginPage: Button
    private lateinit var studentSignUpPage: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Librarian
        librarianLoginPage = findViewById(R.id.btn_librarian_login_page)

        librarianLoginPage.setOnClickListener {
            Log.i(
                getString(R.string.log_tag_librarian_login_page),
                getString(R.string.log_msg_librarian_login_page)
            )
            Toast.makeText(
                this,
                getString(R.string.toast_text_welcome_librarian_login_screen),
                Toast.LENGTH_SHORT
            ).show()
            val intent = Intent(this, LibrarianLoginActivity::class.java)
            startActivity(intent)
        }

        // Student
        studentLoginPage = findViewById(R.id.btn_student_login_page)
        studentLoginPage.setOnClickListener {
            Log.i(
                getString(R.string.log_tag_student_login_page),
                getString(R.string.log_msg_student_login_page)
            )
            Toast.makeText(
                this,
                getString(R.string.toast_text_welcome_student_login_screen),
                Toast.LENGTH_SHORT
            ).show()
            val intent = Intent(this, StudentLoginActivity::class.java)
            startActivity(intent)
        }

        studentSignUpPage = findViewById(R.id.btn_student_sign_up_page)
        studentSignUpPage.setOnClickListener {
            Log.i(
                getString(R.string.log_tag_student_sign_up_page),
                getString(R.string.log_msg_student_sign_up_page)
            )
            Toast.makeText(
                this,
                getString(R.string.toast_text_welcome_student_sign_up_screen),
                Toast.LENGTH_SHORT
            ).show()
            val intent = Intent(this, StudentSignUpActivity::class.java)
            startActivity(intent)
        }
    }
}