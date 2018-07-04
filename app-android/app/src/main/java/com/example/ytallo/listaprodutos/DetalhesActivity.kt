package com.example.ytallo.listaprodutos

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class DetalhesActivity : AppCompatActivity() {

    lateinit var textId: TextView
    lateinit var editNome: EditText
    lateinit var editPreco: EditText
    lateinit var editDescricao: EditText
    lateinit var botaoVoltar: Button
    lateinit var produtos: Produtos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        //Referenciando os componentes de acordo com os identificadores
        textId = findViewById(R.id.text_view_id)
        editNome = findViewById(R.id.edit_text_nome)
        editPreco = findViewById(R.id.edit_text_preco)
        editDescricao= findViewById(R.id.edit_text_descricao)
        botaoVoltar = findViewById(R.id.button_voltar)

        //Criando a Intent para capturar os dados enviados pela navegação
        var intent = getIntent()
        produtos = intent.getParcelableExtra("produto")

        //Populando os componentes com os dados passados pela Intent
        textId.text = produtos.idproduto.toString()
        editNome.setText(produtos.nomeproduto)
        editPreco.setText(produtos.precoproduto)
        editDescricao.setText(produtos.descricaoproduto)

        //Finalizando a atividade ao clicar no botão voltar
        botaoVoltar.setOnClickListener(View.OnClickListener {

            finish()
        })
    }
}