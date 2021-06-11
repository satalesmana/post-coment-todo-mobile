package com.example.postarticle.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.postarticle.Model.PostModel;
import com.example.postarticle.R;

import java.util.List;

public class PostListAdapter  extends RecyclerView.Adapter<PostListAdapter.AdaapterHolder> {
    private Context context;
    private List<PostModel> list;

    public PostListAdapter(Context context, List<PostModel> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PostListAdapter.AdaapterHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_post_list,parent,false);
        AdaapterHolder adaapterHolder = new AdaapterHolder(view);
        return  adaapterHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostListAdapter.AdaapterHolder holder, int position) {
        final PostModel postModel = list.get(position);

        String title = postModel.getTitle();
        String postId = postModel.getId();


        holder.tvTitle.setText(title);

        holder.btnPostDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPostDetail(postId);
            }
        });
    }

    private void viewPostDetail(String id){
        Log.d("Selected Id", id);
    }

    @Override
    public int getItemCount() {
        if(list !=null)
            return list.size();
        else
            return 0;
    }

    public class AdaapterHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        Button btnPostDetail;


        public AdaapterHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            btnPostDetail = itemView.findViewById(R.id.btn_post_detail);

        }
    }
}
