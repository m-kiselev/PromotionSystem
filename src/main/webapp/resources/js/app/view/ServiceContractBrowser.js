Ext.define('app.view.ServiceContractBrowser' ,{
    extend: 'Ext.grid.Panel',
    alias: 'widget.servicebrowser',
    closable: true,
    title: 'Договора на услуги',
    store: 'ServiceContracts',
    tbar:[{
        xtype: 'button',
        text: 'Добавить',
        handler: function() {
            var view = Ext.widget('servicecontractform');
            view.show();
        }
    }, {
        xtype: 'button',
        text: 'Удалить',
        handler: function(button) {
            var grid = button.up('grid');
            var recs = grid.getSelectionModel().getSelection();
            if (recs.length > 0) {
                Ext.Msg.show({
                    title:'Удалить контракт?',
                    msg: 'Вы уверены что хотите удалить?',
                    buttons: Ext.Msg.YESNO,
                    buttonText:{yes:'Да', no:'Нет'},
                    icon: Ext.Msg.QUESTION,
                    fn: function(btn) {
                        if (btn === 'yes') {
                            var delRec = recs[0];
                            ajaxSave({id: delRec.data.id},
                            '/servicecontract/delete',
                            function(resp) {
                                grid.getStore().reload();
                            });
                        }
                    }
                });
            } else {
                Ext.Msg.alert('Инфо', 'Необходимо выбрать контракт.');
            }
        }
    }],
    bbar: {
        xtype:'pagingtoolbar',
        store: 'ServiceContracts',
        displayInfo: true
    },
    columns: [
        {text: 'Номер',                 dataIndex: 'number',      sortable: false,flex:1},
        {text: 'Дата одобрения',        dataIndex: 'approvedDate',sortable: false,flex:1, renderer: myDateRenderer},
        {text: 'Стоимость',             dataIndex: 'coast',       sortable: false,flex:1},
        {text: 'Наименование комплекса',dataIndex: 'complexName', sortable: false,flex:1},
        {text: 'Тип услуги',            dataIndex: 'serviceType', sortable: false,flex:1, renderer: serviceTypeRender},
        {text: 'Кол-во услуг',          dataIndex : 'serviceCount',        sortable: false,flex:1},
        {text: 'Кол-во использован. услуг', dataIndex: 'usedServiceCount', sortable: false,flex:1},
        {text: 'Оплаено',  dataIndex : 'status', width : 70, renderer: boolRenderer},
        {text: 'Менеджер', dataIndex: 'managerName', sortable: false, flex:1}
    ]
});