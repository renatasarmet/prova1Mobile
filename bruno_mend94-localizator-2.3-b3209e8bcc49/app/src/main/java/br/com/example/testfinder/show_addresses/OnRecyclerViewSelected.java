package br.com.example.testfinder.show_addresses;

import android.view.View;

//Criando interface OnRecyclerViewSelected para o clique em cada endereço
public interface OnRecyclerViewSelected {
    void onClick(View view,int position);
}
