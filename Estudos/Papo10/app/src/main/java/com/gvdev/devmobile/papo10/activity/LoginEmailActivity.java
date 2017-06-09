package com.gvdev.devmobile.papo10.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.gvdev.devmobile.papo10.R;
import com.gvdev.devmobile.papo10.config.ConfiguracaoFirebase;
import com.gvdev.devmobile.papo10.helper.Base64Custom;
import com.gvdev.devmobile.papo10.helper.Permissao;
import com.gvdev.devmobile.papo10.helper.Preferencias;
import com.gvdev.devmobile.papo10.model.Usuario;

import java.util.Random;

public class LoginEmailActivity extends Activity {

    //drawable importer https://github.com/winterDroid/android-drawable-importer-intellij-plugin
    //fragments https://developer.android.com/guide/components/fragments.html?hl=pt-br
    /*
    Tabs:
    https://www.google.com/design/spec/components/tabs.html#
    https://github.com/google/iosched
    https://www.google.com/design/spec/style/color.html#color-color-palette
    http://developer.android.com/intl/pt-br/samples/SlidingTabsBasic/src/com.example.android.common/view/SlidingTabLayout.html
     */

    private Button botaoLogar;
    private EditText emailUsuario;
    private EditText senhaUsuario;
    private TextView textoChamadaCadastro;

    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);

        verificaUsuarioLogado();

        atribuiVariaveis();
            botaoLogar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(validaCamposPreenchidos()){
                        usuario = new Usuario();
                        usuario.setEmail(emailUsuario.getText().toString());
                        usuario.setSenha(senhaUsuario.getText().toString());
                        validarLogin();
                    }else{
                        Toast.makeText(LoginEmailActivity.this,"Preencha os campos corretamente.",Toast.LENGTH_SHORT).show();
                    }


                }
            });



        textoChamadaCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCadastroUsuario();

            }
        });

    }

    private boolean validaCamposPreenchidos() {
        if(emailUsuario.getText().toString().equals("") || senhaUsuario.getText().toString().equals("")){
            return false;
        }else{
            return true;
        }
    }

    private void verificaUsuarioLogado(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAuth();
        if(autenticacao.getCurrentUser() != null){
            abrirTelaPrincipal();
        };
    }

    private void atribuiVariaveis() {
        botaoLogar = (Button) findViewById(R.id.BotaoLogarId);
        emailUsuario = (EditText) findViewById(R.id.textoEmail);
        senhaUsuario = (EditText) findViewById(R.id.textoSenha);
        textoChamadaCadastro = (TextView) findViewById(R.id.chamadaCadastro);
    }

    private void validarLogin() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAuth();
        autenticacao.signInWithEmailAndPassword(usuario.getEmail(),usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Preferencias preferencias = new Preferencias(LoginEmailActivity.this);
                    String idUsuario = Base64Custom.encodeBase64(usuario.getEmail());
                    preferencias.salvarDadosUsuarioLogado(idUsuario);

                    abrirTelaPrincipal();
                    Toast.makeText(LoginEmailActivity.this,"Sucesso ao realizar login.",Toast.LENGTH_SHORT).show();


                }else{
                    String erroExcessao = "";
                    try{
                        throw task.getException();

                    }catch(FirebaseAuthInvalidUserException e){
                        erroExcessao = e.getMessage();
                    }catch(FirebaseAuthInvalidCredentialsException e){
                        erroExcessao = e.getMessage();
                    }catch (Exception e){
                        e.printStackTrace();
                        erroExcessao = "ao efetuar login do usuário.";
                    }

                    Toast.makeText(LoginEmailActivity.this,"Erro " + erroExcessao,Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    public void abrirCadastroUsuario(){
        Intent intent = new Intent(LoginEmailActivity.this, CadastroActivity.class);
        startActivity(intent);
    }

    public void abrirTelaPrincipal(){
        Intent intent = new Intent(LoginEmailActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
