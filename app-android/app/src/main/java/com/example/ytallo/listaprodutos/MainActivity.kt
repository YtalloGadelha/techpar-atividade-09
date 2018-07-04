package com.example.ytallo.listaprodutos

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    lateinit var listView: ListView
    lateinit var queue: RequestQueue
    lateinit var url: String
    lateinit var jsonArrayRequest: JsonArrayRequest
    lateinit var jsonList: String
    lateinit var gson: Gson
    lateinit var produtoList: List<Produtos>
    lateinit var adapter: ArrayAdapter<Produtos>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //referêciando a listView a partir do identificador
        listView = findViewById(R.id.list_view)

        // Instanciando a RequestQueue.
        queue = Volley.newRequestQueue(this)

        //Criando a URL
        url = "http://192.168.122.1:3000/list"

        //Criando a requisição. Verbo GET
        jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
                Response.Listener { response ->

                    //Recebendo string por meio do response
                    jsonList = response.toString()

                    //Instanciando o gson para converter json em objeto
                    gson = GsonBuilder().setPrettyPrinting().create()

                    //Criando a lista de produtos
                    produtoList = gson.fromJson(jsonList, object : TypeToken<List<Produtos>>() {}.type)
                    println("=== List from JSON ===")
                    produtoList.forEach {println(it)}

                    //Criando o adapter necessário ao listView
                    adapter = ArrayAdapter<Produtos>(
                    this, // Context
                    android.R.layout.simple_list_item_1, // Layout
                    produtoList // List
                    )

                    //Adiconando o adapter ao listView
                    listView.adapter = adapter

                    // Configurando o click listener da ListView
                    listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

                        //Criando a Intent para fazer a navegação e passar os dados entre as atividades
                        var intent: Intent = Intent(this, DetalhesActivity::class.java)
                        intent.putExtra("produto", produtoList[position])

                        startActivity(intent)
                    }
                },

                Response.ErrorListener { error ->

                    println("Erro: ${error}")
                }
        )

        //Adicionando a requisição na RequestQueue.
        queue.add(jsonArrayRequest)
    }
}