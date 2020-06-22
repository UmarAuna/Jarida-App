package com.example.jaridaapp.jaridaV1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jaridaapp.jaridaV1.Detail;
import com.example.jaridaapp.R;
import com.example.jaridaapp.jaridaV1.model.JaridaListItem;

import java.util.List;

public class JaridaAdapter extends RecyclerView.Adapter<JaridaAdapter.JaridaViewHolder>  {

    Context context;
    private List<JaridaListItem> mCountryList;

    public JaridaAdapter(List<JaridaListItem> countryList){
        mCountryList = countryList;
    }

    @NonNull
    @Override
    public JaridaAdapter.JaridaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jarida, parent, false);
        return new JaridaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JaridaAdapter.JaridaViewHolder holder, int position) {
        holder.textTitle.setText(mCountryList.get(position).getTitle());
        holder.textContent.setText(mCountryList.get(position).getContent());
        holder.textAuthor.setText(mCountryList.get(position).getAuthor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Detail.class);
                intent.putExtra("id", mCountryList.get(position).getId());
                intent.putExtra("title", mCountryList.get(position).getTitle());
                intent.putExtra("content", mCountryList.get(position).getContent());
                intent.putExtra("author", mCountryList.get(position).getAuthor());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCountryList !=null  ?mCountryList.size() : 0;
        //return mCountryList.size();
    }

    public class JaridaViewHolder extends RecyclerView.ViewHolder{
        TextView textTitle, textContent, textAuthor;

        public JaridaViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.titles);
            textContent = itemView.findViewById(R.id.content);
            textAuthor = itemView.findViewById(R.id.author);
        }
    }
}
