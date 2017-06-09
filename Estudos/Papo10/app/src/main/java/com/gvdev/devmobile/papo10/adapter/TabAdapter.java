package com.gvdev.devmobile.papo10.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gvdev.devmobile.papo10.fragment.ContatosFragment;
import com.gvdev.devmobile.papo10.fragment.ConversasFragment;

/**
 * Created by guilhermevianna on 29/05/17.
 */
public class TabAdapter  extends FragmentStatePagerAdapter{

    private String[] tituloAbas = {"SEUS PAPOS", "CONTATOS"};

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position){
            case 0 :
                fragment = new ConversasFragment();
                break;
            case 1 :
                fragment = new ContatosFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return tituloAbas.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {


        return tituloAbas[position];
    }
}
