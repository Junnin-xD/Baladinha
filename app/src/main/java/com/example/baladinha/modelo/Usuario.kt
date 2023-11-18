package com.example.baladinha.modelo

import java.sql.Blob
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Usuario(var nome:String, var valor:Double, var entrada: Boolean, var saida: Boolean) {
    private val formatadorData = DateTimeFormatter.ofPattern("dd/MM/uuuu")

    var data: LocalDate? = null

    //usuário cadastra e arma.
    constructor(nome: String,
                valor: Double,
                data:String,
                entrada:Boolean,
                saida:Boolean):this(nome, valor, entrada, saida){
        this.data = LocalDate.parse(data, formatadorData)
    }

    //recupero esse usuário do meu banco
    constructor(nome: String,
                valor: Double,
                data:Long,
                entrada: Boolean,
                saida: Boolean):this(nome, valor, entrada, saida){
        this.data = LocalDate.ofEpochDay(data)
    }

    fun getDataAsSTR():String{
        return formatadorData.format(this.data)
        //return " "
    }
}