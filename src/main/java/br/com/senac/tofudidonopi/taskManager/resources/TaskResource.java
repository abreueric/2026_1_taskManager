package br.com.senac.tofudidonopi.taskManager.resources;

import br.com.senac.tofudidonopi.taskManager.domain.entities.Task;
import br.com.senac.tofudidonopi.taskManager.domain.entities.TaskPriority;
import br.com.senac.tofudidonopi.taskManager.domain.entities.TaskStatus;
import br.com.senac.tofudidonopi.taskManager.resources.dtos.TaskDTO;
import br.com.senac.tofudidonopi.taskManager.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskResource {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> findAll() {
        List<TaskDTO> list = taskService.findAll()
                .stream()
                .map(TaskDTO::fromEntity)
                .toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> fetchById(@PathVariable Long id){
        Optional<Task> taskOp = taskService.findById(id);

        return taskOp.map(task ->
                ResponseEntity.ok(TaskDTO.fromEntity(task))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TaskDTO> save(@RequestBody TaskDTO taskDTO){
        Task task = taskService.save(TaskDTO.fromDTO(taskDTO));
        return ResponseEntity.ok(TaskDTO.fromEntity(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> update(@PathVariable Long id,
                                          @RequestBody TaskDTO dto) {
        if (!taskService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Task task = TaskDTO.fromDTO(dto);
        task.setId(id);

        Task updated = taskService.update(task);
        return ResponseEntity.ok(TaskDTO.fromEntity(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!taskService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

