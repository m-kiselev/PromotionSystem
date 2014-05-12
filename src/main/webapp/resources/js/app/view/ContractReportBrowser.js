Ext.define('app.view.ContractReportBrowser' ,{
    extend: 'Ext.grid.Panel',
    alias: 'widget.contractreportbrowser',
    closable: true,
    title: 'Отчет по договорам',
    store: 'HeadDeps',
    tbar:[{
        xtype: 'monthfield',
        editable: false, value: new Date(), submitFormat: 'Y-m-d', format: 'F, Y',
        name: 'period', width: 220, fieldLabel: 'Отчетный период', labelWidth: 105,
        listeners: {
            'change': function() {
//                performContractBrowserFilters(this.up('contractbrowser'));
            }
        }
    }],
    bbar: {
        xtype:'pagingtoolbar',
        store: 'HeadDeps',
        displayInfo: true
    },
    columns: [
        {text: 'ФИО',       dataIndex: 'fio',    sortable: false,flex:1},
        {text: 'Номер',     dataIndex: 'number', sortable: false,flex:1},
        {text: 'Статус',    dataIndex: 'status', sortable: false,flex:1},
        {text: 'Коментарий',dataIndex: 'comment',sortable: false,flex:1}
    ]
});