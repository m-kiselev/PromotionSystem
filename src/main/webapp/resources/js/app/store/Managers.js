Ext.define('app.store.Managers', {
    extend: 'Ext.data.Store',
    storeId: 'managerstore',
    model: 'app.model.Manager',
    autoLoad: false,
    autoSync : false,
    remoteSort: true,
    remoteFilter: true,
    proxy: {
        type: 'ajax',
        api:{
            read:'manager/list',
            update:'manager/edit',
        }, 
        headers: {Accept:'application/json'},
        batchActions: false,
        reader: {type:'json',root:'entities'},
        writer: {type:'plainwriter'}
    }
});