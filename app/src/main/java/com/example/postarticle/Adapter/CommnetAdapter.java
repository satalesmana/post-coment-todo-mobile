package com.example.postarticle.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.postarticle.Model.PostModel;
import com.example.postarticle.R;

import java.util.List;

public class CommnetAdapter extends RecyclerView.Adapter<CommnetAdapter.AdapterHolder> {
    Context context;


    public CommnetAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public CommnetAdapter.AdapterHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_commentar_list,parent,false);
        CommnetAdapter.AdapterHolder adapterHolder = new CommnetAdapter.AdapterHolder(view);
        return  adapterHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommnetAdapter.AdapterHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        public AdapterHolder(@NonNull  View itemView) {
            super(itemView);
        }
    }
}
