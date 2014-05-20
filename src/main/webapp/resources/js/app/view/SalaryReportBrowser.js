Ext.define('app.view.SalaryReportBrowser' ,{
    extend: 'Ext.grid.Panel',
    alias: 'widget.salaryreportbrowser',
    closable: true,
    title: 'Отчет по ЗП',
    store: 'SalaryReportRecords',
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
        store: 'SalaryReportRecords',
        displayInfo: true,
        items:[ '-', { xtype:'button',text:'cкачать XLS', action:'xlsexport',   tooltip: 'cкачать XLS'/*, icon: 'img/file_xls.png'*/}]
    },
    columns: [
        {text: 'ФИО менеджера',              dataIndex: 'fio',        sortable: false,flex:1},
        {text: 'Зарплата',         dataIndex: 'salary',     sortable: false,flex:1},
        {text: 'Кол-во раб. часов',dataIndex: 'hourOfDay',  sortable: false,flex:1},
        {text: 'Зарплата за месяц',dataIndex: 'salaryOfMonth',sortable: false,flex:1},
        {text: 'Премия',           dataIndex: 'bonus',      sortable: false,flex:1},
        {text: 'Итого',            dataIndex: 'summ',       sortable: false,flex:1}
    ]
});