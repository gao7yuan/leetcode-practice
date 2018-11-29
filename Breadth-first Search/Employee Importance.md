# 690. Employee Importance
11/28/18
*Easy*

You are given a data structure of employee information, which includes the employee's unique id, his importance value and his direct subordinates' id.

For example, employee 1 is the leader of employee 2, and employee 2 is the leader of employee 3. They have importance value 15, 10 and 5, respectively. Then employee 1 has a data structure like [1, 15, [2]], and employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []]. Note that although employee 3 is also a subordinate of employee 1, the relationship is not direct.

Now given the employee information of a company, and an employee id, you need to return the total importance value of this employee and all his subordinates.

Example 1:
```
Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
Output: 11
Explanation:
Employee 1 has importance value 5, and he has two direct subordinates: employee 2 and employee 3. They both have importance value 3. So the total importance value of employee 1 is 5 + 3 + 3 = 11.
```
Note:
One employee has at most one direct leader and may have several subordinates.
The maximum number of employees won't exceed 2000.

## Attempts
* BFS or DFS
* Use a map: id -> employee
* BFS:
```Java
public int getImportance(List<Employee> employees, int id) {
    Map<Integer, Employee> map = new HashMap<>();
    for (Employee employee : employees) {
        map.put(employee.id, employee);
    }
    int val = 0;
    Queue<Integer> queue = new LinkedList<>();
    queue.add(id);
    while (!queue.isEmpty()) {
        Employee leader = map.get(queue.remove());
        val += leader.importance;
        for (Integer subid : leader.subordinates) {
            queue.add(subid);
        }
    }
    return val;
}
```
* DFS (recursion)
```Java
Map<Integer, Employee> map;
public int getImportance(List<Employee> employees, int id) {
    map = new HashMap<>();
    for (Employee emp : employees) {
        map.put(emp.id, emp);
    }
    return dfs(id);
}

private int dfs(int eid) {
    Employee emp = map.get(eid);
    int val = emp.importance; // add importance of root
    for (Integer sub : emp.subordinates) {
        val += dfs(sub); // recursively call dfs on children
    }
    return val;
}
```
