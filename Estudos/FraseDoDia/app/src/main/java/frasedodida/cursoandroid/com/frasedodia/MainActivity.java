package frasedodida.cursoandroid.com.frasedodia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button botaoNovaFrase;
    private TextView textoNovaFrase;
    private List<String> listaFrases;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoNovaFrase = (Button) findViewById(R.id.botaoNovaFraseId);
        textoNovaFrase = (TextView) findViewById(R.id.textoNovaFraseId);

        listaFrases = new ArrayList<String>();
        listaFrases.add("QUEM ACREDITA SEMPRE ALCANÇA!!");
        listaFrases.add("O MUNDO PODE ATÉ FAZER VOCÊ CHORAR, MAS DEUS TE QUER SORRINDO!!");
        listaFrases.add("O IMPOSSÍVEL É SÓ QUESTÃO DE OPINIÃO!!");
        listaFrases.add("UMA VEZ FLAMENGO, FLAMENGO ATÉ MORRER!!");
        listaFrases.add("JÚLIA, AMOR ETERNO!!");

        botaoNovaFrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random randomico = new Random();
                int numeroAleatorio = randomico.nextInt(listaFrases.size());
                textoNovaFrase.setText(listaFrases.get(numeroAleatorio));

            }
        });

    }
}
