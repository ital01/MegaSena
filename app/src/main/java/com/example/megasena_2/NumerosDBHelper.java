package com.example.megasena_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;

public class NumerosDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "megasena.db";
    private static final int DATABASE_VERSION = 1;
    private Context context;

    public NumerosDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS numeros_megasena (n1 INTEGER, n2 INTEGER, n3 INTEGER, n4 INTEGER, n5 INTEGER, n6 INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Atualização do esquema de banco de dados, se necessário
    }

    public void inserirNumeros(Numeros numeros) {
        SQLiteDatabase db = getWritableDatabase();

        try {
            // Insere os números na tabela
            ContentValues values = new ContentValues();
            values.put("n1", numeros.getN1());
            values.put("n2", numeros.getN2());
            values.put("n3", numeros.getN3());
            values.put("n4", numeros.getN4());
            values.put("n5", numeros.getN5());
            values.put("n6", numeros.getN6());
            db.insert("numeros_megasena", null, values);

            // Mostra um feedback ao usuário
            Toast.makeText(context, "Números adicionados ao banco de dados", Toast.LENGTH_SHORT).show();
        } catch (SQLiteException e) {
            e.printStackTrace();
            Toast.makeText(context, "Erro ao adicionar números ao banco de dados: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            db.close();
        }
    }

    public List<Numeros> recuperarTodosNumeros() {
        List<Numeros> numerosList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        try {
            Cursor cursor = db.query("numeros_megasena", null, null, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Numeros numeros = new Numeros();
                    numeros.setN1(cursor.getInt(cursor.getColumnIndex("n1")));
                    numeros.setN2(cursor.getInt(cursor.getColumnIndex("n2")));
                    numeros.setN3(cursor.getInt(cursor.getColumnIndex("n3")));
                    numeros.setN4(cursor.getInt(cursor.getColumnIndex("n4")));
                    numeros.setN5(cursor.getInt(cursor.getColumnIndex("n5")));
                    numeros.setN6(cursor.getInt(cursor.getColumnIndex("n6")));
                    numerosList.add(numeros);
                } while (cursor.moveToNext());
                cursor.close();
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            Toast.makeText(context, "Erro ao recuperar números do banco de dados: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            db.close();
        }

        return numerosList;
    }
}