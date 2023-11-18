package com.example.baladinha.controle

import android.content.Context
import android.widget.CheckBox
import com.example.baladinha.modelo.Usuario
import com.example.baladinha.repository.UsuarioRepository

class UsuarioControleSQLite(var context:Context) {

    lateinit var repository: UsuarioRepository

    init {
        repository = UsuarioRepository(context)
    }

    //registrar um usuário
    fun salvarUsuario(nome:String, valor: String, data:String, entrada: CheckBox, saida: CheckBox) : Boolean{

        if(nome != null && valor != null && data != null){
            val usr: Usuario = Usuario(nome, valor, data, entrada, saida)
            return repository.salvarUsuario(usr)
        }

        return false
    }

    fun recuperaDadosUsuario(): List<Usuario>? {
            return repository.buscaUsuario()
    }
}