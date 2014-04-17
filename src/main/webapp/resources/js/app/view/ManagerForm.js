Ext.define('app.view.ManagerForm', {
    extend: 'Ext.window.Window',
    alias : 'widget.managerform',
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
            {xtype: 'numberfield',name: 'hourOfDay',   fieldLabel: 'Кол-во раб. часов',allowBlank:false, afterLabelTextTpl: markFieldRequired},
            {xtype: 'numberfield',name: 'salary',      fieldLabel: 'Зарплата',         allowBlank:false, afterLabelTextTpl: markFieldRequired},
            {xtype: 'textfield',  name: 'phone',       fieldLabel: 'Телефон'},
            {xtype: 'textfield',  name: 'passportData',fieldLabel: 'Паспортные данные'},
            {xtype: 'textfield',  name: 'adress',      fieldLabel: 'Адрес'}
        ],
        buttons: [{text: 'Сохранить',action: 'save', formBind:true}]
    }]
});