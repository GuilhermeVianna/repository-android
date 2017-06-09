package com.gvdev.purrinha.purrinha;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.facebook.FacebookSdk;

public class TelaLoginActivity extends FragmentActivity {

    private EditText textoEmail;
    private EditText textoSenha;
    private LoginButton botaoFacebook;
    private LoginManager loginManager;
    private CallbackManager  callbackManager;


    //https://purrinha-game.firebaseapp.com/__/auth/handler
    //pKAMBwKonbZxEsavnIvfBNXdrmE=

    private DatabaseReference databaseReference =FirebaseDatabase.getInstance().getReference();
    //private FacebookAuthCredential facebookAuthCredential =



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);





    }

}
