# User Guide

## Features 

### Feature 1 
* Creates a list of task for user to keep track

1. todo: adds task todo
2. deadline: adds task deadline with dates
3. event: adds task event with date
4. list: prints out list of tasks
5. find: find tasks with matched description
6. done: set task as done
7. bye: end task

## Usage

### `todo` - adds task todo

Example of usage: 

`todo lunch`

Expected outcome:

`Got it. I've added this task: 
[T][✘] lunch
Now you have 1 tasks in the list.`

### `deadline` - adds task deadline with dates

Example of usage: 

`deadline sleep by 10`

Expected outcome:

`Got it. I've added this task: 
[D][✘] sleep -by: 10
Now you have 2 tasks in the list.`

### `event` - adds task event with date

Example of usage: 

`event exercise at 5pm`

Expected outcome:

`Got it. I've added this task: 
[E][✘] exercise -at: 5pm
Now you have 3 tasks in the list.`

### `list` - prints out list of tasks

Example of usage: 

`list`

Expected outcome:

`1.[T][✘] lunch
2.[D][✘] sleep -by: 10
3.[E][✘] exercise -at: 5pm`

### `find` - find tasks with matched description

Example of usage: 

`find sleep`

Expected outcome:

`Here are the matching tasks in your list:
1.[D][✘] sleep -by: 10
End of search`

### done`- set task as done

Example of usage: 

`done 3`

### `bye` - end task

Example of usage: 

`bye`

Expected outcome:

`Bye. Hope to see you again soon!`