Ext.define('app.view.MonthPlanForm', {
    extend: 'Ext.window.Window',
    alias : 'widget.monthplanform',
    title : 'Новый план',
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
            {xtype: 'textfield',  name: 'number',       fieldLabel: 'Номер',            allowBlank:false, afterLabelTextTpl: markFieldRequired},
            {xtype: 'numberfield',name: 'sumVIP',       fieldLabel: 'Сумма по VIP',     allowBlank:false, afterLabelTextTpl: markFieldRequired},
            {xtype: 'numberfield',name: 'sumStandart',  fieldLabel: 'Сумма по STANDART',allowBlank:false, afterLabelTextTpl: markFieldRequired},
            {xtype: 'numberfield',name: 'countVIP',     fieldLabel: 'Кол-во VIP',       allowBlank:false, afterLabelTextTpl: markFieldRequired},
            {xtype: 'numberfield',name: 'countStandart',fieldLabel: 'Кол-во STANDART',  allowBlank:false, afterLabelTextTpl: markFieldRequired}
        ],
        buttons: [{text: 'Сохранить',action: 'save', formBind:true}]
    }]
});