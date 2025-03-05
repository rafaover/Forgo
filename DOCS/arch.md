# FORGO - TASK MANAGER THAT NEVER MISSES A BEAT
# ============

Screens:
- [ ] Home
- [ ] Task List
- [ ] Task Detail
- [ ] Create Task
- [ ] Edit Task
- [ ] Delete Task (Dialog)
- [ ] Mark Task as Done (Dialog)
- [ ] Settings
- [ ] About
- [ ] Help
- [ ] Feedback
- [ ] Search (Dialog or Top Bar)

Task:
- [ ] Create a new task
- [ ] Edit a task
- [ ] Delete a task
- [ ] Mark a task as done

-- Task Model:
- _id: int
- title: str
- description: str
- done: bool
- created_at: datetime
- tags: List[str]
- priority: int
- due_date: datetime
- colour: str
- location: This is a object that will be selected by the user using google maps location picker
or by typing the location name. Will use Google maps API. What's the best way to store this in the database?