package com.example.baladinha

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.example.baladinha.controle.UsuarioControleSQLite
import com.example.baladinha.modelo.Usuario
import com.example.baladinha.databinding.ActivityResumoBinding
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class ResumoActivity : Activity(){

    lateinit var binding:ActivityResumoBinding
    var valor:String? = null
    //lateinit var usrControle:UsuarioControleCache
    lateinit var usrControle: UsuarioControleSQLite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResumoBinding.inflate(layoutInflater)

        setContentView(binding.root)
        //setContentView(R.layout.activity_mensagem)
        this.usrControle = UsuarioControleSQLite(baseContext)

        carregaDadosAct()
    }

    private fun carregaDadosAct(){
        this.valor = intent.getStringExtra("valor")

        atualizaDadosMensag()
    }

    private fun atualizaDadosMensag(){
        val usr: List<Usuario>? = this.usrControle.recuperaDadosUsuario()
        var totEntrada:Double = 0.0
        var totGasto:Double = 0.0
        var totDisponivel:Double = 0.0

        if(usr == null){
            finish()
        }else{
            for (elemento in usr){
                if (elemento.entrada){
                    totEntrada = totEntrada + elemento.valor
                }else{
                    totGasto = totGasto + elemento.valor
                }
            }

            totDisponivel = totEntrada - totGasto
            binding.valorEntradaTxt.text = ("R$ ${totEntrada}")
            binding.valorGastoTxt.text = ("R$ ${totGasto}")
            binding.valorDisponivelTxt.text = ("R$ ${totDisponivel}")

        }
    }

}