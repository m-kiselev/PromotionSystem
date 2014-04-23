Ext.define('app.view.LegalClientForm', {
    extend: 'Ext.window.Window',
    alias : 'widget.legalclientform',
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
            {xtype: 'textfield',  name: 'fio',    fieldLabel: 'ФИО',    allowBlank:false, afterLabelTextTpl: markFieldRequired},
            {xtype: 'textfield',  name: 'details',fieldLabel: 'Реквизиты'},
            {xtype: 'textfield',  name: 'name',   fieldLabel: 'Наименование'},
            {xtype: 'textfield',  name: 'adress', fieldLabel: 'Юр. адрес'},
            {xtype: 'textfield',  name: 'phone',  fieldLabel: 'Телефон'}
        ],
        buttons: [{text: 'Сохранить',action: 'save', formBind:true}]
    }]
});