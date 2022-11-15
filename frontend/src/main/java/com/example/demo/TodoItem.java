package com.example.demo;

import java.io.Serializable;

public class TodoItem implements Serializable{

    public String todo;
    public int priority = 2;
    
    public TodoItem(){}

    public TodoItem(String todo) {
        this.todo = todo;
    }    
    
    public TodoItem(String todo, int priority) {
        this.todo = todo;
        this.priority = priority;
    }

    public String getTodo() {
        return todo;
    }

    public int getPriority() {
        return priority;
    }
    
    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "TodoItem [todo=" + todo + ", priority=" + priority + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((todo == null) ? 0 : todo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TodoItem other = (TodoItem) obj;
        if (todo == null) {
            if (other.todo != null)
                return false;
        } else if (!todo.equals(other.todo))
            return false;
        return true;
    }
    
}
