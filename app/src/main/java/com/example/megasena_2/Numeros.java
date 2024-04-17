package com.example.megasena_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import android.widget.TextView;
import java.util.Random;

public class Numeros {

    private int n1, n2, n3, n4, n5, n6;

    public Numeros() {
        n1 = 0;
        n2 = 0;
        n3 = 0;
        n4 = 0;
        n5 = 0;
        n6 = 0;
    }

    public void gerarNumeros(TextView n1TextView, TextView n2TextView, TextView n3TextView, TextView n4TextView, TextView n5TextView, TextView n6TextView) {
        // Gerar e definir os números aleatórios
        Random random = new Random();
        n1 = random.nextInt(60) + 1;
        n2 = random.nextInt(60) + 1;
        n3 = random.nextInt(60) + 1;
        n4 = random.nextInt(60) + 1;
        n5 = random.nextInt(60) + 1;
        n6 = random.nextInt(60) + 1;

        // Atualiza as TextViews com os números gerados
        n1TextView.setText(String.valueOf(n1));
        n2TextView.setText(String.valueOf(n2));
        n3TextView.setText(String.valueOf(n3));
        n4TextView.setText(String.valueOf(n4));
        n5TextView.setText(String.valueOf(n5));
        n6TextView.setText(String.valueOf(n6));
    }

    public void inserirNoBancoDeDados(Context context) {
        // Abre ou cria o banco de dados
        SQLiteOpenHelper dbHelper = new SQLiteOpenHelper(context, "megasena.db", null, 1) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                // Cria a tabela se ela não existir
                db.execSQL("CREATE TABLE IF NOT EXISTS numeros_megasena (n1 INTEGER, n2 INTEGER, n3 INTEGER, n4 INTEGER, n5 INTEGER, n6 INTEGER)");
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                // Atualização do esquema de banco de dados, se necessário
            }
        };

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            // Insere os números na tabela
            ContentValues values = new ContentValues();
            values.put("n1", n1);
            values.put("n2", n2);
            values.put("n3", n3);
            values.put("n4", n4);
            values.put("n5", n5);
            values.put("n6", n6);
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

    public int getN1() {
        return n1;
    }

    public void setN1(int n1) {
        this.n1 = n1;
    }

    public int getN2() {
        return n2;
    }

    public void setN2(int n2) {
        this.n2 = n2;
    }

    public int getN3() {
        return n3;
    }

    public void setN3(int n3) {
        this.n3 = n3;
    }

    public int getN4() {
        return n4;
    }

    public void setN4(int n4) {
        this.n4 = n4;
    }

    public int getN5() {
        return n5;
    }

    public void setN5(int n5) {
        this.n5 = n5;
    }

    public int getN6() {
        return n6;
    }

    public void setN6(int n6) {
        this.n6 = n6;
    }
}