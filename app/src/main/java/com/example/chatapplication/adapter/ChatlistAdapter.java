package com.example.chatapplication.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapplication.R;
import com.example.chatapplication.model.Chatlist;

import java.util.List;

public class ChatlistAdapter extends RecyclerView.Adapter<ChatlistAdapter.Holder>{
    private List<Chatlist>list;
    private Context Context;

    public ChatlistAdapter(List<Chatlist> list,Context Context) {
        this.list = list;
        this.Context=Context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(Context).inflate(R.layout.layout_chat_list,parent,attachToRoot false);
       return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Chatlist chatlist= list.get(position);
        holder.tvName.setText(chatlist.getUserName());
        holder.tvDesc.setText(chatlist.getDescription());
        holder.tvDate.setText(chatlist.getDate());
        //for image we need library ....

        Glide.with(context).load(chatlist.getUrlProfile()).into(holder.profile);
    }

    @Override
    public int getItemCount() {
        return  list.size();
    }

    public static class Holder extends RecyclerView.ViewHolder{
        private TextView tvName,tvDesc,tvDate;
        private CircularImageView profile;
        public Holder(@NonNull View itemView) {
            super(itemView);
            tvDate=itemView.findViewById(R.id.tv_date);
            tvDesc=itemView.findViewById(R.id.tv_desc);
            tvName=itemView.findViewById(R.id.tv_name);
            profile=itemView.findViewById(R.id.image_profile);
        }
    }
}
