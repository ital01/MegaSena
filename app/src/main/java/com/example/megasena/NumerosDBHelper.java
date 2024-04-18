package com.example.megasena;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class NumerosDBHelper extends SQLiteOpenHelper {

    // Nome do banco de dados
    private static final String DATABASE_NAME = "numeros_db";
    // Versão do banco de dados
    private static final int DATABASE_VERSION = 1;
    // Nome da tabela
    private static final String TABLE_NUMEROS = "numeros";
    // Nomes das colunas
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_N1 = "n1";
    private static final String COLUMN_N2 = "n2";
    private static final String COLUMN_N3 = "n3";
    private static final String COLUMN_N4 = "n4";
    private static final String COLUMN_N5 = "n5";
    private static final String COLUMN_N6 = "n6";

    // Comando SQL para criar a tabela
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NUMEROS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY," +
                    COLUMN_N1 + " INTEGER," +
                    COLUMN_N2 + " INTEGER," +
                    COLUMN_N3 + " INTEGER," +
                    COLUMN_N4 + " INTEGER," +
                    COLUMN_N5 + " INTEGER," +
                    COLUMN_N6 + " INTEGER)";

    // Comando SQL para excluir a tabela
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NUMEROS;

    public NumerosDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Executa o comando SQL para criar a tabela
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Executa o comando SQL para excluir a tabela e cria novamente
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    // Método para inserir números no banco de dados
    public void inserirNumeros(Numeros numeros) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        // Insere os números nas colunas correspondentes
        values.put(COLUMN_N1, numeros.getN1());
        values.put(COLUMN_N2, numeros.getN2());
        values.put(COLUMN_N3, numeros.getN3());
        values.put(COLUMN_N4, numeros.getN4());
        values.put(COLUMN_N5, numeros.getN5());
        values.put(COLUMN_N6, numeros.getN6());
        // Insere os valores no banco de dados
        db.insert(TABLE_NUMEROS, null, values);
        db.close();
    }

    // Método para recuperar todos os números do banco de dados
    @SuppressLint("Range")
    public List<Numeros> recuperarTodosNumeros() {
        List<Numeros> numerosList = new ArrayList<>();
        // Query para selecionar todos os registros na tabela
        String selectQuery = "SELECT * FROM " + TABLE_NUMEROS;
        SQLiteDatabase db = this.getWritableDatabase();
        // Executa a query
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Itera sobre os resultados e adiciona à lista
        if (cursor.moveToFirst()) {
            do {
                Numeros numeros = new Numeros();
                numeros.setN1(cursor.getInt(cursor.getColumnIndex(COLUMN_N1)));
                numeros.setN2(cursor.getInt(cursor.getColumnIndex(COLUMN_N2)));
                numeros.setN3(cursor.getInt(cursor.getColumnIndex(COLUMN_N3)));
                numeros.setN4(cursor.getInt(cursor.getColumnIndex(COLUMN_N4)));
                numeros.setN5(cursor.getInt(cursor.getColumnIndex(COLUMN_N5)));
                numeros.setN6(cursor.getInt(cursor.getColumnIndex(COLUMN_N6)));
                numerosList.add(numeros);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return numerosList;
    }
}