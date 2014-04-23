Ext.define('app.view.IndividClientForm', {
    extend: 'Ext.window.Window',
    alias : 'widget.indclientform',
    title : 'Новый клиент',
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
            {xtype: 'textfield',  name: 'fio',         fieldLabel: 'ФИО',    allowBlank:false, afterLabelTextTpl: markFieldRequired},
            {xtype: 'textfield',  name: 'passportData',fieldLabel: 'Паспортные данные'},
            {xtype: 'textfield',  name: 'adress',      fieldLabel: 'Место работы'},
            {xtype: 'textfield',  name: 'additionInfo',fieldLabel: 'Доп. инфо'},
            {xtype: 'textfield',  name: 'phone',       fieldLabel: 'Телефон'}
        ],
        buttons: [{text: 'Сохранить',action: 'save', formBind:true}]
    }]
});