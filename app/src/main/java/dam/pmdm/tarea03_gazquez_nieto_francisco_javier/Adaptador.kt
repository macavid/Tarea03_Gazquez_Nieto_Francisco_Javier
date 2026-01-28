package dam.pmdm.tarea03_gazquez_nieto_francisco_javier
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dam.pmdm.tarea03_gazquez_nieto_francisco_javier.databinding.ItemEpisodeBinding

class Adaptador (

    private val episodios: MutableList<Episodio>,
    private val onClick : (Episodio) -> Unit


): RecyclerView.Adapter<Adaptador.EpisodiosViewHolder> () {
    private var modoSeleccion: Boolean = false

    inner class EpisodiosViewHolder(
        val binding: ItemEpisodeBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodiosViewHolder {
        val binding = ItemEpisodeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EpisodiosViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: EpisodiosViewHolder,
        position: Int

    ) {
        val episodio = episodios[position]

        holder.binding.tvEpisodeName.text = episodio.nombre
        holder.binding.tvEpisodeCode.text = episodio.codigoEpisodio
        holder.binding.tvEpisodeDate.text = episodio.fechaEmision

        holder.binding.imgVisto.visibility =
            if (episodio.visto) View.VISIBLE else
                View.GONE
        holder.binding.root.strokeColor =
            if (episodio.seleccionado) Color.parseColor("#00FF9C")
            else Color.TRANSPARENT

        holder.itemView.setOnLongClickListener {
            modoSeleccion = true
            episodio.seleccionado = true
            notifyDataSetChanged()
            true
        }
        holder.itemView.setOnClickListener {
            if (modoSeleccion) {
                episodio.seleccionado = !episodio.seleccionado
                notifyItemChanged(position)

                if (episodios.none { it.seleccionado }) {
                    modoSeleccion = false
                    notifyDataSetChanged()
                }
            } else {
                onClick(episodio)
            }


        }

    }


    override fun getItemCount(): Int = episodios.size


    fun marcarSeleccionadosComoVistos() {
        episodios.filter { it.seleccionado }.forEach {
            it.visto = true
            it.seleccionado = false
        }
        modoSeleccion = false
        notifyDataSetChanged()
    }

}