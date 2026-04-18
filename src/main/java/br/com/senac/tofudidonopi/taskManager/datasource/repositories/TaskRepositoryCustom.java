package br.com.senac.tofudidonopi.taskManager.datasource.repositories;
import br.com.senac.tofudidonopi.taskManager.domain.entities.Task;
import br.com.senac.tofudidonopi.taskManager.domain.entities.TaskPriority;
import br.com.senac.tofudidonopi.taskManager.domain.entities.TaskStatus;

import java.util.Date;
import java.util.List;

public interface TaskRepositoryCustom {
List<Task> findByTitleCriteria(String title);

List<Task> findAdvancedCriteria(
    TaskStatus status,
    TaskPriority priority,
    Date startDate,
    Date endDate
    );

}
