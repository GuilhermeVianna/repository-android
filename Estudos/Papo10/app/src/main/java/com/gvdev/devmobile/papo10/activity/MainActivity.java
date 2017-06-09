package com.gvdev.devmobile.papo10.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.gvdev.devmobile.papo10.R;
import com.gvdev.devmobile.papo10.adapter.TabAdapter;
import com.gvdev.devmobile.papo10.config.ConfiguracaoFirebase;
import com.gvdev.devmobile.papo10.helper.Base64Custom;
import com.gvdev.devmobile.papo10.helper.Preferencias;
import com.gvdev.devmobile.papo10.helper.SlidingTabLayout;
import com.gvdev.devmobile.papo10.model.Contato;
import com.gvdev.devmobile.papo10.model.Usuario;

public class MainActivity extends AppCompatActivity {
    /*
    Links com Informações sobre o ToolBar:
    http://android-developers.blogspot.com.br/2014/10/appcompat-v21-material-design-for-pre.html

    Link para código Java para exibição dos menus:
    http://developer.android.com/intl/pt-br/guide/topics/ui/menus.html

    Link para ícones a serem utilizados na ToolBar:
    https://materialdesignicons.com/

    Cores para utilização com o Material Design:
    http://www.materialpalette.com/

    Recomendações de utilização de cores na documentação Oficial:
    https://www.google.com/design/spec/style/color.html#color-color-schemes
     */

    private Toolbar toolbar;
    private FirebaseAuth firebaseAuth;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    private String idContato;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbarId);
        toolbar.setTitle("Papo 10");
        setSupportActionBar(toolbar);

        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.stl_tabs);
        viewPager = (ViewPager) findViewById(R.id.vp_pagina);

        //CONFIGURANDO AS TABS
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this,R.color.colorBarraTab));

        //CONFIGURANDO ADAPTER
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);

        slidingTabLayout.setViewPager(viewPager);


        /*
        botao texte
         botaoLogout = (Button) findViewById(R.id.botaoLogout);
        botaoLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth = ConfiguracaoFirebase.getFirebaseAuth();
                firebaseAuth.signOut();
                retornaTelaLogin();

            }
        });
         */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();//utilizada para inflar/exibir menus na tela
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_sair:
                ConfiguracaoFirebase.deslogarUsuario();
                retornaTelaLogin();
                return true;
            case R.id.item_pesquisa:

                return true;
            case R.id.item_adicionar:
                abrirCadastroContato();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void abrirCadastroContato() {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

        //CONFIGURANDO DIALOG
        alert.setTitle("Novo Contato");
        alert.setMessage("E-mail do usuário");
        alert.setCancelable(false);

        final EditText editText = new EditText(MainActivity.this);
        alert.setView(editText);

        alert.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String emailContato = editText.getText().toString();

                if(emailContato.isEmpty()){

                    Toast.makeText(MainActivity.this, "Preencha o e-mail do contato!", Toast.LENGTH_SHORT).show();

                }else{

                    idContato = Base64Custom.encodeBase64(emailContato);
                    databaseReference = ConfiguracaoFirebase.getFirebase().child("USUARIOS");
                    databaseReference = databaseReference.child(idContato);
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.getValue() != null){

                               Usuario usuarioContato = dataSnapshot.getValue(Usuario.class);

                                Preferencias preferencia = new Preferencias(MainActivity.this);
                                databaseReference = ConfiguracaoFirebase.getFirebase();
                                databaseReference = databaseReference.
                                        child("CONTATOS").
                                        child(preferencia.getIdUsuarioLogado()).
                                        child(idContato);

                                Contato contato = new Contato();
                                contato.setIdContato(idContato);
                                contato.setEmail(usuarioContato.getEmail());
                                contato.setNome(usuarioContato.getNome());

                                databaseReference.setValue(contato);
                                Toast.makeText(MainActivity.this, "Contato adicionado com sucesso!", Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(MainActivity.this, "Contato não possui cadastro no papo 10!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }

            }
        });

        alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alert.create();
        alert.show();

    }

    private void retornaTelaLogin() {
        Intent intent = new Intent(MainActivity.this, LoginEmailActivity.class);
        startActivity(intent);
        finish();
    }
}
