package dam.pmdm.tarea03_gazquez_nieto_francisco_javier

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import dam.pmdm.tarea03_gazquez_nieto_francisco_javier.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val auth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarRegister)
        binding.toolbarRegister.setNavigationOnClickListener { finish() }

        binding.btnCrearCuenta.setOnClickListener {
            registrar()
        }
    }

    private fun registrar() {
        val email = binding.etEmail.text?.toString()?.trim().orEmpty()
        val pass1 = binding.etPassword.text?.toString().orEmpty()
        val pass2 = binding.etPassword2.text?.toString().orEmpty()

        if (email.isBlank() || pass1.isBlank() || pass2.isBlank()) {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass1.length < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass1 != pass2) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return
        }

        binding.progress.visibility = View.VISIBLE
        binding.btnCrearCuenta.isEnabled = false

        auth.createUserWithEmailAndPassword(email, pass1)
            .addOnSuccessListener {

                startActivity(Intent(this, MainActivity::class.java))
                finishAffinity()
            }
            .addOnFailureListener { e ->
                binding.progress.visibility = View.GONE
                binding.btnCrearCuenta.isEnabled = true
                Toast.makeText(this, e.message ?: "Error al registrar", Toast.LENGTH_LONG).show()
            }
    }
}
