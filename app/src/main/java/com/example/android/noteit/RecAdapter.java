package com.example.android.noteit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder> {

    Context context;
    ArrayList<ModelClass> modelClasses = new ArrayList<>();

    public RecAdapter(ArrayList<ModelClass> modelClasses) {
        this.modelClasses = modelClasses;
    }

    public RecAdapter(Context context, ArrayList<ModelClass> modelClasses) {
        this.context = context;
        this.modelClasses = modelClasses;
    }

    @NonNull
    @Override
    public RecAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.overview,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecAdapter.ViewHolder holder, int position) {
            holder.title.setText(modelClasses.get(position).getTitle());
            holder.description.setText(modelClasses.get(position).getDescription());
            holder.id=modelClasses.get(position).getId();
        }

    @Override
    public int getItemCount() {
        return modelClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView title;
        private TextView description;
        private int id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.titleView);
            description=itemView.findViewById(R.id.descriptionView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(view.getContext(),UpdateDelete.class);
                    intent.putExtra("titleKey",title.getText().toString());
                    intent.putExtra("descriptionKey",description.getText().toString());
                    intent.putExtra("idKey",id);
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}
