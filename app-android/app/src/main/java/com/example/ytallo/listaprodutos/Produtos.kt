package com.example.ytallo.listaprodutos

data class Produtos(val idproduto: Int, val nomeproduto: String, val precoproduto: String, val descricaoproduto: String){

    override fun toString(): String {
        return " ID: ${idproduto} \n Nome: ${nomeproduto}; \n Preço: ${precoproduto} \n Descrição: ${descricaoproduto}"
    }
}