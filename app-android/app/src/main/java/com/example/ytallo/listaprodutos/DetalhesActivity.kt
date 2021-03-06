package com.example.ytallo.listaprodutos

import android.app.VoiceInteractor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import org.json.JSONObject
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest

class DetalhesActivity : AppCompatActivity() {

    lateinit var textId: TextView
    lateinit var textID: TextView
    lateinit var editNome: EditText
    lateinit var editPreco: EditText
    lateinit var editDescricao: EditText
    lateinit var botaoVoltar: Button
    lateinit var botaoSalvar: Button
    lateinit var produto: Produtos
    lateinit var produtoSalvo: Produtos
    lateinit var textTitulo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        //Referenciando os componentes de acordo com os identificadores
        textId = findViewById(R.id.text_view_id)
        textID = findViewById(R.id.textViewID)
        editNome = findViewById(R.id.edit_text_nome)
        editPreco = findViewById(R.id.edit_text_preco)
        editDescricao = findViewById(R.id.edit_text_descricao)
        botaoVoltar = findViewById(R.id.button_voltar)
        botaoSalvar = findViewById(R.id.button_salvar)
        textTitulo = findViewById(R.id.text_titulo)

        //Criando a Intent para capturar os dados enviados pela navegação
        var intent = getIntent()
        produto = intent.getParcelableExtra("produto")

        //Tirando a visibilidade dos campos ID
        if (produto.idproduto == -1){

            textTitulo.setText("Novo Produto")
            //limpando os campos dos componentes
            textID.visibility = View.INVISIBLE
            textId.visibility = View.INVISIBLE
        }

        //Populando os componentes com os dados passados pela Intent
        textId.text = produto.idproduto.toString()
        editNome.setText(produto.nomeproduto)
        editPreco.setText(produto.precoproduto)
        editDescricao.setText(produto.descricaoproduto)

        //Configurando o botão voltar
        botaoVoltar.setOnClickListener(View.OnClickListener {

            //Finalizando a atividade
            finish()
        })

        //Configurando o botão salvar
        botaoSalvar.setOnClickListener(View.OnClickListener {

            produtoSalvo = Produtos(0,"","","")

            //Instanciando o gson
            val gson = GsonBuilder().setPrettyPrinting().create()

            //Populando o objeto com as alterações
            produtoSalvo.idproduto = textId.text.toString().toInt()
            produtoSalvo.nomeproduto = editNome.text.toString()
            produtoSalvo.precoproduto = "R$${editPreco.text.toString()}"
            produtoSalvo.descricaoproduto = editDescricao.text.toString()

            //Criando string no formato de json a partir do objeto produtoSalvo
            val stringPoduto: String = gson.toJson(produtoSalvo)

            //Criando json a partir de uma string
            val jsonObject = JSONObject(stringPoduto)

            // Instanciando a RequestQueue.
            var queue = Volley.newRequestQueue(this)

            //Criando a URL
            var url = "http://192.168.0.5:3000/save"

            //Criando a requisição. Verbo PUT || POST
            val httpProtocolo = if(produtoSalvo.idproduto == -1) Request.Method.POST else Request.Method.PUT
            val request = JsonObjectRequest( httpProtocolo, url, jsonObject,
                    Response.Listener { response ->

                        Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show()
                        finish()
                    },

                    Response.ErrorListener { error ->

                        Toast.makeText(this, "BD bugado", Toast.LENGTH_SHORT).show()
                        finish()
                    }
            )
            //Adicionando a requisição na RequestQueue.
            queue.add(request)
        })
    }
}