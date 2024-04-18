package com.example.megasena;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.graphics.Insets;
import androidx.activity.EdgeToEdge;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    // Variável para controlar a visibilidade dos números
    private boolean isNumerosVisible = false;

    // Referência para o banco de dados
    private NumerosDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Habilita o modo Edge-to-Edge para a atividade
        EdgeToEdge.enable(this);

        // Define o layout da atividade
        setContentView(R.layout.activity_main);

        // Aplica o comportamento Edge-to-Edge aos elementos da interface
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicialize o NumerosDBHelper
        dbHelper = new NumerosDBHelper(this);

        // Obtém uma referência para o botão de exibir números
        Button buttonDisplay = findViewById(R.id.button_display);

        // Configura um listener de clique para o botão de exibir números
        buttonDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chama o método para exibir e salvar os números quando o botão é clicado
                exibirNumeros();
            }
        });
    }

    // Método para exibir e salvar os números gerados
    public void exibirNumeros() {
        // Obtém as referências para as TextViews onde os números serão exibidos
        TextView n1TextView = findViewById(R.id.n1);
        TextView n2TextView = findViewById(R.id.n2);
        TextView n3TextView = findViewById(R.id.n3);
        TextView n4TextView = findViewById(R.id.n4);
        TextView n5TextView = findViewById(R.id.n5);
        TextView n6TextView = findViewById(R.id.n6);

        // Cria uma instância da classe Numeros
        Numeros numeros = new Numeros();

        // Gera e exibe os números nas TextViews
        numeros.gerarNumeros(n1TextView, n2TextView, n3TextView, n4TextView, n5TextView, n6TextView);

        // Salva os números gerados no banco de dados SQLite
        dbHelper.inserirNumeros(numeros);
    }

    // Método para exibir o layout de números
    public void exibirNumerosLayout(View view) {
        // Usa um Intent para abrir o layout que exibe os números
        Intent intent = new Intent(this, ExibirNumerosActivity.class);
        startActivity(intent);
    }

}