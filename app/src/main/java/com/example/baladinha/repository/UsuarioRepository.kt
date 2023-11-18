package com.example.baladinha.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.baladinha.dados.EsquemaBD
import com.example.baladinha.dados.MeuBancoSQLite
import com.example.baladinha.modelo.Usuario

class UsuarioRepository(var cotexto: Context) {

    lateinit var banco: MeuBancoSQLite
    lateinit var conexao: SQLiteDatabase

    init {
        this.banco = MeuBancoSQLite(this.cotexto)
        this.conexao = this.banco.writableDatabase
    }

    fun salvarUsuario(usuario: Usuario):Boolean{
        val valores: ContentValues = ContentValues()

        valores.put(EsquemaBD.tabela_usuario.NOME_ATT, usuario.nome)
        valores.put(EsquemaBD.tabela_usuario.VALOR_ATT, usuario.valor)
        valores.put(EsquemaBD.tabela_usuario.ENTRADA_ATT, usuario.entrada)
        valores.put(EsquemaBD.tabela_usuario.SAIDA_ATT, usuario.saida)
        //VOLTAR: por conta do cast
        if(usuario.data != null) {
            valores.put(EsquemaBD.tabela_usuario.DATA_ATT, usuario.data!!.toEpochDay())
        }

        return this.conexao.insert(EsquemaBD.tabela_usuario.NOME_TABELA,
            null,valores) > 0
    }

    fun buscaUsuario(): List<Usuario> {
        val tuplasBanco: Cursor = this.conexao.query(
            EsquemaBD.tabela_usuario.NOME_TABELA,
            arrayOf(
                EsquemaBD.tabela_usuario.NOME_ATT,
                EsquemaBD.tabela_usuario.VALOR_ATT,
                EsquemaBD.tabela_usuario.DATA_ATT,
                EsquemaBD.tabela_usuario.ENTRADA_ATT,
                EsquemaBD.tabela_usuario.SAIDA_ATT
            ),
            null, // Condição WHERE nula para obter todos os registros
            null,
            null,
            null,
            null
        )

        val usuarios: MutableList<Usuario> = mutableListOf()

        while (tuplasBanco.moveToNext()) {
            val usuario = Usuario(
                tuplasBanco.getString(0),
                tuplasBanco.getDouble(1),
                tuplasBanco.getString(2),
                tuplasBanco.getInt(3) ==1,
                tuplasBanco.getInt(4) ==1
            )
            usuarios.add(usuario)
        }

        tuplasBanco.close()

        return usuarios
    }


}