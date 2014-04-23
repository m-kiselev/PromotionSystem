Ext.define('app.store.IndividClients', {
    extend: 'Ext.data.Store',
    storeId: 'indclientstore',
    model: 'app.model.IndividClient',
    autoLoad: false,
    autoSync : false,
    remoteSort: true,
    remoteFilter: true,
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