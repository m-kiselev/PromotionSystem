Ext.define('app.model.LegalClient', {
    extend   : 'Ext.data.Model',
    fields : [
          {type: 'int',    name: 'id'},
          {type: 'string', name: 'fio'},
          {type: 'string', name: 'details'},
          {type: 'string', name: 'name'},
          {type: 'string', name: 'adress'},
          {type: 'string', name: 'phone'}

    ],
    idProperty: 'id',
    proxy: {
        type: 'ajax',
        api:{
            read:'lclient/list',
            update:'lclient/edit',
        }, 
        headers: {Accept:'application/json'},
        batchActions: false,
        reader: {type:'json',root:'entities'},
        writer: {type:'plainwriter'}
    }
});