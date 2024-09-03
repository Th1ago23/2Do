package com.javaproject.webtodo.services;

import com.javaproject.webtodo.models.Task;
import com.javaproject.webtodo.models.User;
import com.javaproject.webtodo.repository.TaskRepository;
import com.javaproject.webtodo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public Task createTaskForUser(Long userId, Task task) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found: " + userId));

        task.setUser(user);
        return taskRepository.save(task);
    }

    public List<Task> getTasksForUser(Long userId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found: " + userId));

        return taskRepository.findByUser(user);
    }

    public Task updateTask(Long userId, Long taskId, Task taskDetails) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found: " + userId));

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new Exception("Task not found: " + taskId));

        if (!task.getUser().getId().equals(userId)) {
            throw new Exception("Task doesnt belong this user: " + userId);
        }

        task.setDescription(taskDetails.getDescription());
        task.setDueDate(taskDetails.getDueDate());

        return taskRepository.save(task);
    }

    public void deleteTask(Long userId, Long taskId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found: " + userId));

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new Exception("Task not found: " + taskId));

        if (!task.getUser().getId().equals(userId)) {
            throw new Exception("Task doesnt belong this user: " + userId);
        }

        taskRepository.delete(task);
    }

    public Task markTaskAsCompleted(Long userId, Long taskId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found: " + userId));

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new Exception("Task not found: " + taskId));

        if (!task.getUser().getId().equals(userId)) {
            throw new Exception("Task doesnt belong this user: " + userId);
        }

        return taskRepository.save(task);
    }
}
