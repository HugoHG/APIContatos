package br.com.senaijandira.apicontatos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 17170098 on 02/05/2018.
 */

public class ContatoAdapter extends ArrayAdapter<Contato> {
    public ContatoAdapter(Context ctx){
        super(ctx, 0, new ArrayList<Contato>());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item,null);
        }
        //pegando o item que será carregado
        Contato item = getItem(position);

        //ViewHolder é uma atualização disso
        TextView txt_nome = v.findViewById(R.id.txt_item_nome);
        TextView txt_email = v.findViewById(R.id.txt_item_email);

        //atualizando a UI
        txt_nome.setText(item.getNome());
        txt_email.setText(item.getEmail());

        return v;
    }
}
