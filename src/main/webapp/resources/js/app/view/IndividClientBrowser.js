Ext.define('app.view.IndividClientBrowser' ,{
    extend: 'Ext.grid.Panel',
    alias: 'widget.indclientbrowser',
    store: 'IndividClients',
    bbar: {
        xtype:'pagingtoolbar',
        store: 'IndividClients',
        displayInfo: true
    },
    columns: [
        {text: 'id',               dataIndex: 'id',          sortable: false,flex:1},
        {text: 'ФИО',              dataIndex: 'fio',         sortable: false,flex:1},
        {text: 'Паспортные данные',dataIndex: 'passportData',sortable: false, flex:1},
        {text: 'Место работы',     dataIndex: 'adress',      sortable: false, flex:1},
        {text: 'Доп. инфо',        dataIndex: 'additionInfo',sortable: false, flex:1},
        {text: 'Телефон',          dataIndex: 'phone',       sortable: false,flex:1}
    ]
});