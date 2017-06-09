package radiobutton.cursoandroid.com.radiobutton;

import android.app.Activity;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

    private RadioGroup radioGroup;
    private RadioButton escolhido;
    private Button botaoEscolher;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup) findViewById(R.id.groupId);
        botaoEscolher = (Button) findViewById(R.id.botaoEscolherId);
        resultado = (TextView) findViewById(R.id.resultadoId);

        botaoEscolher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioGroup.getCheckedRadioButtonId() > 0){
                    escolhido = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                    resultado.setText(escolhido.getText());
                }
            }
        });


    }
}
