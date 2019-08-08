Ext.define('Cms.view.report.EmployeeReport', {
    extend: 'Ext.panel.Panel',
    
    height: 420,
    width: '100%',
    layout: {
        type: 'border'
    },
    //title: 'Create Designation
    iconCls: 'greenBallIcon',
    employeeId: '0',
    teamId: '0',
    
    getEmployeeId: function(){
      return this.employeeId;  
    },
    
    setEmployeeId: function(employeeId){
      this.employeeId = employeeId;  
    },
    
    getTeamId: function(){
        return this.teamId;
    },
    
    setTeamId: function(teamId){
        this.teamId = teamId;
    },
    
    
    createTeamTree : function(memberStore){
        var tree = Ext.create('Ext.tree.Panel', {
            id:'teamTree',
            rootVisible:false,
            lines:false,
            height: 460,
            width: 260,
            split:true,
            autoScroll:true,
            useArrows:true,
            store: memberStore,
            viewConfig: {
                plugins: {
                    ptype: 'treeviewdragdrop'
                }
            },
            listeners: {
                itemmousedown: function(view,rec,item,index,eventObj){
                    /*
                    this.employeeId = 0;
                    this.teamId = 0;
                    var data = rec.get('id');
                    if(rec.isLeaf() == true){
                        var employeeId = data.substring(2, data.length);
                        setEmployeeId(employeeId);
                    }
                    if(rec.isLeaf() == false){
                        setTeamId(data);
                    }
                    //Ext.Msg.alert(data, this.employeeId+" | "+this.teamId);
                    */
                }
            }
        });
        return tree;
    },

    initComponent: function() {
        var me = this;
        var memberStore = Ext.create('Cms.data.store.TeamMemberStore',{
        });
        
        var stagesStore = Ext.create('Cms.data.store.PipelineStageStore',{
        });
        
        var resumeStore = Ext.create('Cms.data.store.ResumeStore',{
            proxy: {
                type: 'ajax',
                url: 'pipeline/pipelinedByDate',
                reader: {
                    type: 'json',
                    root: 'data'
                }
            }
        });
        
        var pipelineHistoryStore = Ext.create('Cms.data.store.PipelineHistoryStore',{
        });
        
        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'panel',
                    bodyPadding: 10,
                    collapsible: true,
                    title: 'Teams & Members',
                    titleCollapse: true,
                    width: 280,
                    region: 'west',
                    split: true,
                    items: [
                        me.createTeamTree(memberStore)
                    ],
                    dockedItems: [
                        {
                            xtype: 'toolbar',
                            dock: 'top',
                            items: [
                                {
                                   xtype: 'tbfill'  
                                },
                                '-',
                                {
                                    xtype: 'button',
                                    text: 'Refresh',
                                    iconCls: 'tableRefreshIcon',
                                    handler: function(){
                                           //var store = Ext.getStore('teamMemberStore');
                                           memberStore.load();
                                    }
                                }
                            ]
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    title: 'Search Criteria',
                    region: 'center',
                    items: [
                        {
                            xtype: 'form',
                            //title: 'Report',
                            height: 130,
                            //padding: 10,
                            border: false,
                            items: [
                                {
                                    xtype: 'panel',
                                    padding: 10,
                                    border: false,
                                    items: [
                                        {
                                            xtype: 'datefield',
                                            name: 'fromDate',
                                            fieldLabel: 'From Date',
                                            format: 'Y-m-d'
                                        },
                                        {
                                            xtype: 'datefield',
                                            name: 'toDate',
                                            fieldLabel: 'To Date',
                                            format: 'Y-m-d'
                                        },
                                        {
                                            xtype: 'combo',
                                            fieldLabel: 'Select Stage',
                                            name: 'stageId',
                                            store: stagesStore,
                                            displayField: 'name',
                                            valueField: 'id',
                                            triggerAction: 'all',
                                            editable: false
                                        }
                                    ]
                                }
                            ],
                            dockedItems: [
                                {
                                    xtype: 'toolbar',
                                    dock: 'bottom',
                                    layout: {
                                        align: 'middle',
                                        pack: 'start',
                                        type: 'hbox'
                                    },
                                    items: [
                                        {
                                            text: 'Graphical View',
                                            handler: function(){
                                               var form = this.up('form').getForm();
                                               var tree = Ext.getCmp('teamTree');
                                               var arraySelected = tree.getSelectionModel().getSelection();
                                               var rec = arraySelected[0];
                                               var data = rec.get('id');
                                                if(rec.isLeaf() == true){
                                                    var employeeId = data.substring(2, data.length);
                                                    me.setTeamId('0');
                                                    me.employeeId = employeeId;
                                                }
                                                if(rec.isLeaf() == false){
                                                    me.setEmployeeId('0')
                                                    me.teamId = data;
                                                }
                                                var component = Ext.create('Cms.chart.pipeline.EmployeeWisePipelinedChart',{
                                                    fromDate: form.findField('fromDate').getRawValue(),
                                                    toDate: form.findField('toDate').getRawValue(),
                                                    employeeId: me.getEmployeeId(),
                                                    teamId: me.getTeamId()
                                                });
                                                
                                                createWindow(component, 'Grahpical View', 600, 600);
                                            }
                                        },
                                        '-',
                                        {
                                          xtype: 'tbfill'  
                                        },
                                        {
                                            xtype: 'button',
                                            text: 'Show',
                                            iconCls: 'tableRefreshIcon',
                                            handler: function(){
                                                   var tree = Ext.getCmp('teamTree');
                                                   var arraySelected = tree.getSelectionModel().getSelection();
                                                   var rec = arraySelected[0];
                                                   var data = rec.get('id');
                                                    if(rec.isLeaf() == true){
                                                        var employeeId = data.substring(2, data.length);
                                                        me.setTeamId('0');
                                                        me.employeeId = employeeId;
                                                    }
                                                    if(rec.isLeaf() == false){
                                                        me.setEmployeeId('0')
                                                        me.teamId = data;
                                                    }
                                                   var form = this.up('form').getForm();
                                                   var stageId =  form.findField('stageId').getValue();
                                                   resumeStore.proxy.extraParams = {
                                                      fromDate: form.findField('fromDate').getRawValue(),
                                                      toDate: form.findField('toDate').getRawValue(),
                                                      employeeId: me.employeeId,
                                                      teamId: me.teamId,
                                                      stageId: stageId
                                                   }
                                                   resumeStore.load();
                                            }
                                        },
                                        '-',
                                        {
                                            xtype: 'button',
                                            text: 'Reset',
                                            handler: function(){
                                                var form = this.up('form').getForm();
                                                form.reset();
                                            }
                                        }
                                     ]
                                }
                            ]
                        },
                        {
                            xtype: 'panel',
                            //title: 'Result',
                            items: [
                                {
                                    xtype: 'gridpanel',
                                    title: 'Pipelined Resumes',
                                    store: resumeStore,
                                    columnLines: true,
                                    collapsible:true,
                                    animCollapse: true,
                                    columns: [
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
                                            width: 145,
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
                                }
                            ]
                        }
                        
                    ]
                },
                {
                    xtype: 'panel',
                    title: 'Pipeline History',
                    region: 'east',
                    split: true,
                    width: 300,
                    collapsible: true,
                    animCollapse: true,
                    items:[
                        {
                            xtype: 'gridpanel',
                            store: pipelineHistoryStore,
                            columnLines: true,
                            autoScroll: true,
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
                    ]
                }
                
            ]
        });

        me.callParent(arguments);
    }
});