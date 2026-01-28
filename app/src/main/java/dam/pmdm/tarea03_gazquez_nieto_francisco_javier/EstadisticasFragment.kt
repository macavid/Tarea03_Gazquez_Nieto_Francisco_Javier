package dam.pmdm.tarea03_gazquez_nieto_francisco_javier

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dam.pmdm.tarea03_gazquez_nieto_francisco_javier.EpisodiosMock.porcentaje
import dam.pmdm.tarea03_gazquez_nieto_francisco_javier.databinding.FragmentEstadisticasBinding

class EstadisticasFragment : Fragment(R.layout.fragment_estadisticas){

    private lateinit var binding: FragmentEstadisticasBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEstadisticasBinding.bind(view)
        pintar()
    }
        override fun onResume(){

            super.onResume()
            pintar()
        }

        private fun pintar() {



            val total = EpisodiosMock.total()
            val vistos = EpisodiosMock.vistos()
            val porcentaje = EpisodiosMock.porcentaje()


            binding.tvContador.text = "Has visto $vistos de $total episodios"
            binding.tvPorcentaje.text = "$porcentaje% completado"
            binding.progreso.progress = porcentaje
            binding.progresoCircular.progress = porcentaje
        }

}