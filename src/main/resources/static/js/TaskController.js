'use strict';

angular.module('TODOApp').controller('TaskController',
    ['TaskService', '$scope',  function( TaskService, $scope) {
        $scope.parent = {checkOut:''};
        var self = this;
        self.task = {};
        self.tasks = [];

        self.submit = submit;
        self.getAllTasks = getAllTasks;
        self.createTask = createTask;
        self.updateTask = updateTask;
        self.removeTask = removeTask;
        self.editTask = editTask;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        function submit() {
            console.log('Submitting');
            self.task.date = moment(self.task.date).format('YYYY-MMM-DD hh:mm a');
            if (self.task.id === undefined || self.task.id === null) {
                console.log('Saving New Task', self.task);
                createTask(self.task);
            } else {
                updateTask(self.task, self.task.id);
                console.log('Task updated with id ', self.task.id);
                reset();
            }
        }

        function createTask(task) {
            console.log('About to create task');
            TaskService.createTask(task)
                .then(
                    function (response) {
                        console.log('Task created successfully');
                        self.successMessage = 'Task created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.task={};
                        $scope.taskForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating task');
                        self.errorMessage = 'Error while creating task: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateTask(task, id){
            console.log('About to update task');
            TaskService.updateTask(task, id)
                .then(
                    function (response){
                        console.log('Task updated successfully');
                        self.successMessage='Task updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.taskForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating task');
                        self.errorMessage='Error while updating task '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeTask(id){
            console.log('About to remove task with id '+id);
            TaskService.removeTask(id)
                .then(
                    function(response){
                        console.log('Task ' +id + ' removed successfully');
                        self.successMessage = '';
                        self.errorMessage='Task removed successfully';
                        self.done = true;
                    },
                    function(errResponse){
                        console.error('Error while removing task '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllTasks(){
            return TaskService.getAllTasks();
        }

        function editTask(id) {
            self.successMessage='';
            self.errorMessage='';
            TaskService.getTask(id).then(
                function (task) {
                    self.task = task;
                },
                function (errResponse) {
                    console.error('Error while editing task ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.task={};
            $("#datepicker1").setValue(new Date());
            $scope.taskForm.$setPristine(); //reset Form
        }
    }
    ]);