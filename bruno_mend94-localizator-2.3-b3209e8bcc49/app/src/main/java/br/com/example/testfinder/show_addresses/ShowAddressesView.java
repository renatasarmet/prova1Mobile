package br.com.example.testfinder.show_addresses;

import java.util.List;

//Criando interface para o ShowAddressesActivity
public interface ShowAddressesView {
    void updateList(List<String> lstAddresses);
}
