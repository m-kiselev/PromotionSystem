Ext.define('app.store.Contracts', {
    extend: 'Ext.data.Store',
    storeId: 'contractstore',
    model: 'app.model.Contract',
    autoLoad: false,
    autoSync : false,
    remoteSort: true,
    remoteFilter: true,
    proxy: {
        type: 'ajax',
        api:{
            read:'contracts'
        }, 
        headers: {Accept:'application/json'},
        batchActions: false,
        reader: {type:'json',root:'entities'},
        writer: {type:'plainwriter'}
    }
});