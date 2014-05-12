Ext.define('app.model.MonthPlan', {
    extend   : 'Ext.data.Model',
    fields : [
          {type: 'int',   name: 'id'},
          {type: 'string',name: 'number'},
          {type: 'int',   name: 'sumVIP'},
          {type: 'int',   name: 'sumStandart'},
          {type: 'int',   name: 'countVIP'},
          {type: 'int',   name: 'countStandart'}
    ],
    idProperty: 'id',
    proxy: {
        type: 'ajax',
        api:{
            read:'monthplan/list',
            update:'monthplan/edit',
        }, 
        headers: {Accept:'application/json'},
        batchActions: false,
        reader: {type:'json',root:'entities'},
        writer: {type:'plainwriter'}
    }
});