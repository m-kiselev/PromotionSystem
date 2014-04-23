Ext.define('app.view.HeadDepBrowser' ,{
    extend: 'Ext.grid.Panel',
    alias: 'widget.headdepbrowser',
    closable: true,
    title: 'Руководители отделов',
    store: 'HeadDeps',
//    features: [{
//        ftype: 'filters',
//        encode: true
//    }],
    tbar:[{
        xtype: 'button',
        text: 'Добавить',
        handler: function() {
            var view = Ext.widget('headdepform');
            var form = view.down('form');
//            addCommonFieldsToGenerateReportField(form, true, true);
//            form.getForm().findField("detailed").setValue(true);
//            form.getForm().findField("finance").setValue(true);
//            view.setTitle("Новый Финансовый Отчет");
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
                            '/headDep/delete',
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
//        {text: 'Кол-во раб. часов',dataIndex: 'hourOfDay', sortable: false,flex:1},
//        {text: 'Зарплата',         dataIndex: 'salary',    sortable: false,flex:1},
        {text: 'Паспортные данные',dataIndex: 'passportData',sortable: false, flex:1},
        {text: 'Адрес',            dataIndex: 'adress',    sortable: false, flex:1}
    ]
});