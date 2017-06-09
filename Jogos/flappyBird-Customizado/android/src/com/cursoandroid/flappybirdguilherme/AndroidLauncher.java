package com.cursoandroid.flappybirdguilherme;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AndroidLauncher extends AndroidApplication {

	private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(); //criando instancia do banco
	private DatabaseReference usuarioRef = databaseReference.child("Usuario"); // criando tabela



	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new FlappyBird(), config);

		Usuario usuario = new Usuario();
		Pontuacao pontuacao = new Pontuacao();

		/*
		usuario.setId("1");
		usuario.setNome("Newma");
		usuario.setSobrenome("Brugger");

		pontuacao.setPontos("30");
		pontuacao.setMelhorPontuacao("100");
		pontuacao.setPiorPontuacao("1");

		usuario.setPontuacao(pontuacao);

		usuarioRef.child(usuario.getId()).setValue(usuario);
		*/

		//conseguimos adicionar um listener para referencia(ouvinte)
		usuarioRef.addValueEventListener(new ValueEventListener() {
			@Override
			//retorna os dados quando é alterado
			public void onDataChange(DataSnapshot dataSnapshot) {
				dataSnapshot.getValue().toString();

			}

			@Override
			//caso ocorra erro, recuperamos os dados
			public void onCancelled(DatabaseError databaseError) {

			}
		})







	}
}
