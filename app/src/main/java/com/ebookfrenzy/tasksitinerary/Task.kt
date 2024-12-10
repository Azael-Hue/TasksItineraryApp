package com.example.tasksitinerary.com.ebookfrenzy.tasksitinerary

class Task {
    var id: Int = 0
    var taskName: String? = null
    var taskImportance: String? = null
    var date: String? = null

    constructor(id: Int, name: String, importance: String, date: String) {
        this.id = id
        this.taskName = name
        this.taskImportance = importance
        this.date = date
    }

    constructor(name: String, importance: String, date: String) {
        this.taskName = name
        this.taskImportance = importance
        this.date = date
    }
}