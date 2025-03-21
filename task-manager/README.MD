1. Display all tasks: `tm list` </br>
2. Display overdue tasks: `tm list --overdue`
3. Display all tasks with a query parameter: `tm list --query name="dummy name"` </br>
4. Display all subtasks related tasks with a query parameter:`tm list --subtask --query name="dummy name"` </br>
5. Perform _insert_|_update_|_delete_ operations: </br>
   `tm create --body name="Test Task" description="Test Description" category="CRITICAL"` </br>
   `tm update --query name="Test Task" --body description="Test Description" category="CRITICAL"` </br>
   `tm delete --query name="del"`  </br>
6. Perform _insert_|_update_|_delete_ all subtasks of a given task: **SKIPPED due to its complexity.** </br>
7. Support full-text search by word in task description: **SKIPPED due to its complexity.** </br>
8. Support full-text search by a sub-task name: **SKIPPED due to its complexity.** </br>

The code became too complex and large.