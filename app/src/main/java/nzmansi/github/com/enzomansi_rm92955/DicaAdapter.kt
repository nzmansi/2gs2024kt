package nzmansi.github.com.enzomansi_rm92955

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DicaAdapter(private var dicas: List<Dica>, private val cliqueDica: (Dica) -> Unit) :

    RecyclerView.Adapter<DicaAdapter.DicaViewHolder>() {

    override fun onCreateViewHolder(pai: ViewGroup, tipo: Int): DicaViewHolder {
        val view = LayoutInflater.from(pai.context).inflate(R.layout.item_dica, pai, false)
        return DicaViewHolder(view)
    }

    override fun onBindViewHolder(holder: DicaViewHolder, posicao: Int) {
        val dica = dicas[posicao]
        holder.titulo.text = dica.titulo
        holder.descricao.text = dica.descricao
        holder.itemView.setOnClickListener { cliqueDica(dica) }
    }

    override fun getItemCount(): Int = dicas.size

    fun atualizarDicas(novasDicas: List<Dica>) {
        dicas = novasDicas
        notifyDataSetChanged()
    }

    class DicaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo: TextView = view.findViewById(R.id.titulo)
        val descricao: TextView = view.findViewById(R.id.descricao)
    }

}