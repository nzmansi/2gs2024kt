package nzmansi.github.com.enzomansi_rm92955

import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import nzmansi.github.com.enzomansi_rm92955.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var vinculo: ActivityMainBinding
    private lateinit var adaptador: DicaAdapter
    private lateinit var banco: BancoDados
    private lateinit var dicas: MutableList<Dica>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vinculo = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vinculo.root)

        banco = BancoDados(this)
        dicas = banco.pegarDicas()

        adaptador = DicaAdapter(dicas) { dica ->
            Toast.makeText(this, dica.descricao, Toast.LENGTH_SHORT).show()
        }

        vinculo.recyclerView.layoutManager = LinearLayoutManager(this)
        vinculo.recyclerView.adapter = adaptador

        vinculo.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(texto: String?): Boolean {
                texto?.let {
                    adaptador.atualizarDicas(dicas.filter { dica ->
                        dica.titulo.contains(texto, ignoreCase = true)
                    })
                }
                return true
            }

            override fun onQueryTextChange(texto: String?): Boolean {
                texto?.let {
                    adaptador.atualizarDicas(dicas.filter { dica ->
                        dica.titulo.contains(texto, ignoreCase = true)
                    })
                }
                return true
            }

        })

    }

}