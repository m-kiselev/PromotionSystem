Ext.define('app.model.IndividClient', {
    extend   : 'Ext.data.Model',
    fields : [
          {type: 'int',    name: 'id'},
          {type: 'string', name: 'fio'},
          {type: 'string', name: 'passportData'},
          {type: 'string', name: 'adress'},
          {type: 'string', name: 'additionInfo'},
          {type: 'string', name: 'phone'}
    ],
    idProperty: 'id',
    proxy: {
        type: 'ajax',
        api:{
            read:'iclient/list',
            update:'iclient/edit',
        }, 
        headers: {Accept:'application/json'},
        batchActions: false,
        reader: {type:'json',root:'entities'},
        writer: {type:'plainwriter'}
    }
});