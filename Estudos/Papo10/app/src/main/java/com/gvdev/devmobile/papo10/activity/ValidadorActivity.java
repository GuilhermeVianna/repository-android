package com.gvdev.devmobile.papo10.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.gvdev.devmobile.papo10.R;
import com.gvdev.devmobile.papo10.helper.Preferencias;

import java.util.HashMap;

public class ValidadorActivity extends Activity {

    private EditText codigoValidador;
    private Button botaoValidar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validador);

        codigoValidador = (EditText) findViewById(R.id.codigoValidadorId);
        botaoValidar = (Button) findViewById(R.id.botaoValidar);

        SimpleMaskFormatter fmt = new SimpleMaskFormatter("NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(codigoValidador,fmt);
        codigoValidador.addTextChangedListener(mtw);

        botaoValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preferencias preferencias = new Preferencias(getApplicationContext());
                HashMap<String, String> usuario = preferencias.getDadosUsuarios();

                String tokenGerado = usuario.get("token");
                String tokenDigitado = codigoValidador.getText().toString();

                if(tokenDigitado.equals(tokenGerado)){
                    Toast.makeText(ValidadorActivity.this,"Código Validado!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ValidadorActivity.this,"Código Inválido!", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
