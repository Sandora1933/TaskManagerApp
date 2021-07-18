package com.example.taskmanagerapp

class Task {
    var taskName: String
    var taskCategory: TaskCategory
    var deadline: String
    var status: Status = Status.STATUS_EXPIRED
    var description: String

    // Full fields constructor
    constructor(taskName: String, taskCategory: TaskCategory, deadline: String,
                status: Status, description: String) {
        this.taskName = taskName
        this.taskCategory = taskCategory
        this.deadline = deadline
        this.status = status
        this.description = description
    }

    // Just name, deadline
    constructor(taskName: String, deadline: String) {
        this.taskName = taskName
        taskCategory = TaskCategory.CATEGORY_NOT_SPECIFIED
        this.deadline = deadline
        status = Status.STATUS_IN_PROCESS
        description = "No description"
    }

    constructor(taskName: String, category: TaskCategory, deadline: String, description: String){
        this.taskName = taskName
        this.taskCategory = category
        this.deadline = deadline
        this.description = description
    }

    override fun toString(): String {
        return "Obj: $taskName $description"
    }
}