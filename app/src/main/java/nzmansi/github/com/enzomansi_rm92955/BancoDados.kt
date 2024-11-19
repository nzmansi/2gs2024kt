package nzmansi.github.com.enzomansi_rm92955

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoDados(contexto: Context) : SQLiteOpenHelper(contexto, "EcoDicas.db", null, 2) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE Dicas (id INTEGER PRIMARY KEY, titulo TEXT, descricao TEXT)")
        db.execSQL("INSERT INTO Dicas (titulo, descricao) VALUES ('Desligue aparelhos que não estão em uso', 'Aparelhos eletrônicos consomem energia mesmo em modo de espera. Desconecte quando não for usar.')")
        db.execSQL("INSERT INTO Dicas (titulo, descricao) VALUES ('Use lâmpadas LED', 'Lâmpadas LED são mais eficientes e duram mais do que lâmpadas incandescentes ou fluorescentes.')")
        db.execSQL("INSERT INTO Dicas (titulo, descricao) VALUES ('Evite o desperdício de água', 'Feche a torneira enquanto escova os dentes ou lava a louça para economizar água.')")
        db.execSQL("INSERT INTO Dicas (titulo, descricao) VALUES ('Faça compostagem', 'Transforme resíduos orgânicos em adubo natural para reduzir o lixo e ajudar o meio ambiente.')")
        db.execSQL("INSERT INTO Dicas (titulo, descricao) VALUES ('Prefira transporte público ou bicicleta', 'Reduza a emissão de CO2 optando por transportes coletivos ou não motorizados.')")
        db.execSQL("INSERT INTO Dicas (titulo, descricao) VALUES ('Reduza o uso de plástico', 'Evite embalagens descartáveis e prefira produtos reutilizáveis.')")
    }

    override fun onUpgrade(db: SQLiteDatabase, antiga: Int, nova: Int) {
        db.execSQL("DROP TABLE IF EXISTS Dicas")
        onCreate(db)
    }

    fun pegarDicas(): MutableList<Dica> {
        val dicas = mutableListOf<Dica>()
        val cursor = readableDatabase.rawQuery("SELECT * FROM Dicas", null)

        while (cursor.moveToNext()) {
            dicas.add(
                Dica(
                    cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("titulo")),
                    cursor.getString(cursor.getColumnIndexOrThrow("descricao"))
                )
            )
        }

        cursor.close()
        return dicas
    }
}