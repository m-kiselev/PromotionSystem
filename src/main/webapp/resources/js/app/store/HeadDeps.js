Ext.define('app.store.HeadDeps', {
    extend: 'Ext.data.Store',
    storeId: 'headdepstore',
    model: 'app.model.HeadDep',
    autoLoad: false,
    autoSync : false,
    remoteSort: true,
    remoteFilter: true,
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