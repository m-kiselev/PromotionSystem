Ext.define('app.view.MonthPlanBrowser' ,{
    extend: 'Ext.grid.Panel',
    alias: 'widget.monthplanbrowser',
    closable: true,
    title: 'Планы на месяц',
    store: 'MonthPlans',
    tbar:[{
        xtype: 'button',
        text: 'Добавить',
        handler: function() {
            var view = Ext.widget('monthplanform');
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
                    title:'Удалить план?',
                    msg: 'Вы уверены что хотите удалить?',
                    buttons: Ext.Msg.YESNO,
                    buttonText:{yes:'Да', no:'Нет'},
                    icon: Ext.Msg.QUESTION,
                    fn: function(btn) {
                        if (btn === 'yes') {
                            var delRec = recs[0];
                            ajaxSave({id: delRec.data.id},
                            '/monthplan/delete',
                            function(resp) {
                                grid.getStore().reload();
                            });
                        }
                    }
                });
            } else {
                Ext.Msg.alert('Инфо', 'Необходимо выбрать план.');
            }
        }
    }],
    bbar: {
        xtype:'pagingtoolbar',
        store: 'MonthPlans',
        displayInfo: true
    },
    columns: [
        {text: 'Номер',            dataIndex: 'number',       sortable: false,flex:1},
        {text: 'Сумма по VIP',     dataIndex: 'sumVIP',       sortable: false,flex:1},
        {text: 'Сумма по STANDART',dataIndex: 'sumStandart',  sortable: false,flex:1},
        {text: 'Кол-во VIP',       dataIndex: 'countVIP',     sortable: false,flex:1},
        {text: 'Кол-во STANDART',  dataIndex: 'countStandart',sortable: false,flex:1}
    ]
});