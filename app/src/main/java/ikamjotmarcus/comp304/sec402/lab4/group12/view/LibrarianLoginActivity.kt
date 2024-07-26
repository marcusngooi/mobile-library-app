package ikamjotmarcus.comp304.sec402.lab4.group12.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ikamjotmarcus.comp304.sec402.lab4.group12.LibraryApplication
import ikamjotmarcus.comp304.sec402.lab4.group12.R
import ikamjotmarcus.comp304.sec402.lab4.group12.viewModel.LibrarianViewModel
import ikamjotmarcus.comp304.sec402.lab4.group12.viewModel.LibrarianViewModelFactory

class LibrarianLoginActivity : AppCompatActivity() {
    private lateinit var librarianId: EditText
    private lateinit var librarianPassword: EditText
    private lateinit var librarianLogin: Button

    private val librarianViewModel: LibrarianViewModel by viewModels {
        LibrarianViewModelFactory((application as LibraryApplication).librarianRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_librarian_login)

        librarianId = findViewById(R.id.et_librarian_id)
        librarianPassword = findViewById(R.id.et_librarian_password)

        librarianLogin = findViewById(R.id.btn_librarian_login)
        librarianLogin.setOnClickListener {
            Log.i(
                getString(R.string.log_tag_librarian_login),
                getString(R.string.log_msg_librarian_login)
            )
            val id = librarianId.text.toString().trim()
            val password = librarianPassword.text.toString().trim()
            librarianViewModel.signIn(id, password)
        }

        librarianViewModel.isSignedIn.observe(this) { isSignedIn ->
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
                    getString(R.string.toast_text_welcome_librarian_dashboard),
                    Toast.LENGTH_SHORT
                )
                    .show()
                val intent = Intent(this, LibrarianDashboardActivity::class.java)
                startActivity(intent)
            } else {
                Log.i(getString(R.string.log_tag_auth_fail), getString(R.string.log_msg_auth_fail))
                Toast.makeText(this, getString(R.string.toast_text_auth_fail), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}