package com.jayani.itubeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class itemAdapter extends RecyclerView.Adapter<itemAdapter.ViewHolder> {

    private ArrayList<itemModel> itemsList;
    private  OnItemClickListner mListener;

    public interface OnItemClickListner{

        void onItemClick(int position);
    }

    public void setOnClickListner(OnItemClickListner listner){
        mListener=listner;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;




        ViewHolder(View view, OnItemClickListner mListener) {
            super(view);
            itemName = view.findViewById(R.id.itemName);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(mListener != null){
                        int position=getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }

                }
            });


        }
    }

    public itemAdapter(ArrayList<itemModel> itemsList) {

//        this.context = context;
        this.itemsList = itemsList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.url_item,parent,false);
        ViewHolder evh = new ViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        itemModel item = itemsList.get(position);
        String url = item.getUrl();

        holder.itemName.setText(url);


    }

    public void removeAtPosition(int position) {
        itemsList.remove(position);

        notifyItemRemoved(position);
        notifyItemRangeChanged(position, itemsList.size());
    }

    public static void notifyChange(){
        notifyChange();
    }
    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}
