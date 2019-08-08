Ext.define('Cms.view.opening.OpeningByEmployee', {
    extend: 'Ext.panel.Panel',

    autoHeight: true,
    width: '100%',
    layout: {
        type: 'border'
    },
    title: 'Opening',
    
    createWindow: function(extComponent, title, height, width){
        extComponent.region = 'center';
        var win;
        if(!win){
            win = Ext.create('widget.window',{
                title: title,
                layout: 'border',
                closeAction: 'destroy',
                items:[extComponent],
                autoHeight: true,
                width: width,
                modal: false,
                //minimizable: true,
                maximizable: true,
                closable:true,
                collapsible:true,
                animCollapse: true,
                //maximized: true,
                //y: 20,
                listeners: {
                    maximize: function(){
                        var viewportHeight = Ext.getCmp('mainViewport').height;
                        win.setHeight(viewportHeight-32);
                        win.doLayout();
                    }
                }
            })
            win.show(this, function(){

            });
        }
    },
    
    showOpeningDetails: function(openingId, details){
        var data = {
            openingId: openingId,
            details : details
        }
        var panel = Ext.getCmp('openingDetailsPanel');
        panel.removeAll(true);
        var tpl = Ext.create('Ext.Template', 
            '<br>'+
            '<p style="margin-left: 40px;">'+    
                '<b>Opening Id:</b> {openingId}'+
            '</p>'+    
            '<div style="margin-left: 40px; margin-right: 40px; margin-top: 20px; margin-bottom: 40px;">{details}</div>'
        )
        tpl.overwrite(panel.body, data);
        panel.doComponentLayout();
    },
    
    initComponent: function() {
        
        var openingStore = Ext.create('Cms.data.store.OpeningByEmployeeStore',{
            });
        
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'panel',
                    region: 'center',
                    //title: 'Opening List',
                    autoScroll: true,
                    split: true,
                    layout:'border',
                    items: [
                        {
                            xtype: 'gridpanel',
                            id: 'TLOpeningGrid',
                            title: 'Opening List',
                            store: openingStore,
                            collapsible: true,
                            animCollapse: true,
                            collapsed: false,
                            collapseDirection: 'top',
                            autoHeight: true,
                            columnLines: true,
                            enableDragDrop: true,
                            region: 'north',
                            split: true,
                            columns: [
                                {
                                    xtype: 'templatecolumn',
                                    dataIndex: 'positionName',
                                    text: 'Opening',
                                    width: 160,
                                    tpl: 'Opening Id: {openingId}<br>'+
                                         '<b>{positionName}</b><img src="../images/{openingStatusId}.jpg" /><br>'+
                                         'Exp.: {minimumExp} - {maximumExp} Yrs <br>'
                                },
                                {
                                    xtype: 'templatecolumn',
                                    dataIndex: 'minimumExp',
                                    text: 'Details',
                                    width: 250,
                                    tpl: '<b>{clientName}</b><br>'+
                                         '{location}<br>'+
                                         'Salary: {minimumSalary} - {maximumSalary}<br>'+
                                         '{keySkill}<br>'+
                                         '{qualificationName}'
                                }   
                            ],
                            viewConfig: {
                                plugins: {
                                    ddGroup: 'openingDDGroup',
                                    ptype: 'gridviewdragdrop',
                                    enableDrop: false
                                }
                            },
                            listeners: {
                                selectionchange: function(model, records){
                                    var grid = Ext.getCmp('TLOpeningGrid');
                                    var arraySelected =grid.getSelectionModel().getSelection();
                                    var record = arraySelected[0];
                                    var openingId = record.get('openingId');
                                    var clientId = record.get('clientId');
                                    var details = record.get('openingDetails');
                                    Ext.Ajax.request({
                                       method: 'GET',
                                       url: 'opening/getDetails?openingId='+openingId,
                                       waitMsg: 'Processing...',
                                       success: function(result, request){
                                           var data = result.responseText;
                                           var jsonData = Ext.JSON.decode(data);
                                           me.showOpeningDetails(openingId, jsonData.data);
                                       },
                                       failure: function(result, request){
                                           Ext.Msg.alert('Alert', 'failure');
                                       }
                                    });
                                }
                            },
                            dockedItems: [
                                    {
                                        xtype: 'toolbar',
                                        dock: 'top',
                                        items: [
                                            {
                                                xtype: 'button',
                                                text: 'Refresh',
                                                iconCls: 'tableRefreshIcon',
                                                handler: function(){
                                                    openingStore.load(function(records, operation, success) {
                                                        console.log('Employee created and loaded in list.');
                                                    }); 
                                                }
                                            }
                                        ]
                                    }
                                ]
                        },
                        {
                            xtype: 'panel',
                            id: 'openingDetailsPanel',
                            title: 'Opening Details',
                            autoScroll: true,
                            collapsible: true,
                            animCollapse: true,
                            region: 'center',
                            split: true
                        }
                    ]
                }
            ]
        });
        me.callParent(arguments);
    }
});