Ext.application({
    name: 'AM',
    appFolder: 'app',

    launch: function() {
    Ext.create('Ext.container.Viewport', {
    	
        layout: {
            type: 'border',
            padding: 5
        },

        items: [{
            region: 'north',
            xtype: 'toolbar',
            height: 35,
            items: [{
                    xtype: 'tbtext',
                    style: "font-weight:bold;",
                    text: 'Система учета поощрений'
            }, {
                    xtype: 'tbfill'
            }]
        },{
            region: 'west',
            collapsible: true,
            title: 'Меню',
            split: true,
            width: '30%',
            minWidth: 100,
            minHeight: 140,
            width: 200,
            layout: {
                type:'vbox',
                padding:'5',
                align:'stretch'
            },
            defaults:{margin:'0 0 5 0'},
            // TODO: переделать меню, сделать ссылки 
            items: [{
            	xtype: 'button',
//                	text: 'Информация о сотрудниках',
            	text: 'Менеджеры',
            	handler: openManagerPanel
            }, {
            	xtype: 'button',
            	text: 'Руководители отдела',
                handler: openHeadDepsPanel
            }, {
            	xtype: 'button',
            	text: 'Список договоров',
            	handler: openContractsPanel
            }]
        },{
            region: 'center',
            plain: true,
            name: 'InfoPanel',
            layout: 'anchor'
        }]
       });
   }
});

function openManagerPanel(btn) {
	
}

function openHeadDeps(btn) {
	
}

function openContractsPanel(btn) {
	
}