package idadedoscachorros.cursoandroid.com.idadedoscachorros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button botaoDescobrirIdade;
    private EditText caixaTextoIdade;
    private TextView resultadoIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoDescobrirIdade = (Button) findViewById(R.id.botaoDescobrirId);
        caixaTextoIdade = (EditText) findViewById(R.id.caixaTextoIdadeId);
        resultadoIdade = (TextView) findViewById(R.id.resultadoIdadeId);

        botaoDescobrirIdade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Recuperar o que foi digitado
                String textoDigitado = caixaTextoIdade.getText().toString();
                if(textoDigitado.isEmpty()){
                    //String vazia tratamento de erro
                    resultadoIdade.setVisibility(View.VISIBLE);
                    resultadoIdade.setText("Nenhuma idade digitada!");
                }else{

                    int valorDigitado = Integer.parseInt(textoDigitado);
                    int valorMultiplicado = valorDigitado * 7;
                    resultadoIdade.setVisibility(View.VISIBLE);
                    resultadoIdade.setText(valorMultiplicado + " anos.");

                }
            }
        });
    }
}
