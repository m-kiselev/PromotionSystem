Ext.define('app.store.SalaryReportRecords', {
    extend: 'Ext.data.Store',
    storeId: 'salaryreportstore',
    model: 'app.model.SalaryReportRecord',
    autoLoad: false,
    autoSync : false,
    remoteSort: true,
    remoteFilter: true,
    proxy: {
        type: 'ajax',
        url:'report/salary',
        extraParams:{date: Ext.Date.format(new Date(), 'd.m.Y')},
        headers: {Accept:'application/json'},
        batchActions: false,
        reader: {type:'json',root:'entities'},
        writer: {type:'plainwriter'}
    }
});