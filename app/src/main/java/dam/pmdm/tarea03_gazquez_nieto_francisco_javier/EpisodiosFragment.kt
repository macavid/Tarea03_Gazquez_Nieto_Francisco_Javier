package dam.pmdm.tarea03_gazquez_nieto_francisco_javier
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.fragment.findNavController
import com.google.android.material.switchmaterial.SwitchMaterial
import dam.pmdm.tarea03_gazquez_nieto_francisco_javier.data.api.RetrofitClient
import dam.pmdm.tarea03_gazquez_nieto_francisco_javier.data.model.EpisodeDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class EpisodiosFragment : Fragment(R.layout.fragment_episodios) {




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val rv = view.findViewById<RecyclerView>(R.id.rvEpisodios)
        rv.layoutManager = LinearLayoutManager(requireContext())
        val swSoloVistos= view.findViewById<SwitchMaterial>(R.id.swSoloVistos)


      val lista= mutableListOf<Episodio>()

        val listaMostrada= mutableListOf<Episodio>()

      val adaptador = Adaptador(listaMostrada) { episodio ->
            val bundle = Bundle().apply {
                putInt("episodeId", episodio.id)
                putBoolean("visto", episodio.visto)
            }
            findNavController().navigate(R.id.detalles, bundle)
        }
        rv.adapter=adaptador
        fun aplicarFiltro(){
            listaMostrada.clear()
            if(swSoloVistos.isChecked){
                listaMostrada.addAll(lista.filter{it.visto})
            }
            else{
                listaMostrada.addAll(lista)
            }
            adaptador.notifyDataSetChanged()

        }


        swSoloVistos.setOnCheckedChangeListener {_,_-> aplicarFiltro() }

        parentFragmentManager.setFragmentResultListener(
            "resultadoVisto", viewLifecycleOwner
        ) { _, bundle ->
            val id = bundle.getInt("episodeId", -1)
            val visto = bundle.getBoolean("visto", false)

            lista.find { it.id == id }?.visto = visto
            aplicarFiltro()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val dtos = withContext(Dispatchers.IO) {
                    cargarTodosLosEpisodios()
                }

                lista.clear()
                lista.addAll(dtos.map { it.toEpisodio() })

                aplicarFiltro()

            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error cargando episodios: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }



    }

}
private suspend fun cargarTodosLosEpisodios(): List<EpisodeDto> {
    val todos = mutableListOf<EpisodeDto>()


    val first = RetrofitClient.api.getEpisodes(page = 1)
    todos.addAll(first.results)

    val totalPages = first.info.pages


    for (page in 2..totalPages) {
        val resp = RetrofitClient.api.getEpisodes(page = page)
        todos.addAll(resp.results)
    }

    return todos
}
