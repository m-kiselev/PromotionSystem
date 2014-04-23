Ext.define('app.store.LegalClients', {
    extend: 'Ext.data.Store',
    storeId: 'indclientstore',
    model: 'app.model.LegalClient',
    autoLoad: false,
    autoSync : false,
    remoteSort: true,
    remoteFilter: true,
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