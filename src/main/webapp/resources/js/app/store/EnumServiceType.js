Ext.define('app.store.EnumServiceType', {
    extend: 'Ext.data.Store',
    storeId: 'enumservicetype',
    fields: ['id', 'name'],
    data: [
        {id: 'WATERPOOL', 'name':'Бассейн'},
        {id: 'SPORTHALL', 'name':'Спорт зал'},
        {id: 'FITNESHALL','name':'Фитнес зал'},
        {id: 'PAINTBALL', 'name':'Пейнтбол'},
        {id: 'SAUNA',     'name':'Сауна'},
        {id: 'MASSAGE',   'name':'Массажный кабинет'}
    ]
});