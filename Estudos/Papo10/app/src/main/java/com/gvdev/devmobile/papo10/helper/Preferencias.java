package com.gvdev.devmobile.papo10.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by guilhermevianna on 22/05/17.
 */
public class Preferencias {

    private static final String NOME_ARQUIVO = "papo10.preferencias";
    private static final int MODE = 0; //MODE_PRIVATE  - somente o app tera acesso
    private static final String CHAVE_NOME = "nome";
    private static final String CHAVE_TELEFONE = "telefone";
    private static final String CHAVE_TOKEN = "token";

    private static final String ID_USUARIO_LOGADO = "idUsuario";
    private static final String NOME_USUARIO_LOGADO = "nomeUsuario";



    private Context contexto;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;



    public Preferencias (Context contextoParametros){

        contexto = contextoParametros;
        preferences = contexto.getSharedPreferences(NOME_ARQUIVO,MODE);
        editor = preferences.edit();
    }

    public void salvarUsuariosPreferencias(String nomeUsuario, String telefone, String token){

        editor.putString(CHAVE_NOME, nomeUsuario);
        editor.putString(CHAVE_TELEFONE, nomeUsuario);
        editor.putString(CHAVE_TOKEN, nomeUsuario);

    }

    public HashMap<String, String> getDadosUsuarios(){
        HashMap<String, String> dadosUsuarios = new HashMap<>();
        dadosUsuarios.put(CHAVE_NOME,preferences.getString(CHAVE_NOME,null));
        dadosUsuarios.put(CHAVE_TELEFONE,preferences.getString(CHAVE_TELEFONE,null));
        dadosUsuarios.put(CHAVE_TOKEN,preferences.getString(CHAVE_TOKEN,null));

        return dadosUsuarios;

    }

    public void salvarDadosUsuarioLogado(String idUsuario){
        editor.putString(ID_USUARIO_LOGADO, idUsuario);
        editor.commit();
    }

    public String getIdUsuarioLogado(){
        return preferences.getString(ID_USUARIO_LOGADO, null);
    }
}
