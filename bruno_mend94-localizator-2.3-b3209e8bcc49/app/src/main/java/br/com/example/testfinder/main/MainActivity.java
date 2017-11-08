package br.com.example.testfinder.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import br.com.example.testfinder.R;
import br.com.example.testfinder.add_address.AddAddressActivity;
import br.com.example.testfinder.show_addresses.ShowAddressesActivity;

import java.util.ArrayList;
//Importando para utilizar o ButterKnife
import butterknife.ButterKnife;
import butterknife.OnClick;

// Alunos:
// Bruno Prates Coelho RA:619426
// Renata Sarmet Smiderle Mendes RA: 726586

public class MainActivity extends AppCompatActivity implements MainView{

    //código utilizado para adicionar novos endereços
    private final int RC_ADD_ADDRESS = 123;
    //lista de endereços
    private ArrayList<String> lstAddresses = new ArrayList<>();

    //Criando objeto mainPresenter para utilização posterior
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Fazendo ligação com o ButterKnife
        ButterKnife.bind(this);
        //Instanciando o mainPresenter
        mainPresenter = new MainPresenter();
    }

    //Atualizando o metodo onClick com o ButterKnife
    @Override
    @OnClick(R.id.btn_add_address)
    public void openAddAddress() {
        Intent openAddAddressActivity = new Intent(MainActivity.this, AddAddressActivity.class);
        startActivityForResult(openAddAddressActivity, RC_ADD_ADDRESS);
    }

    //abre a activity para exibir os endereços cadastrados
    //Atualizando o metodo onClick com o ButterKnife
    @Override
    @OnClick(R.id.btn_show_addresses)
    public void showAddress() {
        //verifica se há endereços cadastrados antes executar a activity
        if(lstAddresses.size() <= 0){
            Log.d("ADD","entrando aqui");
            Toast.makeText(MainActivity.this, "Não há endereços cadastrados", Toast.LENGTH_SHORT).show();
        }else{
            //abre a ShowAddressActivity enviando a lista de endereços
            Intent openShowAddressActivity = new Intent(MainActivity.this, ShowAddressesActivity.class);
            openShowAddressActivity.putStringArrayListExtra("addresses_list", lstAddresses);
            startActivity(openShowAddressActivity);
        }
    }

    //A lógica presente nessa parte do código foi movida para o MainPresenter, a fim de satisfazer o MVP
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Chamando o método do mainPresenter para adicionar endereços na lista
        mainPresenter.addAddressInList(requestCode,resultCode,data,lstAddresses);
    }
}