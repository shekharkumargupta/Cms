Ext.define('Cms.view.pipeline.PipelineStatus', {
    extend: 'Ext.panel.Panel',

    height: 400,
    width: '100%',
    layout: {
        type: 'border'
    },
    //title: 'My Panel',
    
    
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
                        //Ext.Msg.alert('Message',viewportHeight);
                        win.setHeight(viewportHeight-32);
                        win.doLayout();
                    }
                }
            })
            win.show(this, function(){

            });
        }
    },

    initComponent: function() {
        
        var clientStore = Ext.create('Cms.data.store.ClientStore',{
        });
        
        var openingStore = Ext.create('Cms.data.store.OpeningStore',{
        });
        
        var contactStore = Ext.create('Cms.data.store.ContactStore',{
        });
        
        var resumeStore = Ext.create('Cms.data.store.ResumeStore',{
            proxy: {
                type: 'ajax',
                url: 'pipeline/pipelinedResumeByOpeningId',
                reader: {
                    type: 'json',
                    root: 'data'
                }
            }
        });
        
        var pipelineHistoryStore = Ext.create('Cms.data.store.PipelineHistoryStore',{
        });
        
        var clientSearch = Ext.create('Cms.view.client.ClientSearch',{
        });
        
        
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'panel',
                    //title: 'Center Panel',
                    region: 'center',
                    layout: 'border',
                    items: [
                        {
                            xtype: 'gridpanel',
                            title: 'Pipelined Resumes',
                            store: resumeStore,
                            columnLines: true,
                            collapsible:true,
                            animCollapse: true,
                            region: 'north',
                            height: 240,
                            split: true,
                            columns: [
                                {
                                    xtype: 'actioncolumn',
                                    width: 30,
                                    text: '',
                                    align: 'center',
                                    items:[
                                            {
                                            icon   : '../images/add.gif',
                                            tooltip: 'Request',
                                            handler: function(grid, rowIndex, colIndex){
                                                        var record = grid.getStore().getAt(rowIndex);
                                                        var pipelineId = record.get('pipelineId');
                                                        var resumeId = record.get('id');
                                                        var component = Ext.create('Cms.view.pipeline.PipelineHistory',{

                                                        });
                                                        component.setPipelineId(pipelineId);
                                                        me.createWindow(component, 'Pipeline Id: '+pipelineId+", Resume Id: "+resumeId, 600, 600);

                                                    }
                                            }
                                    ]
                                },
                                {
                                    xtype: 'templatecolumn',
                                    dataIndex: 'id',
                                    text: 'Resume Id',
                                    tpl: '{id}',
                                    width: 70
                                },
                                /*
                                {
                                    xtype: 'templatecolumn',
                                    dataIndex: 'pipelineId',
                                    text: 'Pipeline Id',
                                    tpl: '{pipelineId}'
                                },
                                */
                                {
                                    xtype: 'templatecolumn',
                                    dataIndex: 'name',
                                    text: 'Name',
                                    width: 200,
                                    tpl: '<b>{name}</b> ({experience} Yrs.)<br>'+
                                         '{contactNo}<br>'+
                                         '{email}<br>'

                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'pipelineStatusName',
                                    text: 'Status'
                                },
                                {
                                    xtype: 'templatecolumn',
                                    text: 'Pipelined By',
                                    dataIndex: 'createdByName',
                                    width: 140,
                                    Name: 'Pipelined By',
                                    tpl: '{createdByName}'+
                                         '</br>'+
                                         '({createdAt})'
                                }
                            ],
                            viewConfig: {

                            },
                            listeners: {
                                selectionchange: function(model, records){
                                    if(records[0]){
                                        var record = records[0];
                                        var pipelineId = record.get('pipelineId');
                                        //Ext.Msg.alert("Pipeline Id", pipelineId);
                                        pipelineHistoryStore.load({
                                            params:{
                                                pipelineId: pipelineId
                                            }
                                        });
                                    }
                                }
                            }
                        },
                        {
                            xtype: 'gridpanel',
                            title: 'Pipeline History',
                            store: pipelineHistoryStore,
                            columnLines: true,
                            collapsible: true,
                            animCollapse: true,
                            collapseDirection: 'bottom',
                            //height: 350,
                            region: 'center',
                            columns: [
                                /*
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'id',
                                    text: 'History Id'
                                },
                                */
                                {   
                                    xtype: 'templatecolumn',
                                    dataIndex: 'pipelineStatusId',
                                    text: 'Remarks',
                                    width: 470,
                                    tpl: '<table width="100%">'+
                                            '<tr>'+
                                                '<td align="left" valign="top">'+
                                                    '<b>{pipelineStatusName}</b>'+ 
                                                '</td>'+
                                                '<td align="right">'+
                                                    '<b>'+
                                                    '{createdByName}'+
                                                    '</br>'+
                                                    '{createdAt}'+
                                                    '</b>'+
                                                '</td>'+
                                             '</tr>'+
                                         '</table>'+
                                         '<div style="height: 80px; overflow:auto;">'+
                                            '{remarks}'+
                                         '</div>'   
                                }
                                /*,
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'remarks',
                                    text: 'Remarks'
                                }*/
                            ],
                            viewConfig: {

                            }
                        }
                    ],
                    dockedItems: [
                        {
                            xtype: 'toolbar',
                            dock: 'top',
                            layout: {
                                pack: 'start',
                                type: 'hbox'
                            },
                            padding: 5,
                            items: [
                                '-',
                                {
                                    xtype: 'button',
                                    text: 'Refresh',
                                    iconCls: 'tableRefreshIcon',
                                    handler: function(){
                                        var grid = Ext.getCmp('openingGrid');
                                        var arraySelected =grid.getSelectionModel().getSelection();
                                        var record = arraySelected[0];
                                        var openingId = record.get('openingId');
                                        var clientId = record.get('clientId');
                                        resumeStore.load({
                                            //url: 'clientContact/contactList',
                                            params: {
                                                openingId: openingId
                                            }
                                        });
                                    }
                                },
                                '-'
                            ]
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    width: 320,
                    title: 'Opening List',
                    collapsible: true,
                    region: 'west',
                    //layout: 'accordion',
                    split: true,
                    items: [
                        {
                            xtype: 'gridpanel',
                            //title: 'Opening List',
                            id: 'openingGrid',
                            store: openingStore,
                            autoHeight: true,
                            columnLines: true,
                            enableDragDrop: true,
                            columns: [
                                {
                                  xtype: 'templatecolumn',
                                  tpl: '<img src="../images/{openingStatusId}.jpg" />',
                                  width: 30
                                },
                                {
                                    xtype: 'templatecolumn',
                                    dataIndex: 'positionName',
                                    text: 'Opening',
                                    width: 160,
                                    tpl: 'Opening Id: {openingId}<br>'+
                                         '<b>{positionName}</b><br>'+
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
                                    var grid = Ext.getCmp('openingGrid');
                                    var arraySelected =grid.getSelectionModel().getSelection();
                                    var record = arraySelected[0];
                                    var openingId = record.get('openingId');
                                    var clientId = record.get('clientId');
                                    resumeStore.load({
                                        //url: 'clientContact/contactList',
                                        params: {
                                            openingId: openingId
                                        }
                                    });
                                }
                            },
                            dockedItems: [
                                {
                                    xtype: 'toolbar',
                                    dock: 'top',
                                    layout: {
                                        pack: 'start',
                                        type: 'hbox'
                                    },
                                    padding: 5,
                                    items: [
                                        {
                                            xtype: 'combo',
                                            fieldLabel: 'Client',
                                            name: 'clientId',
                                            labelAlign: 'left',
                                            labelWidth: 40,
                                            anchor: '100%',
                                            allowBlank: false,
                                            msgTarget: 'under',
                                            blankText: 'Must select a client',
                                            store: clientStore,
                                            valueField: 'clientId',
                                            displayField: 'clientName',
                                            triggerAction: 'all',
                                            editable: false,
                                            emptyText: 'Select Client',
                                            listeners: {
                                                select: function(combo, rec, index){
                                                   /* 
                                                   var positionCombo = Ext.getCmp('positionCombo') ;
                                                   positionCombo.setDisabled(false);
                                                   positionCombo.clearValue();
                                                   positionStore.proxy.extraParams = {
                                                       categoryId: combo.getValue()
                                                   }
                                                   positionStore.load();
                                                   */
                                                   Ext.getStore('openingStore').load({
                                                        params: {
                                                            clientId: combo.getValue()
                                                        }
                                                    });
                                                }
                                            }
                                        }
                                    ]
                                }
                            ]
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }
});