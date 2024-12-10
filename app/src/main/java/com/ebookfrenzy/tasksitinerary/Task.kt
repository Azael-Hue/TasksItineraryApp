package com.example.tasksitinerary.com.ebookfrenzy.tasksitinerary

/// The task class consist of 3 elements
// ID an Integer
// task name, a string
// and importance, also a string
class Task {
    var id: Int = 0
    var taskName: String? = null
    var taskImportance: String? = null


    constructor(id: Int, name: String, importance: String) {
        this.id = id
        this.taskName = name
        this.taskImportance = importance
    }

    constructor(name: String, importance: String) {
        this.taskName = name
        this.taskImportance = importance
    }
}