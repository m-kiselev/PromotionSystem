Ext.define('app.model.ContractReportRecord', {
    extend   : 'Ext.data.Model',
    fields : [
          {type: 'int',    name: 'id'},
          {type: 'string', name: 'fio'},
          {type: 'string', name: 'number'},
          {type: 'boolean',name: 'approved'},
          {type: 'string', name: 'comment'},
          {type: 'int',    name: 'summ'}
    ],
    idProperty: 'id'
});