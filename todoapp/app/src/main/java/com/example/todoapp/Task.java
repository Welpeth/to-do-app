package com.example.todoapp;

public class Task {
    private String title;
    private String description;
    private boolean completed; // Estado da CheckBox
    private String dateCreated;

    public Task(String title, String description, boolean completed, String dateCreated) {
        this.title = title;
        this.description = description;
        this.completed = completed; // Inicializa o estado da tarefa
        this.dateCreated = dateCreated;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed; // Retorna o estado da tarefa
    }

    public void setCompleted(boolean completed) {
        this.completed = completed; // Atualiza o estado da tarefa
    }

    public String getDateCreated() {
        return dateCreated;
    }
}
