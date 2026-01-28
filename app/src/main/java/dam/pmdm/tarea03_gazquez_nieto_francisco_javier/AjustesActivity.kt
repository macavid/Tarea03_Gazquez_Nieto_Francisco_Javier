package dam.pmdm.tarea03_gazquez_nieto_francisco_javier
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import dam.pmdm.tarea03_gazquez_nieto_francisco_javier.databinding.ActivityAjustesBinding
import dam.pmdm.tarea03_gazquez_nieto_francisco_javier.databinding.FragmentDetallesBinding

class AjustesActivity: AppCompatActivity (){
    private lateinit var binding: ActivityAjustesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    binding= ActivityAjustesBinding.inflate(layoutInflater)
    setContentView(binding.root)


   setSupportActionBar(binding.toolbarAjustes)
   binding.toolbarAjustes.setNavigationOnClickListener { finish() }


        binding.btnCerrarSession.setOnClickListener {

            FirebaseAuth.getInstance().signOut()

            AuthUI.getInstance().signOut(this).addOnCompleteListener {

                val prefs = getSharedPreferences("ajustes", MODE_PRIVATE)
                prefs.edit().clear().apply()
                finishAffinity()

            }
        }


    }


}


