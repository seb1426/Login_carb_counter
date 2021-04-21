package com.example.login_carb_counter.ui.dbo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.login_carb_counter.ui.login.Registro
import com.example.login_carb_counter.ui.modelo.Usuario

class DatabaseOperations(context: Context): SQLiteOpenHelper(
        context,
    DATABASE_NAME, null,
    DATABASE_VERSION
) {

    companion object{
        const val DATABASE_NAME = "CarbCounter.db"
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
         db.execSQL(DatabaseInfo.SQL_CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DatabaseInfo.SQL_DELETE_TABLE_QUERY)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    // MÉTODOS DE ACCESO PARA EL USUARIO
    fun addUsuario(dbo: DatabaseOperations, usuario: Usuario)
    {
        val db = dbo.writableDatabase
        val nombre = usuario.nombre
        val email = usuario.email
        val fechaNacimiento = usuario.getDateAsString()
        val tipoDiabetes = usuario.tipoDiabetes
        val peso = usuario.peso
        val altura = usuario.altura
        val grupoSanguineo = usuario.grupoSanguineo
        val cedula = usuario.cedula

        val contentValues = ContentValues().apply {
            put(DatabaseInfo.UsuariosTableInfo.COLUMN_USUARIO_NOMBRE, nombre)
            put(DatabaseInfo.UsuariosTableInfo.COLUMN_USUARIO_EMAIL, email)
            put(DatabaseInfo.UsuariosTableInfo.COLUMN_USUARIO_FECHA_NACIMIENTO, fechaNacimiento)
            put(DatabaseInfo.UsuariosTableInfo.COLUMN_USUARIO_TIPO_DIABETES, tipoDiabetes)
            put(DatabaseInfo.UsuariosTableInfo.COLUMN_USUARIO_PESO, peso)
            put(DatabaseInfo.UsuariosTableInfo.COLUMN_USUARIO_ALTURA, altura)
            put(DatabaseInfo.UsuariosTableInfo.COLUMN_USUARIO_GRUPO_SANGUINEO, grupoSanguineo)
            put(DatabaseInfo.UsuariosTableInfo.COLUMN_USUARIO_CEDULA, cedula)

        }

       val rowID =  db.insert(DatabaseInfo.UsuariosTableInfo.TABLE_NAME, null, contentValues)


    }

    fun getAllUsuarios(dbo: DatabaseOperations):Cursor {

        val db = dbo.writableDatabase
        val projection = arrayOf(
            BaseColumns._ID,
            DatabaseInfo.UsuariosTableInfo.COLUMN_USUARIO_NOMBRE,
            DatabaseInfo.UsuariosTableInfo.COLUMN_USUARIO_EMAIL,
            DatabaseInfo.UsuariosTableInfo.COLUMN_USUARIO_FECHA_NACIMIENTO,
            DatabaseInfo.UsuariosTableInfo.COLUMN_USUARIO_TIPO_DIABETES,
            DatabaseInfo.UsuariosTableInfo.COLUMN_USUARIO_PESO,
            DatabaseInfo.UsuariosTableInfo.COLUMN_USUARIO_ALTURA,
            DatabaseInfo.UsuariosTableInfo.COLUMN_USUARIO_GRUPO_SANGUINEO,
            DatabaseInfo.UsuariosTableInfo.COLUMN_USUARIO_CEDULA)

        val selection = ""
        val selectionArgs = null
        val sortOrder = null

       val cursor = db.query(
            DatabaseInfo.UsuariosTableInfo.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
                null,
                    null,
            sortOrder
        )

        return cursor
    }

    fun updateUsuario(dbo:DatabaseOperations, usuarioViejo: Usuario, usuarioNuevo: Usuario)
    {
        val db = dbo.writableDatabase
        val nombre = usuarioNuevo.nombre
        val email = usuarioNuevo.email
        val fechaNacimiento = usuarioNuevo.getDateAsString()
        val tipoDiabetes = usuarioNuevo.tipoDiabetes
        val peso = usuarioNuevo.peso
        val altura = usuarioNuevo.altura
        val grupoSanguineo = usuarioNuevo.grupoSanguineo
        val cedula = usuarioNuevo.cedula

        val contentValues = ContentValues().apply {
            put(DatabaseInfo.UsuariosTableInfo.COLUMN_USUARIO_NOMBRE, nombre)
            put(DatabaseInfo.UsuariosTableInfo.COLUMN_USUARIO_EMAIL, email)
            put(DatabaseInfo.UsuariosTableInfo.COLUMN_USUARIO_FECHA_NACIMIENTO, fechaNacimiento)
            put(DatabaseInfo.UsuariosTableInfo.COLUMN_USUARIO_TIPO_DIABETES, tipoDiabetes)
            put(DatabaseInfo.UsuariosTableInfo.COLUMN_USUARIO_PESO, peso)
            put(DatabaseInfo.UsuariosTableInfo.COLUMN_USUARIO_ALTURA, altura)
            put(DatabaseInfo.UsuariosTableInfo.COLUMN_USUARIO_GRUPO_SANGUINEO, grupoSanguineo)
            put(DatabaseInfo.UsuariosTableInfo.COLUMN_USUARIO_CEDULA, cedula)

        }

        val selection = "${DatabaseInfo.UsuariosTableInfo.COLUMN_USUARIO_NOMBRE} LIKE ?"
        val selectionArgs = arrayOf(usuarioViejo.nombre)

        db.update(DatabaseInfo.UsuariosTableInfo.TABLE_NAME, contentValues, selection, selectionArgs)
    }

}