Ext.define('app.view.HeadDepBrowser' ,{
    extend: 'Ext.grid.Panel',
    alias: 'widget.headdepbrowser',
    closable: true,
    title: 'Руководители отделов',
    store: 'HeadDeps',
    tbar:[{
        xtype: 'button',
        text: 'Добавить',
        handler: function() {
            var view = Ext.widget('headdepform');
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
                    title:'Удалить работника?',
                    msg: 'Вы уверены что хотите удалить?',
                    buttons: Ext.Msg.YESNO,
                    buttonText:{yes:'Да', no:'Нет'},
                    icon: Ext.Msg.QUESTION,
                    fn: function(btn) {
                        if (btn === 'yes') {
                            var delRec = recs[0];
                            ajaxSave({id: delRec.data.id},
                            '/headdep/delete',
                            function(resp) {
                                grid.getStore().reload();
                            });
                        }
                    }
                });
            } else {
                Ext.Msg.alert('Инфо', 'Необходимо выбрать работника.');
            }
        }
    }],
    bbar: {
        xtype:'pagingtoolbar',
        store: 'HeadDeps',
        displayInfo: true
    },
    columns: [
        {text: 'Номер',            dataIndex: 'number',    sortable: false,flex:1},
        {text: 'ФИО',              dataIndex: 'fio',       sortable: false,flex:1},
        {text: 'Телефон',          dataIndex: 'phone',     sortable: false,flex:1},
        {text: 'Паспортные данные',dataIndex: 'passportData',sortable: false, flex:1},
        {text: 'Адрес',            dataIndex: 'adress',    sortable: false, flex:1},
        {text: 'План на месяц',    dataIndex: 'monthPlanNumber', sortable: false, flex:1}
    ]
});