package caraoucoroa.cursoandroid.com.caraoucoroa;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class JogoActivity extends Activity {

    private ImageView botaoVoltar;
    private ImageView imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        imagem = (ImageView) findViewById(R.id.moedaId);

        //PEGANDO OS VALORES PASSADOS DA ACTIVITY
        Bundle extras = getIntent().getExtras();
        String lado = extras.getString("moeda");
        if(lado.equals("coroa") ){
            imagem.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.moeda_coroa));
        }else{
            imagem.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.moeda_cara));
        }

        botaoVoltar = (ImageView) findViewById(R.id.botaoVoltarId);
        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JogoActivity.this, MainActivity.class));
                //MÉTODO QUE FINALIZA A ACTIVITY ATUAL
                //finish();
            }
        });
    }
}
