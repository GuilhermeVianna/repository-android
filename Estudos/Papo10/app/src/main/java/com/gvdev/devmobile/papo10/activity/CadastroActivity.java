package com.gvdev.devmobile.papo10.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.gvdev.devmobile.papo10.R;
import com.gvdev.devmobile.papo10.config.ConfiguracaoFirebase;
import com.gvdev.devmobile.papo10.helper.Base64Custom;
import com.gvdev.devmobile.papo10.helper.Preferencias;
import com.gvdev.devmobile.papo10.model.Usuario;

public class CadastroActivity extends Activity {

    /*
    Base 64 (Encode/Decode):
    https://www.base64decode.org/
    http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.10.6
     */

    private Button botaoCadastrarUsuario;
    private EditText nomeCadUsuario;
    private EditText emailCadUsuario;
    private EditText senhaCadUsuario;
    private EditText confirmaSenhaCadUsuario;

    private Usuario usuario;

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        atribuiVariaveis();

        botaoCadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validaCamposPreenchidos()){
                    if(validaSenhasDigitadas()){
                        
                        usuario = new Usuario();
                        usuario.setNome(nomeCadUsuario.getText().toString());
                        usuario.setEmail(emailCadUsuario.getText().toString());
                        usuario.setSenha(senhaCadUsuario.getText().toString());
                        cadastrarUsuario();


                    }else{
                        Toast.makeText(CadastroActivity.this,"Senhas Digitadas nao conferem.",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(CadastroActivity.this,"Preencha Todos os Campos.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void cadastrarUsuario() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAuth();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CadastroActivity.this,"Usuario cadastrado com sucesso!",Toast.LENGTH_SHORT).show();

                    String idUsuario = Base64Custom.encodeBase64(usuario.getEmail());
                    usuario.setId(idUsuario);
                    usuario.salvar();

                    Preferencias preferencias = new Preferencias(CadastroActivity.this);
                    preferencias.salvarDadosUsuarioLogado(idUsuario);

                    Intent intent = new Intent(CadastroActivity.this, LoginEmailActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    String erroExcessao = "";
                    try{
                        throw task.getException();
                    }catch(FirebaseAuthWeakPasswordException e){
                        erroExcessao = e.getMessage();
                        e.printStackTrace();

                    }catch(FirebaseAuthInvalidCredentialsException e){
                        erroExcessao =  e.getMessage();

                    }catch(FirebaseAuthUserCollisionException e){
                        erroExcessao = e.getMessage();

                    } catch (Exception e) {
                        erroExcessao = "ao cadastrar usuario.";
                        e.printStackTrace();
                    }

                    Toast.makeText(CadastroActivity.this,"Erro " + erroExcessao,Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private boolean validaSenhasDigitadas() {
        String senhaDigitada = senhaCadUsuario.getText().toString();
        String confirmaSenhaDigitada =  confirmaSenhaCadUsuario.getText().toString();
        if(!senhaDigitada.equals(confirmaSenhaDigitada)){
            return false;
        }else{
            return true;
        }
    }

    private boolean validaCamposPreenchidos() {

        if(nomeCadUsuario.getText().toString().equals("")){
            return false;
        }else if(emailCadUsuario.getText().toString().equals("")){
            return false;
        }else if(senhaCadUsuario.getText().toString().equals("")){
            return false;
        }else if(confirmaSenhaCadUsuario.getText().toString().equals("")){
            return false;
        }else{
            return true;
        }

    }

    private void atribuiVariaveis() {

        botaoCadastrarUsuario = (Button) findViewById(R.id.botaoCadastrarUsuario);
        nomeCadUsuario = (EditText) findViewById(R.id.nomeCadUsuario);
        emailCadUsuario = (EditText) findViewById(R.id.emailCadUsuario);
        senhaCadUsuario = (EditText) findViewById(R.id.senhaCadUsuario);
        confirmaSenhaCadUsuario = (EditText) findViewById(R.id.confirmaSenhaCadUsuario);


    }
}
