Ext.define('app.store.ContractReportRecords', {
    extend: 'Ext.data.Store',
    storeId: 'contractreportstore',
    model: 'app.model.ContractReportRecord',
    autoLoad: false,
    autoSync : false,
    remoteSort: true,
    remoteFilter: true,
    proxy: {
        type: 'ajax',
        url:'report/contracts',
        extraParams:{date: Ext.Date.format(new Date(), 'd.m.Y')},
        headers: {Accept:'application/json'},
        batchActions: false,
        reader: {type:'json',root:'entities'},
        writer: {type:'plainwriter'}
    }
});