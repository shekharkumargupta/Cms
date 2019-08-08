Ext.define('Cms.view.massmail.MassMailStatus', {
    extend: 'Ext.panel.Panel',

    height: 400,
    width: '100%',
    layout: {
        type: 'border'
    },
    //title: 'My Panel',
    
    showComponent: function(component){
        component.closable = true;
        
        var tabContainer = Ext.getCmp('candidateResumePanel');
        tabContainer.add(component);
        tabContainer.setActiveTab(component);
    },
    

    initComponent: function() {
        
        var clientStore = Ext.create('Cms.data.store.ClientStore',{
        });
        
        var openingStore = Ext.create('Cms.data.store.OpeningStore',{
        });
        
        var massMailStore = Ext.create('Cms.data.store.MassMailStore',{
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
        
                
        
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'tabpanel',
                    //title: 'Center Panel',
                    id: 'candidateResumePanel',
                    region: 'center',
                    layout: 'border',
                    items: [
                        {
                            xtype: 'gridpanel',
                            title: 'Mass Mailed Candidate',
                            store: massMailStore,
                            columnLines: true,
                            collapsible:true,
                            animCollapse: true,
                            region: 'center',
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
                                            icon   : '../images/word.jpg',
                                            tooltip: 'View Word Document',
                                            handler: function(grid, rowIndex, colIndex){
                                                
                                                    var record = grid.getStore().getAt(rowIndex);
                                                    var id = record.get('id');
                                                    var emailId = record.get('email');
                                                    var resumeId = record.get('resumeId');
                                                    var companyId = record.get('companyId');
                                                    var branchId = record.get('branchId');
                                                    var name = record.get('name');
                                                    var remarks = record.get('remarks');
                                                    var component = Ext.create('Cms.view.resume.ResumeView',{
                                                        resumeId: resumeId,
                                                        branchId: branchId,
                                                        companyId: companyId
                                                    });
                                                    component.setTitle("("+resumeId+") "+name);
                                                    /*
                                                    component.setCompanyId(companyId);
                                                    component.setBranchId(branchId);
                                                    component.setResumeId(resumeId);
                                                    */
                                                    Ext.Ajax.request({
                                                       method: 'GET',
                                                       url: 'resume/showResumeByEmailId?emailId='+emailId,
                                                       success: function(result, request){
                                                           var responseData = result.responseText;
                                                           var jsonData = Ext.JSON.decode(responseData); 
                                                           component.setDetails(resumeId, companyId, branchId, jsonData.data, remarks);
                                                       },
                                                       failure: function(){
                                                           Ext.Msg.alert('Alert','Failure!');
                                                       }
                                                    });
                                                    me.showComponent(component);
                                                }
                                            }
                                    ]
                                },
                                {
                                    xtype: 'gridcolumn',
                                    text: 'Name',
                                    dataIndex: 'name'
                                },
                                {
                                    xtype: 'templatecolumn',
                                    dataIndex: 'id',
                                    text: 'Mass Mail Id',
                                    tpl: '{id}',
                                    width: 70,
                                    hidden: true
                                },
                                {
                                    xtype: 'templatecolumn',
                                    dataIndex: 'name',
                                    text: 'Name',
                                    width: 200,
                                    tpl:  '{email}'

                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'status',
                                    text: 'Status'
                                },
                                {
                                    xtype: 'templatecolumn',
                                    text: 'Sender',
                                    dataIndex: 'senderName',
                                    width: 140,
                                    tpl: '<b>{senderName}</b>'
                                },
                                {
                                    xtype: 'templatecolumn',
                                    text: 'Sent At',
                                    dataIndex: 'openingId',
                                    width: 140,
                                    tpl: '{sentAt}'
                                }
                            ],
                            viewConfig: {

                            },
                            listeners: {
                                selectionchange: function(model, records){
                                    if(records[0]){
                                        var record = records[0];
                                    }
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
                                    massMailStore.load({
                                        //url: 'clientContact/contactList',
                                        params: {
                                            openingId: openingId,
                                            senderId: '0'
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