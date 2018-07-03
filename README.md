# techpar-atividade-09
Atividade 9, treinamento TechPar.

criar um serviço em node/express para gerir um catálogo de produtos
listar produtos
ver detalhes de produto
nome
preço
descrição
o knex migrations deve ser utilizado para
criar o banco de dados de produtos (esquema)
alimentar a base com produtos (carga)
criar um aplicativo android para consumir este serviço
uma tela para listar os produtos
uma tela para ver os detalhes do produto


//COLINHA

val textView = findViewById<TextView>(R.id.text)
        // ...

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        // verificar o IP da máquina
        val url = "http://192.168.25.3:3000/list"
        

        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    textView.text = response.toString()
                },
                Response.ErrorListener { error ->
                    textView.text = error.toString()

                }
        )
        
        // Add the request to the RequestQueue.
        queue.add(jsonArrayRequest)
