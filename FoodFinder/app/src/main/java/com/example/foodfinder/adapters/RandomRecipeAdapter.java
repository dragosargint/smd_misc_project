package com.example.foodfinder.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodfinder.interfaces.ImageClickListener;
import com.example.foodfinder.R;
import com.example.foodfinder.spoonacularAPI.responseformat.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipeAdapter  extends  RecyclerView.Adapter<RandomRecipeViewHolder>{

    Context context;
    List<Recipe> list;
    ImageClickListener listener;

    public RandomRecipeAdapter(Context context, List<Recipe> list, ImageClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
        holder.title.setText(list.get(position).title);
        Picasso.get().load(list.get(position).image).into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               listener.onClick(list.get(position).id,
                       list.get(position).image,
                       list.get(position).title);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        return list.size();
    }

    public void replaceAll(List<Recipe> new_list) {
        list.clear();
        list.addAll(new_list);
        notifyDataSetChanged();
    }
}
class RandomRecipeViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    ImageView image;

    public RandomRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        image = itemView.findViewById(R.id.image);
    }
}