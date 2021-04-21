package com.example.login_carb_counter.ui.dbo

import android.provider.BaseColumns

object DatabaseInfo {

    const val SQL_CREATE_TABLE_QUERY =
        "CREATE TABLE ${UsuariosTableInfo.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${UsuariosTableInfo.COLUMN_USUARIO_NOMBRE} TEXT," +
                "${UsuariosTableInfo.COLUMN_USUARIO_EMAIL} TEXT," +
                "${UsuariosTableInfo.COLUMN_USUARIO_FECHA_NACIMIENTO} DATE," +
                "${UsuariosTableInfo.COLUMN_USUARIO_TIPO_DIABETES} INT," +
                "${UsuariosTableInfo.COLUMN_USUARIO_PESO} DOUBLE," +
                "${UsuariosTableInfo.COLUMN_USUARIO_ALTURA} DOUBLE," +
                "${UsuariosTableInfo.COLUMN_USUARIO_GRUPO_SANGUINEO} TEXT," +
                "${UsuariosTableInfo.COLUMN_USUARIO_CEDULA} INT)"

    const val SQL_DELETE_TABLE_QUERY = "DROP TABLE IF EXISTS ${UsuariosTableInfo.TABLE_NAME}"


    object UsuariosTableInfo:BaseColumns {
        const val TABLE_NAME = "registroTable"
        const val COLUMN_USUARIO_NOMBRE = "usuarioNombre"
        const val COLUMN_USUARIO_EMAIL = "usuarioEmail"
        const val COLUMN_USUARIO_FECHA_NACIMIENTO = "usuarioFechaNacimiento"
        const val COLUMN_USUARIO_TIPO_DIABETES = "usuarioTipoDiabetes" // TODO: revisar esto ya que es de varias opciones
        const val COLUMN_USUARIO_PESO = "usuarioPeso"
        const val COLUMN_USUARIO_ALTURA = "usuarioAltura"
        const val COLUMN_USUARIO_GRUPO_SANGUINEO = "usuarioGrupoSanguineo" // TODO: revisar esto ya que es de varias opciones
        const val COLUMN_USUARIO_CEDULA = "usuarioCedula"

    }
}