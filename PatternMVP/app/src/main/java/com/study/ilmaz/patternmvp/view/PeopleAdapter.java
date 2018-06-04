package com.study.ilmaz.patternmvp.view;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.study.ilmaz.patternmvp.R;
import com.study.ilmaz.patternmvp.model.entities.User;

import java.util.Collections;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>{
    private List<User> userList;
    private ItemClickListener itemClickListener;

    public PeopleAdapter() {
        userList = Collections.emptyList();
    }

    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_people, parent, false);
        return new PeopleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PeopleViewHolder holder, int position) {
        final User user = userList.get(position);
        holder.bind(user);
        if(itemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(user, v);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public static class PeopleViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvPhone;

        public PeopleViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvPhone = (TextView) itemView.findViewById(R.id.tv_phone);

        }

        void bind(User user){
            tvName.setText(user.getFullName());
            tvPhone.setText(user.getPhone());
        }
    }

    public interface ItemClickListener {
        void onItemClick(User user, View view);
    }
}
