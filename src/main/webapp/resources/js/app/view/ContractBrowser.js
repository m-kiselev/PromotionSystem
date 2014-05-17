Ext.define('app.view.ContractBrowser' ,{
    extend: 'Ext.grid.Panel',
    alias: 'widget.contractbrowser',
    closable: true,
    title: 'Договора',
//    store: 'Contracts',
    store:'ext-empty-store',/*to be redefined in constructor*/
    tbar:[{
        xtype: 'button',
        text: 'Добавить',
        handler: function() {
            var view = Ext.widget('contractform');
            view.show();
        }
    }, '-', {
        xtype: 'button',
        text: 'Удалить',
        handler: function(button) {
            var grid = button.up('grid');
            var recs = grid.getSelectionModel().getSelection();
            if (recs.length > 0) {
                Ext.Msg.show({
                    title:'Удалить договор?',
                    msg: 'Вы уверены что хотите удалить?',
                    buttons: Ext.Msg.YESNO,
                    buttonText:{yes:'Да', no:'Нет'},
                    icon: Ext.Msg.QUESTION,
                    fn: function(btn) {
                        if (btn === 'yes') {
                            var delRec = recs[0];
                            ajaxSave({id: delRec.data.id},
                            '/contract/delete',
                            function(resp) {
                                grid.getStore().reload();
                            });
                        }
                    }
                });
            } else {
                Ext.Msg.alert('Инфо', 'Необходимо выбрать договор.');
            }
        }
    }, '-',
    {
        xtype: 'combo', 
        name : 'managerId',
        fieldLabel: 'Менеджер',
        labelWidth: 60,
        displayField: 'fio',
        valueField: 'id',
        width: 170,
        store: 'Managers',
        editable: false,
        listeners: {
            'afterrender': function() {
                var store = this.getStore();
                store.pageSize = 100;
                store.load();
            },
            'change': function() {
//                this.up('form').down('hidden[name=managerName]').setValue(this.getRawValue());
                performContractBrowserFilters(this.up('contractbrowser'));
            }
        }
    },
    {xtype: 'datefield', name: 'startDate',width: 170, fieldLabel: 'Дата начала',
        labelWidth: 75, format:'d.m.Y', submitFormat: 'm/d/Y',
        value: getFirstDayCurrentMonth(),
        listeners: {
            'change': function() {
//                this.up('form').down('hidden[name=managerName]').setValue(this.getRawValue());
                performContractBrowserFilters(this.up('contractbrowser'));
            }
        }
    },
    {xtype: 'datefield', name: 'endDate',  width: 190, fieldLabel: 'Дата окончания',
        labelWidth: 95, format:'d.m.Y', submitFormat: 'm/d/Y',
        value: getLastDayCurrentMonth(),
        listeners: {
            'change': function() {
//                this.up('form').down('hidden[name=managerName]').setValue(this.getRawValue());
                performContractBrowserFilters(this.up('contractbrowser'));
            }
        }
        },
    ],
    bbar: {
        xtype:'pagingtoolbar',
//        store: 'Contracts',
        store:'ext-empty-store',/*to be redefined in constructor*/
        displayInfo: true
    },
    columns: [
        {text: 'Номер',      dataIndex: 'number',    sortable: false,flex:1},
        {text: 'Одобрено',   dataIndex : 'approved', width : 70, renderer: boolRenderer},
        {text: 'Сумма договора', dataIndex : 'summ', width : 70},
        {text: 'Дата одобрения', dataIndex: 'approvedDate',sortable: false,flex:1, renderer: myDateRenderer},
        {text: 'Статус',     dataIndex: 'status',     sortable: false, flex:1},
        {text: 'Комментарий',dataIndex: 'comment',    sortable: false, flex:1},
        {text: 'Менеджер',   dataIndex: 'managerName',sortable: false, flex:1},
        {text: 'Клиент',     dataIndex: 'clientName', sortable: false, flex:1}
    ]
});

function performContractBrowserFilters(view){
    var store = Ext.create('app.store.Contracts');

    var managerId = view.down('combo[name=managerId]').getValue();
    var startDate = Ext.Date.format(view.down('datefield[name=startDate]').getValue(), 'd.m.Y');
    var endDate   = Ext.Date.format(view.down('datefield[name=endDate]').getValue(),   'd.m.Y');
    store.proxy.extraParams={'managerId':managerId, 'startDate':startDate, 'endDate':endDate};
    store.load();

    view.reconfigure(store);
    view.down('pagingtoolbar').bindStore(store);
}
