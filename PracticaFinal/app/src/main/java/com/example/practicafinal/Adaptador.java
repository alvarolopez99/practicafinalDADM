package com.example.practicafinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    private ArrayList<Item> list;
    private Context context;
    ImageView fotoPerfil;
    TextView alias, fechaUltima, numPartidas, maxAciertos;

    public Adaptador(ArrayList<Item> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = (Item) getItem(position);


        convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
        fotoPerfil = (ImageView) convertView.findViewById(R.id.foto);
        alias = (TextView) convertView.findViewById(R.id.alias);

        fotoPerfil.setImageBitmap(item.getFotoPerfil());
        alias.setText(item.getAlias());

        return convertView;
    }


}
