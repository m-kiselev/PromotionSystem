Ext.define('app.store.EnumContractStatus', {
    extend: 'Ext.data.Store',
    storeId: 'enumcontractstatus',
    fields: ['id', 'name'],
    data: [
        {id: 'STANDART','name':'Стандарт'},
        {id: 'VIP',     'name':'VIP'},
    ]
});