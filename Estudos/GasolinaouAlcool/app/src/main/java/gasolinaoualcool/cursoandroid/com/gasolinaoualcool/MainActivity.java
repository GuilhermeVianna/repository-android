package gasolinaoualcool.cursoandroid.com.gasolinaoualcool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button botaoVerificar;
    private EditText valorGasolina;
    private EditText valorAlcool;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Localizando elementos da tela
        botaoVerificar = (Button) findViewById(R.id.botaoVerificarId);
        valorGasolina = (EditText) findViewById(R.id.precoGasolinaId);
        valorAlcool = (EditText) findViewById(R.id.precoAlcoolId);
        resultado = (TextView) findViewById(R.id.resultadoId);

        //Evento de click no botão
        botaoVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoPrecoGasolina = valorGasolina.getText().toString();
                String textoPrecoAlcool = valorAlcool.getText().toString();

                if(textoPrecoGasolina.isEmpty() || textoPrecoAlcool.isEmpty()){
                    resultado.setVisibility(View.VISIBLE);
                    resultado.setText("PREENCHA OS DOIS VALORES!!");
                }else{
                    Double valorAlcool = Double.parseDouble(textoPrecoAlcool);
                    Double valorGasolina = Double.parseDouble(textoPrecoGasolina);

                    Double resultadoFinal = valorAlcool / valorGasolina;
                    if(resultadoFinal >= 0.7){
                        resultado.setVisibility(View.VISIBLE);
                        resultado.setText("É MELHOR UTILIZAR GASOLINA!");
                    }else{
                        resultado.setVisibility(View.VISIBLE);
                        resultado.setText("É MELHOR UTILIZAR ÁLCOOL!");
                    }
                }





            }
        });
    }
}
