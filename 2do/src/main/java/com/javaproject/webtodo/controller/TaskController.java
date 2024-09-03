package com.javaproject.webtodo.controller;

import com.javaproject.webtodo.models.Task;
import com.javaproject.webtodo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
    @RestController
    @RequestMapping("/tasks")
    public class TaskController {

        @Autowired
        private TaskService taskService;

        @PostMapping("/user/{userId}")
        public ResponseEntity<Task> createTask(@PathVariable Long userId, @RequestBody Task task) throws Exception {
            Task createdTask = taskService.createTaskForUser(userId, task);
            return ResponseEntity.ok(createdTask);
        }

        @GetMapping("/user/{userId}")
        public ResponseEntity<List<Task>> getTasks(@PathVariable Long userId) throws Exception {
            List<Task> tasks = taskService.getTasksForUser(userId);
            return ResponseEntity.ok(tasks);
        }


        @PutMapping("/user/{userId}/task/{taskId}")
        public ResponseEntity<Task> updateTask(
                @PathVariable Long userId,
                @PathVariable Long taskId,
                @RequestBody Task taskDetails) throws Exception {
            Task updatedTask = taskService.updateTask(userId, taskId, taskDetails);
            return ResponseEntity.ok(updatedTask);
        }

        @DeleteMapping("/user/{userId}/task/{taskId}")
        public ResponseEntity<Void> deleteTask(
                @PathVariable Long userId,
                @PathVariable Long taskId) throws Exception {
            taskService.deleteTask(userId, taskId);
            return ResponseEntity.noContent().build();
        }

        @PatchMapping("/user/{userId}/task/{taskId}/complete")
        public ResponseEntity<Task> markTaskAsCompleted(
                @PathVariable Long userId,
                @PathVariable Long taskId) throws Exception {
            Task completedTask = taskService.markTaskAsCompleted(userId, taskId);
            return ResponseEntity.ok(completedTask);
        }
    }

