package dam.pmdm.tarea03_gazquez_nieto_francisco_javier

import android.os.Bundle
import androidx.fragment.app.Fragment

import android.view.View

import dam.pmdm.tarea03_gazquez_nieto_francisco_javier.databinding.FragmentDetallesBinding

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import dam.pmdm.tarea03_gazquez_nieto_francisco_javier.data.api.RetrofitClient
import dam.pmdm.tarea03_gazquez_nieto_francisco_javier.data.model.CharacterDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class detalles : Fragment(R.layout.fragment_detalles) {
   private lateinit var binding: FragmentDetallesBinding
private var visto=false
private var episodeId: Int=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetallesBinding.bind(view)

        episodeId = arguments?.getInt("episodeId") ?: -1
        visto = arguments?.getBoolean("visto") ?: false

        binding.btnVisto.text = if (visto) "Marcado como visto" else "Marcar como visto"

        val personajes = mutableListOf<CharacterDto>()
        val adapterChars = CharactersAdapter(personajes)
        binding.rvPersonajes.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvPersonajes.adapter = adapterChars

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val episode = withContext(Dispatchers.IO) {
                    RetrofitClient.api.getEpisode(episodeId)
                }

                binding.tvEpisodeName.text = episode.name
                binding.tvEpisodeCode.text = "${episode.episode} • ${episode.air_date}"

                // IDs desde URLs
                val ids = episode.characters.mapNotNull { url ->
                    url.substringAfterLast("/").toIntOrNull()
                }.joinToString(",")

                val chars = withContext(Dispatchers.IO) {
                    RetrofitClient.api.getCharacters(ids)
                }

                personajes.clear()
                personajes.addAll(chars)
                adapterChars.notifyDataSetChanged()

            } catch (e: Exception) {
                // opcional: Toast
            }
        }

        binding.btnVisto.setOnClickListener {
            visto = !visto
            binding.btnVisto.text = if (visto) "Marcado como visto" else "Marcar como visto"

            parentFragmentManager.setFragmentResult(
                "resultadoVisto",
                Bundle().apply {
                    putInt("episodeId", episodeId)
                    putBoolean("visto", visto)
                }
            )
        }
    }
}
