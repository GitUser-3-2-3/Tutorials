package com.parth.model;

public class Task {

    public static final int LOW_PRIORITY = 1;
    public static final int MEDIUM_PRIORITY = 2;
    public static final int HIGH_PRIORITY = 1;

    private final int id;
    private final String title;
    private final String description;
    private final int priority;
    private final String deadline;
    private final boolean status;

    public static class Builder {
        // must have fields
        private final int id;
        private final String title;

        // optional
        private String description = "";
        private int priority = LOW_PRIORITY;
        private String deadline = "";
        private boolean status = false;

        public Builder(int id, String title) {
            this.id = id;
            this.title = title;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder priority(int val) {
            priority = val;
            return this;
        }

        public Builder deadline(String val) {
            deadline = val;
            return this;
        }

        public Builder status(boolean val) {
            status = val;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }

    private Task(Builder builder) {
        id = builder.id;
        title = builder.title;
        description = builder.description;
        priority = builder.priority;
        deadline = builder.deadline;
        status = builder.status;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public String getDeadline() {
        return deadline;
    }

    public boolean isStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Task {" +
            "id = " + id +
            ", title = '" + title + '\'' +
            ", description = '" + description + '\'' +
            ", priority = '" + priority + '\'' +
            ", deadline = '" + deadline + '\'' +
            ", status = " + status +
            '}';
    }
}
