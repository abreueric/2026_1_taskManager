package br.com.senac.tofudidonopi.taskManager.datasourcerepositories;
import br.com.senac.tofudidonopi.taskManager.domain.entities.Task;
import br.com.senac.tofudidonopi.taskManager.domain.entities.TaskPriority;
import br.com.senac.tofudidonopi.taskManager.domain.entities.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TaskRepository extends JpaRepository<Task, Long> {
    public List<Task> findAllOrderByPriorityDueDateAsc(TaskPriority priority);
    public List<Task> findByStatusOrderByDueDateAsc(TaskStatus status);
}
