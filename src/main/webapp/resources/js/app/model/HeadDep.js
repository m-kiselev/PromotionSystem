Ext.define('app.model.HeadDep', {
    extend   : 'Ext.data.Model',
    fields : [
          {type: 'int',    name: 'id'},
          {type: 'string', name: 'number'},
          {type: 'string', name: 'fio'},
          {type: 'string', name: 'phone'},
          {type: 'string', name: 'passportData'},
          {type: 'string', name: 'adress'},
//          {type: 'int',    name: 'hourOfDay'},
//          {type: 'int',    name: 'salary'}
    ],
    idProperty: 'id',
    proxy: {
        type: 'ajax',
        api:{
            read:'headdep/list',
            update:'headdep/edit',
        }, 
        headers: {Accept:'application/json'},
        batchActions: false,
        reader: {type:'json',root:'entities'},
        writer: {type:'plainwriter'}
    }
});