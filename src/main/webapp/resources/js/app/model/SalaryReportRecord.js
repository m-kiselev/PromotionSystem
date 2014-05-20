Ext.define('app.model.SalaryReportRecord', {
    extend   : 'Ext.data.Model',
    fields : [
          {type: 'int',    name: 'id'},
          {type: 'string', name: 'fio'},
          {type: 'int',    name: 'salary'},
          {type: 'int',    name: 'hourOfDay'},
          {type: 'int',    name: 'salaryOfMonth'},
          {type: 'int',    name: 'bonus'},
          {type: 'int',    name: 'summ'},
    ],
    idProperty: 'id'
});