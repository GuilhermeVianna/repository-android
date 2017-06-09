package com.gvdev.devmobile.papo10.helper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guilhermevianna on 22/05/17.
 */
public class Permissao {

    public static boolean validaPermissoes(int requestCode,Activity activity, String[] permissoes){

        if(Build.VERSION.SDK_INT >= 23){

            List<String> listaPermissoes = new ArrayList<String>();

            /*Percorre as permissoes passadas, e verificando umauma
            se ja tem a permissao liberada*/
            for(String permissao : permissoes){

                boolean validaPermissao  = ContextCompat.checkSelfPermission(activity,permissao) == PackageManager.PERMISSION_GRANTED;
                if(!validaPermissao){
                    listaPermissoes.add(permissao);
                }
            }
            /* Caso a lista esteja vazia nao e necessario solicitar permissao */
            if(listaPermissoes.isEmpty()) return true;

            String[] novasPermissoes = new String[listaPermissoes.size()];
            listaPermissoes.toArray(novasPermissoes);

            /* solicita permissao */
            ActivityCompat.requestPermissions(activity, novasPermissoes, requestCode);
        }


        return true;
    }
}
