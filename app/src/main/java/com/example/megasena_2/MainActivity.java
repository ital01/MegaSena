package com.example.megasena_2;

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

    private boolean isNumerosVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtém uma referência para o botão
        Button buttonDisplay = findViewById(R.id.button_display);

        // Configura um listener de clique para o botão
        buttonDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chama o método para exibir os números quando o botão é clicado
                exibirNumeros();
            }
        });
    }

    public void exibirNumeros() {
        // Obtém as referências para as TextViews
        TextView n1TextView = findViewById(R.id.n1);
        TextView n2TextView = findViewById(R.id.n2);
        TextView n3TextView = findViewById(R.id.n3);
        TextView n4TextView = findViewById(R.id.n4);
        TextView n5TextView = findViewById(R.id.n5);
        TextView n6TextView = findViewById(R.id.n6);

        // Cria uma instância da classe Numeros
        Numeros numeros = new Numeros();

        // Chama o método gerarNumeros() passando as TextViews como parâmetros
        numeros.gerarNumeros(n1TextView, n2TextView, n3TextView, n4TextView, n5TextView, n6TextView);

        // Adiciona os números ao banco de dados SQLite
        numeros.inserirNoBancoDeDados(getApplicationContext());
    }
    public void exibirNumerosLayout(View view) {
        // Use um Intent para abrir o novo layout
        Intent intent = new Intent(this, ExibirNumerosActivity.class);
        startActivity(intent);
    }

}