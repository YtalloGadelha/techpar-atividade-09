package com.example.ytallo.listaprodutos

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.gson.GsonBuilder

class DetalhesActivity : AppCompatActivity() {

    lateinit var textId: TextView
    lateinit var editNome: EditText
    lateinit var editPreco: EditText
    lateinit var editDescricao: EditText
    lateinit var botaoVoltar: Button
    lateinit var botaoSalvar: Button
    lateinit var produto: Produtos
    lateinit var produtoSalvo: Produtos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        //Referenciando os componentes de acordo com os identificadores
        textId = findViewById(R.id.text_view_id)
        editNome = findViewById<EditText>(R.id.edit_text_nome)
        editPreco = findViewById<EditText>(R.id.edit_text_preco)
        editDescricao = findViewById<EditText>(R.id.edit_text_descricao)
        botaoVoltar = findViewById(R.id.button_voltar)
        botaoSalvar = findViewById(R.id.button_salvar)

        //Criando a Intent para capturar os dados enviados pela navegação
        var intent = getIntent()
        produto = intent.getParcelableExtra("produto")

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

            produtoSalvo = produto.copy()

            val gson = GsonBuilder().setPrettyPrinting().create()

            produtoSalvo.idproduto = textId.text.toString().toInt()
            produtoSalvo.nomeproduto = editNome.text.toString()
            produtoSalvo.precoproduto = editPreco.text.toString()
            produtoSalvo.descricaoproduto = editDescricao.text.toString()

            val jsonProduto: String = gson.toJson(produtoSalvo)

            println(produtoSalvo)

            println(jsonProduto)

        })
    }
}