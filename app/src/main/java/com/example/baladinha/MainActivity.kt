package com.example.baladinha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import com.example.baladinha.controle.UsuarioControleSQLite
import com.example.baladinha.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {

    lateinit var binding: ActivityMainBinding

    //lateinit var usuarioControle:UsuarioControleCache
    lateinit var usuarioControle: UsuarioControleSQLite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)

        usuarioControle = UsuarioControleSQLite(baseContext)

        registrarEventos()
    }

    private fun registrarEventos() {
        binding.salvarBtn.setOnClickListener(this)
        binding.resumoBtn.setOnClickListener(View.OnClickListener { finish() })
    }

    override fun onClick(botao: View) {
        //avalio o id do botao "pressionado"
        when (botao.id) {
            binding.salvarBtn.id -> gravarUsuarioCache()
            binding.resumoBtn.id -> resume()
        }
    }

    private fun resume() {
        val transicaoResume: Intent = Intent(baseContext, ResumoActivity::class.java)
        startActivity(transicaoResume)
    }

    private fun gravarUsuarioCache() {
        if (this.usuarioControle.salvarUsuario(
                binding.nomeTxt.text.toString(),
                binding.valorTxt.text.toString(),
                binding.dataTxt.text.toString(),
                binding.entrada,
                binding.saida
            )
        ) {
            //cadastro com sucesso e "voltamos" para o login...
            finish()
        } else {
            //algum erro no cadastro
            Toast.makeText(this, "algum não foi preenchido", Toast.LENGTH_LONG).show()
        }
    }
}