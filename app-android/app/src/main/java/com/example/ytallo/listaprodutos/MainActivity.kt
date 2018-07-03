package com.example.ytallo.listaprodutos

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //referêciando a listView a partir do id
        val listView: ListView = findViewById<ListView>(R.id.list_view)

        // Instanciando a RequestQueue.
        val queue = Volley.newRequestQueue(this)

        //Criando a URL
        val url = "http://192.168.122.1:3000/list"

        //Criando a requisição. Verbo GET
        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
                Response.Listener { response ->

                    //Recebendo string por meio do response
                    val jsonList = response.toString()

                    //Instanciando o gson para converter json em objeto
                    val gson = GsonBuilder().setPrettyPrinting().create()

                    //Criando a lista de produtos
                    var produtoList: List<Produtos> = gson.fromJson(jsonList, object : TypeToken<List<Produtos>>() {}.type)
                    println("=== List from JSON ===")
                    produtoList.forEach {println(it)}

                    //Criando o adapter necessário ao listView
                    val adapter = ArrayAdapter<Produtos>(
                    this, // Context
                    android.R.layout.simple_list_item_1, // Layout
                    produtoList // List
                    )

                    //Adiconando o adapter ao listView
                    listView.adapter = adapter

                },
                Response.ErrorListener { error ->

                    println("Erro!")
                }
        )

        //Adicionando a requisição na RequestQueue.
        queue.add(jsonArrayRequest)
    }
}