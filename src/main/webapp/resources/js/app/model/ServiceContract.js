Ext.define('app.model.ServiceContract', {
    extend   : 'Ext.data.Model',
    fields : [
          {type: 'int',    name: 'id'},
          {type: 'string', name: 'number'},
          {type: 'date',   name: 'approvedDate'},
          {type: 'string', name: 'complexName'},
          {                name: 'serviceType'},
          {type: 'int',    name: 'serviceCount'},
          {type: 'int',    name: 'usedServiceCount'},
          {type: 'int',    name: 'coast'},
          {type: 'boolean',name: 'status'},
          {type: 'string', name: 'managerName'}
    ],
    idProperty: 'id'
});