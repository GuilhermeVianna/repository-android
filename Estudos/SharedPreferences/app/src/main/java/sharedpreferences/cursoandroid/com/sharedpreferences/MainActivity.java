package sharedpreferences.cursoandroid.com.sharedpreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button botaoSalvar;
    private EditText textoNome;
    private TextView resultadoTexto;
    private RadioGroup radioGroup;
    private RadioButton radioCorSelecionada;
    private RelativeLayout layout;

    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoNome = (EditText) findViewById(R.id.nomeTextoId);
        resultadoTexto = (TextView) findViewById(R.id.resultadoTextoId);
        botaoSalvar = (Button) findViewById(R.id.botaoSalvarId);
        radioGroup = (RadioGroup) findViewById(R.id.groupId);
        layout = (RelativeLayout) findViewById(R.id.layoutId);


        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                salvaCorSelecionada(editor);
                salvaNome(editor);
            }
        });

        recuperaDadosSalvos();
    }

    private void recuperaDadosSalvos() {
        //RECUPERAR DADOOS DO ARQUIVO
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
        if (sharedPreferences.contains("nome")) {
            String nomeUsuario = sharedPreferences.getString("nome", "Usuário não definido!");
            resultadoTexto.setText("Olá, " + nomeUsuario);
        } else {
            resultadoTexto.setText("Olá usuário não definido!");
        }
        if(sharedPreferences.contains("corSelecionada")){
            String cor = sharedPreferences.getString("corSelecionada","Branco");
            setBackGroud(cor);
        }
    }

    private void salvaCorSelecionada(SharedPreferences.Editor editor) {

        int idCorSelecionada = radioGroup.getCheckedRadioButtonId();
        radioCorSelecionada = (RadioButton) findViewById(idCorSelecionada);
        String corSelecionada =  radioCorSelecionada.getText().toString();
        editor.putString("corSelecionada",corSelecionada );
        editor.commit();
        setBackGroud(corSelecionada);

    }

    private void salvaNome(SharedPreferences.Editor editor){
        if (textoNome.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this, "Por Favor Preencher o Nome!", Toast.LENGTH_SHORT);
        } else {
            editor.putString("nome", textoNome.getText().toString());
            editor.commit();
            resultadoTexto.setText("Olá, " + textoNome.getText().toString());
        }
    }


    private void setBackGroud(String cor) {
        if(cor.equals("Azul")){
            layout.setBackgroundColor(Color.parseColor("#0008ff"));
        }else if(cor.equals("Amarelo")){
            layout.setBackgroundColor(Color.parseColor("#ffe100"));
        }else if(cor.equals("Rosa")){
            layout.setBackgroundColor(Color.parseColor("#ff00bb"));
        }else if(cor.equals("Verde")){
            layout.setBackgroundColor(Color.parseColor("#00ff00"));
        }else if(cor.equals("Vermelho")){
            layout.setBackgroundColor(Color.parseColor("#ff0000"));
        }
    }
}
