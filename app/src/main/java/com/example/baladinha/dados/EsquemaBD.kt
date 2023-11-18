package com.example.baladinha.dados

class EsquemaBD {

    companion object{
        val NOME_BD:String = "bd_baladinha"
        val VERSAO:Int = 1
    }

    object tabela_usuario{
        val NOME_TABELA = "usuario"
        val ID_ATT = "id"
        val NOME_ATT = "nome"
        val VALOR_ATT = "valor"
        val DATA_ATT = "data"
        val ENTRADA_ATT = "entrada"
        val SAIDA_ATT = "saida"
        val CRIA_TABELA = "CREATE TABLE IF NOT EXISTS $NOME_TABELA " +
                "($ID_ATT INTEGER primary key autoincrement, " +
                "$NOME_ATT text, $VALOR_ATT text, $DATA_ATT integer, $ENTRADA_ATT boolean, $SAIDA_ATT boolean)"
    }
}