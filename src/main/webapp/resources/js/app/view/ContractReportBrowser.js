Ext.define('app.view.ContractReportBrowser' ,{
    extend: 'Ext.grid.Panel',
    alias: 'widget.contractreportbrowser',
    closable: true,
    title: 'Отчет по договорам',
    store: 'ContractReportRecords',
    tbar:[{
        xtype: 'monthfield',
        editable: false, value: new Date(), submitFormat: 'Y-m-d', format: 'F, Y',
        name: 'period', width: 220, fieldLabel: 'Отчетный период', labelWidth: 105,
        listeners: {
            'change': function() {
                reconfigureContractReportBrowser(this.up('grid'));
            }
        }
    }],
    bbar: {
        xtype:'pagingtoolbar',
        store: 'ContractReportRecords',
        displayInfo: true
    },
    columns: [
        {text: 'ФИО менеджера', dataIndex:'fio',     sortable: false,flex:1},
        {text: 'Номер',         dataIndex:'number',  sortable: false,flex:1},
        {text: 'Одобрено',      dataIndex:'approved',width : 70, renderer: boolRenderer},
        {text: 'Коментарий',    dataIndex:'comment', sortable: false,flex:1},
        {text: 'Сумма договора',dataIndex:'summ',    flex:1}
    ]
});

function reconfigureContractReportBrowser(view) {
    var store = Ext.create('app.store.ContractReportRecords');

    var date = view.down('monthfield[name=period]').getValue();
    store.proxy.extraParams = {'date': Ext.Date.format(date, 'd.m.Y')};
    store.load();

    view.reconfigure(store);
    view.down('pagingtoolbar').bindStore(store);
}