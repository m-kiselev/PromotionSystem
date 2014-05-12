Ext.define('app.model.Contract', {
    extend   : 'Ext.data.Model',
    fields : [
          {type: 'int',    name: 'id'},
          {type: 'string', name: 'number'},
          {type: 'boolean',name: 'approved'},
          {type: 'date',   name: 'approvedDate'},
          {type: 'string', name: 'comment'},

          {type: 'int',    name: 'managerId'},
          {type: 'string', name: 'managerName'},

          {type: 'int',    name: 'clientId'},
          {type: 'boolean',name: 'clientIsIndivid'},
          {type: 'string', name: 'clientName'},
          {type: 'string', name: 'status'}
    ],
    idProperty: 'id'
});