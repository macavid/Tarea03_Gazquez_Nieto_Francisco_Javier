package dam.pmdm.tarea03_gazquez_nieto_francisco_javier

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import dam.pmdm.tarea03_gazquez_nieto_francisco_javier.data.model.CharacterDto

class CharactersAdapter(private val data: MutableList<CharacterDto>) :
    RecyclerView.Adapter<CharactersAdapter.VH>() {

    inner class VH(v: View) : RecyclerView.ViewHolder(v) {
        val img = v.findViewById<ImageView>(R.id.imgChar)
        val name = v.findViewById<TextView>(R.id.tvCharName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val c = data[position]
        holder.name.text = c.name
        holder.img.load(c.image)
    }

    override fun getItemCount() = data.size
}
