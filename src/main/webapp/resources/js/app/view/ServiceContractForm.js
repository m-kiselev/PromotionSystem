Ext.define('app.view.ServiceContractForm', {
    extend: 'Ext.window.Window',
    alias : 'widget.servicecontractform',
    id: 'serviceContractForm',
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
            {xtype: 'hidden',    name: 'id', value:0},
            {xtype: 'textfield', name: 'number',      fieldLabel: 'Номер',  allowBlank:false, afterLabelTextTpl: markFieldRequired},
            {xtype: 'datefield', name: 'approvedDate',fieldLabel: 'Дата одобрения', format:'d.m.Y', submitFormat: 'm/d/Y'
                ,  allowBlank:false, afterLabelTextTpl: markFieldRequired},
            {xtype: 'numberfield',name: 'coast',       fieldLabel: 'Сумма договора'},
            {xtype: 'textfield',  name: 'complexName', fieldLabel: 'Наименование комплекса'},
            {xtype: 'combo', store: 'EnumServiceType', fieldLabel: 'Тип услуги', name: 'serviceType',
                valueField: 'id',
                displayField: 'name',
                triggerAction: 'all',
                editable: false,
                allowBlank:false, afterLabelTextTpl: markFieldRequired},
            {xtype: 'numberfield',name: 'serviceCount',     fieldLabel: 'Кол-во услуг'},
//            {xtype: 'numberfield',name: 'usedServiceCount', fieldLabel: 'Кол-во использован. услуг'},
            {xtype: 'checkbox',   name: 'status',           fieldLabel: 'Оплачено'},
            {xtype: 'textfield',  name: 'managerName',      fieldLabel: 'Менеджер'},
        ],
        buttons: [{text: 'Сохранить',action: 'save', formBind:true}]
    }]
});