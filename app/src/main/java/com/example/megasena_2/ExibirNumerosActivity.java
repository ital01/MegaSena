package com.example.megasena_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class ExibirNumerosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exibir_numeros);

        // Obtém a referência para o TextView
        TextView textViewNumeros = findViewById(R.id.textViewNumeros);

        // Recupera os números do banco de dados
        NumerosDBHelper dbHelper = new NumerosDBHelper(this);
        List<Numeros> numerosList = dbHelper.recuperarTodosNumeros();

        // Constrói uma string com os números recuperados
        StringBuilder numerosString = new StringBuilder();

        // Obtém o tamanho da lista de números
        int quantidadeSorteios = numerosList.size();

        // Itera sobre os números e constrói a string
        for (int i = 0; i < quantidadeSorteios; i++) {
            int sorteioID = i + 1; // Adiciona 1 porque os IDs de sorteio geralmente começam em 1
            Numeros numeros = numerosList.get(i);

            numerosString.append("Sorteio ").append(sorteioID).append(": ")
                    .append(numeros.getN1()).append(", ")
                    .append(numeros.getN2()).append(", ")
                    .append(numeros.getN3()).append(", ")
                    .append(numeros.getN4()).append(", ")
                    .append(numeros.getN5()).append(", ")
                    .append(numeros.getN6()).append("\n");
        }

        // Define o texto do TextView com os números recuperados
        textViewNumeros.setText(numerosString.toString());
    }
}