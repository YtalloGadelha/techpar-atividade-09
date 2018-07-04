package com.example.ytallo.listaprodutos

import android.os.Parcel
import android.os.Parcelable

data class Produtos(val idproduto: Int, val nomeproduto: String, val precoproduto: String, val descricaoproduto: String): Parcelable{

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idproduto)
        parcel.writeString(nomeproduto)
        parcel.writeString(precoproduto)
        parcel.writeString(descricaoproduto)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Produtos> {
        override fun createFromParcel(parcel: Parcel): Produtos {
            return Produtos(parcel)
        }

        override fun newArray(size: Int): Array<Produtos?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return " ID: ${idproduto} \n Nome: ${nomeproduto}; \n Preço: ${precoproduto} \n Descrição: ${descricaoproduto}"
    }

}