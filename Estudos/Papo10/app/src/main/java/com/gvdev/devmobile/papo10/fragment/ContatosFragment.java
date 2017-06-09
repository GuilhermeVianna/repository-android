package com.gvdev.devmobile.papo10.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.gvdev.devmobile.papo10.R;
import com.gvdev.devmobile.papo10.config.ConfiguracaoFirebase;
import com.gvdev.devmobile.papo10.helper.Preferencias;
import com.gvdev.devmobile.papo10.model.Contato;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContatosFragment extends Fragment {

    private ListView listaContatos;
    private ArrayAdapter adapter;
    private ArrayList<String> contatos;
    private DatabaseReference reference;
    private ValueEventListener valueEventListener;


    public ContatosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_contatos, container, false);

        listaContatos = (ListView) view.findViewById(R.id.lv_contatos);
        contatos = new ArrayList<>();
        adapter = new ArrayAdapter(getActivity(), R.layout.lista_contatos, contatos);
        listaContatos.setAdapter(adapter);

        Preferencias preferencias = new Preferencias(getActivity());
        reference = ConfiguracaoFirebase.getFirebase().
                child("CONTATOS").
                child(preferencias.getIdUsuarioLogado());

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //limpar lista
                contatos.clear();

                //Listar contatos
                for(DataSnapshot dados : dataSnapshot.getChildren()){
                    Contato contato = dados.getValue(Contato.class);
                    contatos.add(contato.getNome());

                }

                //notificando adapter que houve mudança
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        reference.addValueEventListener(valueEventListener);


        return view;
    }

}
