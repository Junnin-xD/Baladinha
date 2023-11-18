package com.example.baladinha.dados

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MeuBancoSQLite(var context: Context): SQLiteOpenHelper(context,
    EsquemaBD.NOME_BD,null,EsquemaBD.VERSAO) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(EsquemaBD.tabela_usuario.CRIA_TABELA)

    }

    //executa quando a versao passada no construtor é diferente da "instalada"
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}