package checkbox.cursoandroid.com.checkbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends Activity {

    private CheckBox checkCao;
    private CheckBox checkGato;
    private CheckBox checkPapagaio;
    private Button botaoMostrar;
    private TextView textoExibicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkCao = (CheckBox) findViewById(R.id.caoId);
        checkGato = (CheckBox) findViewById(R.id.gatoId);
        checkPapagaio = (CheckBox) findViewById(R.id.papagaioId);
        botaoMostrar = (Button) findViewById(R.id.botaoMostrarId);
        textoExibicao = (TextView) findViewById(R.id.textoId);

        botaoMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itensSelecionados = "";
                itensSelecionados += "Item: " + checkCao.getText() + "Status: " + checkCao.isChecked() + "\n";
                itensSelecionados += "Item: " + checkGato.getText() + "Status: " + checkGato.isChecked() + "\n" ;
                itensSelecionados += "Item: " + checkPapagaio.getText() + "Status: " + checkPapagaio.isChecked();

                textoExibicao.setText(itensSelecionados);
            }
        });
    }
}
