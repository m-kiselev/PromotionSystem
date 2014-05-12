Ext.define('app.store.MonthPlans', {
    extend: 'Ext.data.Store',
    storeId: 'monthplanstore',
    model: 'app.model.MonthPlan',
    autoLoad: false,
    autoSync : false,
    remoteSort: true,
    remoteFilter: true,
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