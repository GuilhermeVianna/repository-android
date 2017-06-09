package com.gvdev.devmobile.papo10.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.gvdev.devmobile.papo10.R;
import com.gvdev.devmobile.papo10.helper.Permissao;
import com.gvdev.devmobile.papo10.helper.Preferencias;

import java.util.Random;

public class LoginActivity extends Activity {

    //smsmanager https://developer.android.com/reference/android/telephony/SmsManager.html?hl=pt-br
    //masformatter https://github.com/rtoshiro/MaskFormatter
    //grupo de permissoes https://developer.android.com/guide/topics/security/permissions.html?hl=pt-br

    private static final String MENSAGEM_VALIDACAO = "Papo 10 Código de validaçao : ";

    private EditText numeroTelefone;
    private EditText numeroDdd;
    private EditText numeroArea;
    private EditText nomeUsuario;
    private Button botaoCadastrar;

    private String[] permissoesNecessarias = new String[] {
            Manifest.permission.SEND_SMS
    };

    private SimpleMaskFormatter fmt;
    private MaskTextWatcher mtw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Permissao.validaPermissoes(1,this,permissoesNecessarias);

        atribuiVariaveis();
        aplicaMascara();

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validaCamposPreenchidos()){
                    String telefoneCompleto = numeroArea.getText().toString() + numeroDdd.getText().toString() + numeroTelefone.getText().toString();
                    String token = geraTokenValidador();
                    salvaDadosUsuarios(token, formataNumeroTelefone(telefoneCompleto));
                    boolean enviadoSMS = enviaSMS("+" + formataNumeroTelefone(telefoneCompleto), MENSAGEM_VALIDACAO + token);

                    if(enviadoSMS){

                        Intent intent = new Intent(LoginActivity.this, ValidadorActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this,"Erro ao enviar SMS, tente novamente!", Toast.LENGTH_SHORT).show();
                    }


                }else{
                    Toast.makeText(getApplicationContext(),"Preencha todos os campos.", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }

    private void salvaDadosUsuarios(String token, String telefoneCompleto) {
        Preferencias preferencias = new Preferencias(getApplicationContext());
        preferencias.salvarUsuariosPreferencias(nomeUsuario.getText().toString(), telefoneCompleto, token);
    }

    private void atribuiVariaveis() {
        numeroTelefone = (EditText) findViewById(R.id.textoTelefoneId);
        numeroArea = (EditText) findViewById(R.id.textoNumeroAreaId);
        numeroDdd = (EditText) findViewById(R.id.textoDddId);
        nomeUsuario = (EditText) findViewById(R.id.TextoNomeId);
        botaoCadastrar = (Button) findViewById(R.id.botaoCadastrarId);
    }

    public void aplicaMascara(){
        aplicaMascaraTelefone(numeroTelefone, "N-NNNN-NNNN");
        aplicaMascaraDdd(numeroDdd,"NN");
        aplicaMascaraArea(numeroArea,"+NN");
    }

    private void aplicaMascaraArea(EditText numeroArea, String mask) {
        fmt = new SimpleMaskFormatter(mask);
        mtw = new MaskTextWatcher(numeroArea,fmt);
        numeroArea.addTextChangedListener(mtw);
    }

    private void aplicaMascaraDdd(EditText numeroDdd, String mask) {
        fmt = new SimpleMaskFormatter(mask);
        mtw = new MaskTextWatcher(numeroDdd,fmt);
        numeroDdd.addTextChangedListener(mtw);
    }

    private void aplicaMascaraTelefone(EditText numeroTelefone, String mask) {
        fmt = new SimpleMaskFormatter(mask);
        mtw = new MaskTextWatcher(numeroTelefone,fmt);
        numeroTelefone.addTextChangedListener(mtw);

    }

    private boolean validaCamposPreenchidos() {
        if(nomeUsuario.getText().toString().equals("")){
            return false;
        }else if(numeroTelefone.getText().toString().equals("")){
            return false;
        }else if(numeroDdd.getText().toString().equals("")){
            return false;
        }else if(numeroArea.getText().toString().equals("")){
            return false;
        }else{
            return true;
        }

    }

    private String geraTokenValidador() {
        Random random = new Random();
        int numeroRandomico = random.nextInt(9999 - 1000) + 1000;
        return String.valueOf(numeroRandomico);

    }

    private String formataNumeroTelefone(String telefoneCompleto) {
        String telefoneFormatado = telefoneCompleto.replace("+","");
        return  telefoneFormatado.replace("-","");

    }

    private boolean enviaSMS(String telefoneCompleto, String mensagem) {
        try{
            SmsManager smsManager = SmsManager.getDefault(); //intancia da classe sms manager
            smsManager.sendTextMessage(telefoneCompleto,null,mensagem,null,null);

            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;

        }

    }

    //calback que verifica permissoes negadas

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for(int resultado : grantResults){
            if(resultado == PackageManager.PERMISSION_DENIED){
                alertaValidacaoPermissao();
            }
        }
    }


    private void alertaValidacaoPermissao() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissoes negadas.");
        builder.setMessage("Para utilizar esse app, é necessário aceitar as permissoes.");
        builder.setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

}
