package com.example.taskmanagerapp

class Task {
    var taskName: String
    var taskCategory: TaskCategory
    var deadline: Int
    var status: Status
    var description: String

    // Full fields constructor
    constructor(taskName: String, taskCategory: TaskCategory, deadline: Int,
                status: Status, description: String) {
        this.taskName = taskName
        this.taskCategory = taskCategory
        this.deadline = deadline
        this.status = status
        this.description = description
    }

    // Except description
    constructor(taskName: String, taskCategory: TaskCategory, deadline: Int, status: Status) {
        this.taskName = taskName
        this.taskCategory = taskCategory
        this.deadline = deadline
        this.status = status
        description = "No description"
    }

    // Without description, category
    constructor(taskName: String, deadline: Int, status: Status) {
        this.taskName = taskName
        taskCategory = TaskCategory.CATEGORY_NOT_SPECIFIED
        this.deadline = deadline
        this.status = status
        description = "No description"
    }

    // Just name, deadline
    constructor(taskName: String, deadline: Int) {
        this.taskName = taskName
        taskCategory = TaskCategory.CATEGORY_NOT_SPECIFIED
        this.deadline = deadline
        status = Status.STATUS_IN_PROCESS
        description = "No description"
    }



}