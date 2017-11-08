package br.com.example.testfinder.show_addresses;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import br.com.example.testfinder.R;

import java.util.ArrayList;
import java.util.List;

//Importando para utilizar o ButterKnife
import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowAddressesActivity extends AppCompatActivity implements ShowAddressesView {
    //Fazendo ligação XML com Java através do bind para diminuir codigo
    @BindView(R.id.rv_addresses) RecyclerView rvAddresses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_addresses);
        //Fazendo ligação com o ButterKnife
        ButterKnife.bind(this);
        //captura a lista enviada pela MainActivity
        ArrayList<String> lstAddresses = getIntent().getStringArrayListExtra("addresses_list");
        //Chamando o método para atualizar a lista com os valores de endereços adicionados.
        this.updateList(lstAddresses);
    }

    //Adicionando método para atualizar lista e clique na view do endereço
    @Override
    public void updateList(List<String> lstAddresses){
        //instancia um ShowAddressesAdapter passando a lista de endereços
        ShowAddressesAdapter showAddressesAdapter = new ShowAddressesAdapter(lstAddresses);
        //Implementando o método onClick da interface OnRecyclerViewSelected
        showAddressesAdapter.setOnRecyclerViewSelected(new OnRecyclerViewSelected() {
            @Override
            public void onClick(View view, int position) {
                //Abrindo o mapa
                Intent intentMapa = new Intent(Intent.ACTION_VIEW);
                //Pegando endereço correspondente à view clicada pelo usuário
                TextView endereco = (TextView)view.findViewById(R.id.tv_address);
                //Setando o endereço a ser buscado no mapa
                intentMapa.setData(Uri.parse("geo:0,0?q=" + endereco.getText().toString() ));
                if(intentMapa.resolveActivity(getPackageManager()) != null) {
                    startActivity(intentMapa);
                }else {
                    Toast toast = Toast.makeText(ShowAddressesActivity.this, "Impossível abrir o recurso", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
        //seta o adapter no Recycler View
        rvAddresses.setAdapter(showAddressesAdapter);

        //cria o gerenciador de layouts
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //seta o gerenciador de layouts no Recycler View
        rvAddresses.setLayoutManager(layoutManager);
    }
}