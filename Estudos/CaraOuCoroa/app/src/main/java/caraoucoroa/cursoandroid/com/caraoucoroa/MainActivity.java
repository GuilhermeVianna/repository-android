package caraoucoroa.cursoandroid.com.caraoucoroa;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends Activity {

    private ImageView botaoJogar;
    private String[] opcao = {"cara","coroa"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoJogar = (ImageView) findViewById(R.id.botaoJogarId);
        botaoJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //SORTEANDO LADO
                Random randomico = new Random();
                int indiceMoeda = randomico.nextInt(opcao.length);

                //SETANDO VALOR SORTEADO PARA PROXIMA ACTIVITY
                Intent intent = new Intent(MainActivity.this, JogoActivity.class);
                intent.putExtra("moeda",opcao[indiceMoeda]);
                startActivity(intent);
            }
        });
    }
}
