package dam.pmdm.tarea03_gazquez_nieto_francisco_javier

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import dam.pmdm.tarea03_gazquez_nieto_francisco_javier.databinding.FragmentDetallesBinding

class AcercaDeFragment: Fragment(R.layout.fragment_acerca_de) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvVersion= view.findViewById<TextView>(R.id.tvVersion)
        val btnCerrar= view.findViewById<MaterialButton>(R.id.btnCerrar)

        val versionName= try{requireContext()
            .packageManager
            .getPackageInfo(requireContext().packageName,0)
            .versionName}
        catch (e: Exception){"1.0.0"}

        tvVersion.text="Version: $versionName"

        btnCerrar.setOnClickListener {
            findNavController().navigateUp()
        }


    }


}