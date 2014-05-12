Ext.define('app.view.ContractForm', {
    extend: 'Ext.window.Window',
    alias : 'widget.contractform',
    id: 'contractForm',
    title : 'Новый договор',
    modal: true,
    bodyPadding:5,
    layout: 'fit',
    width: 450,
    items: [{
        xtype: 'form',
        border:0,
        monitorValid: true,
        cls:'promsystem-bg',
        layout: {
            type:'vbox',
            padding:'5',
            align:'stretch'
        },
        items: [
            {xtype: 'hidden',     name: 'id', value:0},
            {xtype: 'textfield',  name: 'number',      fieldLabel: 'Номер',  allowBlank:false, afterLabelTextTpl: markFieldRequired},
            {xtype: 'checkbox',  name: 'approved',      fieldLabel: 'Одобрено'},
            {xtype: 'datefield', name: 'approvedDate',   fieldLabel: 'Дата одобрения', format:'d.m.Y', submitFormat: 'm/d/Y'},
            {xtype: 'combo', store: 'EnumContractStatus', fieldLabel: 'Статус', name: 'status',
                valueField: 'id',
                displayField: 'name',
                triggerAction: 'all',
                editable: false,
                allowBlank:false, afterLabelTextTpl: markFieldRequired},
            {xtype: 'textfield',  name: 'comment',      fieldLabel: 'Коментарий'},

            // manager fields
            {xtype: 'hidden', name: 'monthPlanNumber'},
            {   xtype: 'combo', 
                name : 'managerId',
                fieldLabel: 'Менеджер',
                displayField: 'fio',
                valueField: 'id',
                store: 'Managers',
                allowBlank:false, afterLabelTextTpl: markFieldRequired,
                editable: false,
                listeners: {
                    'afterrender': function() {
                        var store = this.getStore();
                        store.pageSize = 100;
                        store.load();
                    },
                    'change': function() {
                        this.up('form').down('hidden[name=monthPlanNumber]').setValue(this.getRawValue());
                    }
                }
            },

            // client fields
            {xtype: 'hidden',     name: 'clientId'},
            {xtype: 'hidden',     name: 'clientIsIndivid'},
            {xtype: 'panel',
                //border: 0,
                layout: { type: 'hbox', padding:'5',align:'stretch'}, cls:'promsystem-bg', items: [
                {xtype: 'textfield',  name: 'clientName', fieldLabel: 'Клиент', width: 380,
                    readOnly: true,
                    allowBlank:false, afterLabelTextTpl: markFieldRequired},
                {xtype: 'button', text: '...', handler: openClientWindow}
            ]}
        ],
        buttons: [{text: 'Сохранить',action: 'save', formBind:true}]
    }]
});

function openClientWindow(btn) {
    var selectClientWindow = Ext.create('app.view.SelectClientWindow');
    var view = selectClientWindow.down('clientpanel');
    reconfigureStoreInClientPanel(view);
    selectClientWindow.show();
}