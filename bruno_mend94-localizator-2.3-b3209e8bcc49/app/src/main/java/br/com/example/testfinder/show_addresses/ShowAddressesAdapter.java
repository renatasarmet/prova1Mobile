package br.com.example.testfinder.show_addresses;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.example.testfinder.R;
//Importando para utilizar o ButterKnife
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import java.util.List;

public class ShowAddressesAdapter extends RecyclerView.Adapter<ShowAddressesAdapter.ViewHolder>{

    private List<String> addressesList;
    //Criando atributo do tipo OnRecyclerViewSelected para uso posterior
    OnRecyclerViewSelected onRecyclerViewSelected;

    //Construtor para receber a lista
    ShowAddressesAdapter(List<String> addressesList){
        this.addressesList = addressesList;
    }

    //Infla o layout XML
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_list_item, parent, false);
        return new ViewHolder(v);
    }

    //Seta os dados na lista
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvAddress.setText(addressesList.get(position));
    }

    //Retorna o tamanho da lista
    @Override
    public int getItemCount() {
        return addressesList.size();
    }

    //Mapeamento dos componentes da View
    public class ViewHolder extends RecyclerView.ViewHolder{

        //Fazendo ligação XML com Java através do bind para diminuir código
        @BindView(R.id.tv_address) TextView tvAddress;

        public ViewHolder(View itemView) {
            super(itemView);
            //Fazendo ligação com o ButterKnife
            ButterKnife.bind(this,itemView);
        }

        //Seta clique rápido
        //Clique feito com ButterKnife
        @OnClick(R.id.address_item)
        void onItemClick(View view){
            if(onRecyclerViewSelected != null){
                onRecyclerViewSelected.onClick(view,getAdapterPosition());
            }
        }
    }
    //Atualiza o atributo dessa classe com o passado por parâmetro (método set)
    public void setOnRecyclerViewSelected(OnRecyclerViewSelected onRecyclerViewSelected){
        this.onRecyclerViewSelected= onRecyclerViewSelected;
    }

}