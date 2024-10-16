package com.example.todoapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private ArrayList<Task> taskList;
    private Context context;

    public TaskAdapter(ArrayList<Task> taskList, Context context) {
        this.taskList = taskList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.task_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.titleTextView.setText(task.getTitle());
        holder.dateTextView.setText(task.getDateCreated());
        holder.descriptionTextView.setText(task.getDescription());

        // Configura a visibilidade da descrição como GONE inicialmente
        holder.descriptionTextView.setVisibility(View.GONE);

        // Restaura o estado da CheckBox
        holder.checkBoxCompleted.setChecked(task.isCompleted()); // Restaura o estado da CheckBox

        // Configurando o listener da CheckBox
        holder.checkBoxCompleted.setOnCheckedChangeListener((buttonView, isChecked) -> {
            task.setCompleted(isChecked); // Atualiza o estado da tarefa
            // Salva as tarefas após a alteração do estado
            ((MainActivity) context).saveTasks();
        });

        // Configurando o clique no título da tarefa
        holder.titleTextView.setOnClickListener(v -> {
            if (holder.descriptionTextView.getVisibility() == View.VISIBLE) {
                holder.descriptionTextView.setVisibility(View.GONE);
            } else {
                holder.descriptionTextView.setVisibility(View.VISIBLE);
            }
        });

        // Configurando o botão de excluir
        holder.deleteButton.setOnClickListener(v -> {
            taskList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, taskList.size());

            // Chama saveTasks na MainActivity para atualizar as SharedPreferences
            if (context instanceof MainActivity) {
                ((MainActivity) context).saveTasks();
            }

            Toast.makeText(context, "Task deleted", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView dateTextView;
        public TextView descriptionTextView;
        public Button deleteButton;
        public CheckBox checkBoxCompleted; // Adiciona a CheckBox

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.taskTitle);
            dateTextView = itemView.findViewById(R.id.taskDate);
            descriptionTextView = itemView.findViewById(R.id.taskDescription);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            checkBoxCompleted = itemView.findViewById(R.id.checkBoxCompleted); // Inicializa a CheckBox
        }
    }
}