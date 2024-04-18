package com.example.megasena;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import java.util.Locale;

public class ExibirNumerosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exibir_numeros);

        // Obtém a referência para o TextView onde os números serão exibidos
        TextView textViewNumeros = findViewById(R.id.textViewNumeros);

        // Instancia um objeto NumerosDBHelper para interagir com o banco de dados
        NumerosDBHelper dbHelper = new NumerosDBHelper(this);

        // Recupera todos os números do banco de dados
        List<Numeros> numerosList = dbHelper.recuperarTodosNumeros();

        // Constrói uma string com os números recuperados
        StringBuilder numerosString = new StringBuilder();

        // Obtém a quantidade de sorteios realizados
        int quantidadeSorteios = numerosList.size();

        // Itera sobre os números recuperados e constrói a string
        for (int i = 0; i < quantidadeSorteios; i++) {
            int sorteioID = i + 1; // Adiciona 1 porque os IDs de sorteio geralmente começam em 1
            Numeros numeros = numerosList.get(i);

            // Formata a string com os números do sorteio
            numerosString.append("Sorteio ").append(String.format(Locale.getDefault(), "%3d", sorteioID)).append(": ")
                    .append(String.format(Locale.getDefault(), "%3d - %3d - %3d - %3d - %3d - %3d",
                            numeros.getN1(), numeros.getN2(), numeros.getN3(),
                            numeros.getN4(), numeros.getN5(), numeros.getN6()))
                    .append("\n\n");
        }

        // Define o texto do TextView com os números formatados
        textViewNumeros.setText(numerosString.toString());
    }
}