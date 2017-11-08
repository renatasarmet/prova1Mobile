package br.com.example.testfinder.main;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

//Criação do Presenter da MainActivity
public class MainPresenter {
    //código utilizado para adicionar novos endereços
    private final int RC_ADD_ADDRESS = 123;

   public MainPresenter(){}

    // Lógica para adicionar um endereço na lista. Código foi movido do MainActivity para o MainPresenter para satisfazer
    // o MVP
   public void addAddressInList(int requestCode,int resultCode,Intent data,List<String> lstAddresses){
        if(requestCode==RC_ADD_ADDRESS && resultCode== Activity.RESULT_OK){
            lstAddresses.add(data.getStringExtra("address_name"));
        }
    }
}
