package com.example.foodfinder.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodfinder.R;
import com.example.foodfinder.interfaces.ImageClickListener;
import com.example.foodfinder.spoonacularAPI.responseformat.Instructions;
import com.example.foodfinder.spoonacularAPI.responseformat.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InstructionsAdapter extends  RecyclerView.Adapter<InstructionsViewHolder>{

    Context context;
    Instructions instructions;

    public InstructionsAdapter(Context context, Instructions instructions) {
        this.context = context;
        this.instructions = instructions;
    }

    @NonNull
    @Override
    public InstructionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_steps, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionsViewHolder holder, int position) {
        holder.step.setText("Step " + instructions.steps.get(position).number);
        holder.details.setText(instructions.steps.get(position).step);
    }

    @Override
    public int getItemCount() {
        if (instructions != null && instructions.steps != null)
            return instructions.steps.size();
        return 0;
    }

    public void replaceAll(Instructions new_instructions) {
        this.instructions = new_instructions;

        notifyDataSetChanged();
    }
}
class InstructionsViewHolder extends RecyclerView.ViewHolder {
    TextView step;
    TextView details;

    public InstructionsViewHolder(@NonNull View itemView) {
        super(itemView);
        step = itemView.findViewById(R.id.step);
        details = itemView.findViewById(R.id.details);
    }
}