package com.martific.crucesapp.dbManager

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.martific.crucesapp.data.Cruce

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "CrucesDB.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Define la estructura de tus tablas aquí
        // Por ejemplo, crea tablas utilizando db.execSQL("CREATE TABLE ...")
        db.execSQL("CREATE TABLE Cruces (numCruce INTEGER PRIMARY KEY, direccion TEXT, tipoRegulador TEXT, central INTEGER, nodo INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Maneja las actualizaciones de la base de datos aquí
        // Por ejemplo, puedes eliminar y recrear tablas si es necesario
    }

    fun readData(): MutableList<Cruce> {
        val list: MutableList<Cruce> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from Cruces"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            val columnIndexNumCruce = result.getColumnIndex("numCruce")
            val columnIndexDireccion = result.getColumnIndex("direccion")
            val columnIndexTipoRegulador = result.getColumnIndex("tipoRegulador")
            val columnIndexCentral = result.getColumnIndex("central")
            val columnIndexNodo = result.getColumnIndex("nodo")

            do {
                var cruce = Cruce(0, "", "", 0, 0)
                cruce.numCruce = result.getString(columnIndexNumCruce).toLong()
                cruce.direccion = result.getString(columnIndexDireccion)
                cruce.tipoRegulador = result.getString(columnIndexTipoRegulador)
                cruce.central = result.getString(columnIndexCentral).toInt()
                cruce.nodo = result.getString(columnIndexNodo).toInt()
                list.add(cruce)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }

}
