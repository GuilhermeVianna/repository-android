package com.gvdev.devmobile.papo10.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by guilhermevianna on 23/05/17.
 */
public final class ConfiguracaoFirebase {

    private static DatabaseReference firebaseRef;
    private static FirebaseAuth firebaseAuth;


    /*
    responsavel por recuperar instacia do firebase
     */
    public static DatabaseReference getFirebase(){

        if(firebaseRef == null){
            firebaseRef = FirebaseDatabase.getInstance().getReference();
        }

        return firebaseRef;
    }

    /*
   responsavel por recuperar instacia do firebaseAuth
    */
    public static FirebaseAuth getFirebaseAuth(){
        if(firebaseAuth == null){
            firebaseAuth = FirebaseAuth.getInstance();
        }

        return firebaseAuth;

    }

    public static void deslogarUsuario(){
        firebaseAuth = getFirebaseAuth();
        firebaseAuth.signOut();

    }
}
