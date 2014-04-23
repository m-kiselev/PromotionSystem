Ext.define('app.view.LegalClientBrowser' ,{
    extend: 'Ext.grid.Panel',
    alias: 'widget.legalclientbrowser',
    store: 'LegalClients',
    bbar: {
        xtype:'pagingtoolbar',
        store: 'LegalClients',
        displayInfo: true
    },
    columns: [
        {text: 'id',           dataIndex: 'id',     sortable: false, flex:1},
        {text: 'ФИО контакт.', dataIndex: 'fio',    sortable: false, flex:1},
        {text: 'Реквизиты',    dataIndex: 'details',sortable: false, flex:1},
        {text: 'Наименование', dataIndex: 'name',   sortable: false, flex:1},
        {text: 'Юр. адрес',    dataIndex: 'adress', sortable: false, flex:1},
        {text: 'Телефон',      dataIndex: 'phone',  sortable: false, flex:1}
    ]
});