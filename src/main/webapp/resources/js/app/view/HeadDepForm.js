Ext.define('app.view.HeadDepForm', {
    extend: 'Ext.window.Window',
    alias : 'widget.headdepform',
    title : 'Новый сотрудник',
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
            {xtype: 'textfield',  name: 'fio',         fieldLabel: 'ФИО',    allowBlank:false, afterLabelTextTpl: markFieldRequired},
            {xtype: 'textfield',  name: 'phone',       fieldLabel: 'Телефон'},
            {xtype: 'textfield',  name: 'passportData',fieldLabel: 'Паспортные данные'},
            {xtype: 'textfield',  name: 'adress',      fieldLabel: 'Адрес'},
            
            {xtype: 'hidden', name: 'monthPlanNumber'},
            {   xtype: 'combo', 
                name : 'monthPlanId',
                fieldLabel: 'План',
                displayField: 'number',
                valueField: 'id',
                store: 'MonthPlans',
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
        ],
        buttons: [{text: 'Сохранить',action: 'save', formBind:true}]
    }]
});