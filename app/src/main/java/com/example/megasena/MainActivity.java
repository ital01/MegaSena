package com.example.megasena;

import android.os.Bundle;
import android.os.Handler;
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

    private NumerosDBHelper dbHelper;

    // Handler para lidar com os cliques do botão com um intervalo de tempo
    private final Handler handler = new Handler();
    private boolean isButtonClickable = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Ajusta o padding para a compatibilidade com os insets da janela
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new NumerosDBHelper(this);

        Button buttonDisplay = findViewById(R.id.button_display);
        buttonDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verifica se o botão está clicável
                if (isButtonClickable) {
                    // Desabilita o botão temporariamente
                    isButtonClickable = false;

                    // Chama o método para exibir e salvar os números após um delay
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            exibirNumeros();

                            // Habilita o botão novamente após o delay
                            isButtonClickable = true;
                        }
                    }, 500); // Milissegundos de delay para gerar novamente
                }
            }
        });
    }

    // Método para gerar, exibir e salvar números
    public void exibirNumeros() {
        TextView n1TextView = findViewById(R.id.n1);
        TextView n2TextView = findViewById(R.id.n2);
        TextView n3TextView = findViewById(R.id.n3);
        TextView n4TextView = findViewById(R.id.n4);
        TextView n5TextView = findViewById(R.id.n5);
        TextView n6TextView = findViewById(R.id.n6);

        Numeros numeros = new Numeros();
        numeros.gerarNumeros(
          n1TextView,
          n2TextView,
          n3TextView,
          n4TextView,
          n5TextView,
          n6TextView
        );

        // Salva os números no banco de dados
        dbHelper.inserirNumeros(numeros);
    }

    // Método para iniciar a atividade que exibe todos os números
    public void exibirNumerosLayout(View view) {
        Intent intent = new Intent(this, ExibirNumerosActivity.class);
        startActivity(intent);
    }
}
