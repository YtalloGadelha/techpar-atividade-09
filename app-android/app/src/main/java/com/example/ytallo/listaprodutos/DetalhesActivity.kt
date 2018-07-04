package com.example.ytallo.listaprodutos

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class DetalhesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        val botaoVoltar: Button = findViewById(R.id.button_voltar)

        botaoVoltar.setOnClickListener(View.OnClickListener {

            finish()
        })

    }
}
