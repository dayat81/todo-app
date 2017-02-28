<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Tasks</span></div>
        <div class="panel-body">
            <div class="formcontainer">
                <div class="alert alert-success" role="alert" ng-if="taskCtrl.successMessage">{{taskCtrl.successMessage}}</div>
                <div class="alert alert-danger" role="alert" ng-if="taskCtrl.errorMessage">{{taskCtrl.errorMessage}}</div>
                <form ng-submit="taskCtrl.submit()" name="taskForm" class="form-horizontal">
                    <input type="hidden" ng-model="taskCtrl.task.id" />
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-label" for="date">Date:</label>
                            <div class="dropdown col-md-7">
                                <a class="dropdown-toggle" id="dropdown2" role="button" data-toggle="dropdown" data-target="#" href="#">
                                    <div class="input-group">
                                        <input data-date-time-input="YYYY-MMM-DD hh:mm a" data-date-parse-strict="true" data-date-formats="['YYYY-MMM-DD hh:mm a']"  type="text" class="form-control" data-ng-model="taskCtrl.task.date">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                                    </div>
                                </a>
                                <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                    <datetimepicker data-ng-model="taskCtrl.task.date" data-datetimepicker-config="{ dropdownSelector: '#dropdown2' }"/>
                                </ul>
                            </div>


                        </div>
                    </div>



                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-label" for="description">Description:</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="taskCtrl.task.description" id="description" class="form-control input-sm" required ng-minlength="1" placeholder="Description..."/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-actions floatRight">
                            <input type="submit"  value="{{!taskCtrl.task.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="taskForm.$invalid">
                            <button type="button" ng-click="taskCtrl.reset()" class="btn btn-warning btn-sm" ng-disabled="taskForm.$pristine">Reset Form</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">List of tasks </span></div>
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th font-size="18">Date</th>
                        <th>Description</th>
                        <th width="50"></th>
                        <th width="50"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="tk in taskCtrl.getAllTasks()">
                        <td>{{tk.id}}</td>
                        <td>{{tk.date}}</td>
                        <td>{{tk.description}}</td>
                        <td><button type="button" ng-click="taskCtrl.editTask(tk.id)" class="btn btn-success btn-xs">Edit</button></td>
                        <td><button type="button" ng-click="taskCtrl.removeTask(tk.id)" class="btn btn-danger btn-xs">Remove</button></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

