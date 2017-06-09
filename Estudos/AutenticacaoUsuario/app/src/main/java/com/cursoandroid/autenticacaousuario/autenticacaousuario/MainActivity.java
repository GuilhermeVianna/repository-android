package com.cursoandroid.autenticacaousuario.autenticacaousuario;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //login de usuario
        firebaseAuth.signInWithEmailAndPassword("guilherme.vianna25@gmail.com", "Jvv31052013")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //sucesso
                        }else{
                            //erro
                        }
                    }
                });

        //cadastro do usuario
        firebaseAuth.createUserWithEmailAndPassword("guilherme.vianna25@gmail.com", "Jvv31052013")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //sucesso
                        }else{
                            //erro
                        }
                    }
                });

        //verifica usuario logado
        if(firebaseAuth.getCurrentUser() != null){

            //Retorna Usuario Logado

        }else{

            //Retorna Nulo
        }

        //deslogar usuario
        firebaseAuth.signOut();

    }
}
