Ext.define('app.view.SelectClientWindow' ,{
    extend: 'Ext.window.Window',
    alias: 'widget.selectclientwindow',
    title : 'Выбор клиента',
    modal : true,
    width : 700,
    height: 400,
    x: 10,
    y: 10,
    bodyPadding : 5,
    layout : 'fit',
    autoShow : true,
    items: [{
        xtype : 'clientpanel',
        id: 'windowclientpanel',
        height : 250,
        isDialog: true,
        closable: false
    }],
    buttons : [ {
        text : 'Выбрать',
        listeners : {
            click : function(button) {
                var rec = button.up('window').down('grid').getSelectionModel().getSelection();
                if (rec.length > 0) {
                    setClientToContractForm(this.up('window').down('clientpanel'), rec[0].data);
                    button.up('window').close();
                } else {
                    Ext.Msg.alert("Инфо", "Выберите клиента");
                }
            }
        }
    } ]
});

function setClientToContractForm(clientPanel, rec) {
//    console.log(rec);
    var isIndivid = true;
    var clientName;
    var contractForm = Ext.getCmp('contractForm');
    if (clientPanel.down('radiogroup').getValue().rb_client == 1) {
        clientName = rec.fio;
    } else {
        clientName = rec.name;
        isIndivid = false;
    }

    contractForm.down('hidden[name=clientIsIndivid]').setValue(isIndivid);
    contractForm.down('hidden[name=clientId]').setValue(rec.id);
    contractForm.down('textfield[name=clientName]').setValue(clientName);
}