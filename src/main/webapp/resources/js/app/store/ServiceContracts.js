Ext.define('app.store.ServiceContracts', {
    extend: 'Ext.data.Store',
    storeId: 'servicecontractstore',
    model: 'app.model.ServiceContract',
    autoLoad: false,
    autoSync : false,
    remoteSort: true,
    remoteFilter: true,
    proxy: {
        type: 'ajax',
        api:{
            read:'servicecontract/list',
            update:'servicecontract/edit',
        }, 
        headers: {Accept:'application/json'},
        batchActions: false,
        reader: {type:'json',root:'entities'},
        writer: {type:'plainwriter'}
    }
});