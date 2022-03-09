///*
// * Copyright (C) 2019 The Android Open Source Project
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package com.marvel.radwa.data.source
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.map
//import com.example.android.architecture.blueprints.todoapp.data.Result.Success
//import com.example.android.architecture.blueprints.todoapp.data.Task
//import com.marvel.radwa.data.Resource
//import com.marvel.radwa.data.models.Character
//import com.marvel.radwa.data.models.Comics
//import com.marvel.radwa.data.models.responses.BaseResponse
//import kotlinx.coroutines.runBlocking
//import java.util.LinkedHashMap
//
///**
// * Implementation of a remote data source with static access to the data for easy testing.
// */
//class FakeTestRepository : DataSource {
//
//    var tasksServiceData: LinkedHashMap<String, Character> = LinkedHashMap()
//
//    private val observableTasks = MutableLiveData<Result<List<Character>>>()
//
//
//    override suspend fun getTask(taskId: String, forceUpdate: Boolean): Result<Task> {
//        tasksServiceData[taskId]?.let {
//            return Success(it)
//        }
//        return Error(Exception("Could not find task"))
//    }
//
//    override suspend fun getTasks(forceUpdate: Boolean): Result<List<Task>> {
//        return Success(tasksServiceData.values.toList())
//    }
//
//    override suspend fun saveTask(task: Task) {
//        tasksServiceData[task.id] = task
//    }
//
//    override suspend fun completeTask(task: Task) {
//        val completedTask = task.copy(isCompleted = true)
//        tasksServiceData[task.id] = completedTask
//        refreshTasks()
//    }
//
//    override suspend fun completeTask(taskId: String) {
//        // Not required for the remote data source.
//        throw NotImplementedError()
//    }
//
//    override suspend fun activateTask(task: Task) {
//        val activeTask = task.copy(isCompleted = false)
//        tasksServiceData[task.id] = activeTask
//        refreshTasks()
//    }
//
//    override suspend fun activateTask(taskId: String) {
//        throw NotImplementedError()
//    }
//
//
//    override suspend fun deleteTask(taskId: String) {
//        tasksServiceData.remove(taskId)
//        refreshTasks()
//    }
//
//    override suspend fun deleteAllTasks() {
//        tasksServiceData.clear()
//        refreshTasks()
//    }
//
//    fun addTasks(vararg tasks: Task) {
//        for (task in tasks) {
//            tasksServiceData[task.id] = task
//        }
//        runBlocking { refreshTasks() }
//    }
//
//    override fun saveCharacter(character: Character) {
//
//    }
//
//    override fun getAllCharacters(): List<Character> {
//        TODO("Not yet implemented")
//    }
//
//    override fun saveComic(comics: Comics) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getComicsByCharacterId(id: Int): List<Comics> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getMarvelCharacters(
//        ts: String,
//        apikey: String,
//        hash: String,
//        limit: Int,
//        offset: Int
//    ): Resource<BaseResponse<Character>> {
//        runBlocking { refreshTasks() }
//        return observableTasks.map { tasks ->
//            when (tasks) {
//                is Resource.Loading -> Resource.Loading
//                is Error -> Error("ERROR")
//                is Resource.Success -> {
//                    Resource.Success(task)
//                }
//            }
//        }
//    }
//
//    override suspend fun getCharacterDetails(
//        characterId: Int,
//        ts: String,
//        apikey: String,
//        hash: String
//    ): Resource<BaseResponse<Character>> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getCharacterComics(
//        characterId: Int,
//        ts: String,
//        apikey: String,
//        hash: String
//    ): Resource<BaseResponse<Comics>> {
//        TODO("Not yet implemented")
//    }
//}