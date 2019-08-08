Ext.define('Cms.view.resume.ResumeSearch', {
    extend: 'Ext.panel.Panel',

    height: 550,
    //width: '100%',
    layout: {
        type: 'border'
    },
    
    showComponent: function(component){
        component.closable = true;
        
        var tabContainer = Ext.getCmp('resumeBoardPanel');
        tabContainer.add(component);
        tabContainer.setActiveTab(component);
    },
    
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
                modal: true,
                closable:true,
                listeners: {
                    maximize: function(){
                        var viewportHeight = Ext.getCmp('userMainViewport').height;
                        //Ext.Msg.alert('Message',viewportHeight);
                        win.setHeight(viewportHeight);
                        win.doLayout();
                    }
                }
            })
            win.show(this, function(){

            });
        }
    },
    getActionClass: function (val, meta, rec) {
        if (rec.get('contentImage') === 'WORD_ICON') {
            this.tooltip = 'Content Not Available';
            return 'WORD_ICON';
        }
        else {
            this.tooltip = 'Content Available';
            return 'BLANK_ICON';
        }
    },

    initComponent: function() {
       
        
        var branchStore = Ext.create('Cms.data.store.BranchStore',{
        });
        
        var resumeStore = Ext.create('Cms.data.store.ResumeStore',{
            
        });
        
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'panel',
                    id: 'basicSearchPanel',
                    height: 140,
                    //width: '100%',
                    region: 'north',
                    title: 'Search Resume',
                    split: true,
                    collapsible:true,
                    animCollapse: true,
                    items: [
                        {
                            xtype: 'form',
                            bodyPadding: 10,
                            width: '100%',
                            items: [
                                {
                                    xtype: 'fieldcontainer',
                                    height: 30,
                                    margin: '',
                                    padding: 5,
                                    layout: {
                                        type: 'table'
                                    },
                                    items: [
                                        {
                                            xtype: 'combobox',
                                            id: 'branchCombo',
                                            fieldLabel: 'Select Branch',
                                            labelAlign: 'right',
                                            width: 300,
                                            anchor: '100%',
                                            store: branchStore,
                                            triggerAction: 'all',
                                            displayField: 'branchName',
                                            valueField: 'branchId',
                                            editable: false
                                        }
                                    ]
                                },
                                {
                                    xtype: 'textfield',
                                    labelWidth: 105,
                                    fieldLabel: 'Search Key',
                                    id: 'searchKeyTF',
                                    name: 'searchKey',
                                    labelAlign: 'right',
                                    anchor: '100%'
                                }
                            ]
                        }
                    ],
                    dockedItems: [
                        {
                            xtype: 'toolbar',
                            dock: 'bottom',
                            layout: {
                                pack: 'start',
                                type: 'hbox'
                            },
                            items: [
                                {
                                    xtype: 'tbfill'
                                },
                                {
                                    xtype: 'button',
                                    text: 'Search',
                                    //iconCls: 'searchIcon',
                                    handler: function(){
                                        var searchKey = Ext.getCmp('searchKeyTF').value;
                                        var branchId = Ext.getCmp('branchCombo').value;
                                        resumeStore.load({
                                            params:{
                                                searchKey: searchKey,
                                                branchId: branchId
                                            }
                                        });
                                    }
                                },
                                '-',
                                {
                                    xtype: 'button',
                                    text: 'Reset',
                                    handler: function(){
                                    }
                                }
                            ]
                        }
                    ]
                },
                {
                    xtype: 'gridpanel',
                    //title: 'Client List',
                    store: resumeStore,
                    region: 'center',
                    //height: 300,
                    columnLines: true,
                    selModel: Ext.create('Ext.selection.CheckboxModel',{
                        listeners:{ 
                            selectionchange: function(selectionModel, selected, options){ 
                                // Bunch of code to update store 
                            } 
                        } 
                    }),
                    columns: [
                            {
                            xtype: 'actioncolumn',
                            width: 30,
                            text: '',
                            align: 'center',
                            items:[
                                    {
                                    //icon   : '../images/{contentImage}.jpg',
                                    tooltip: 'View Word Document',
                                    getClass: this.getActionClass,
                                    handler: function(grid, rowIndex, colIndex){
                                                
                                                var record = grid.getStore().getAt(rowIndex);
                                                var resumeId = record.get('id');
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
                                                   url: 'resume/showResume?resumeId='+resumeId,
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
                            xtype: 'templatecolumn',
                            dataIndex: 'id',
                            text: 'Resume Id',
                            tpl: '{id}'
                        },
                        {
                            xtype: 'templatecolumn',
                            dataIndex: 'name',
                            text: 'Name',
                            width: 190,
                            tpl: '<b>{name}</b> ({experience} Yrs.)<br>'+
                                 '{contactNo}<br>'+
                                 '{email}<br>'

                        },
                        {
                            xtype: 'templatecolumn',
                            dataIndex: 'createdByName',
                            Name: 'Created By',
                            width: 130,
                            tpl: '{createdByName}'+
                                 '</br>'+
                                 '({createdAt})'
                        },
                        {
                            xtype: 'actioncolumn',
                            width: 30,
                            text: '',
                            align: 'center',
                            items:[
                                    {
                                    icon   : '../images/upload.jpg',
                                    tooltip: 'Upload Document',
                                    handler: function(grid, rowIndex, colIndex){
                                                
                                                var record = grid.getStore().getAt(rowIndex);
                                                var resumeId = record.get('id');
                                                var companyId = record.get('companyId');
                                                var branchId = record.get('branchId');
                                                var name = record.get('name');
                                                var remarks = record.get('remarks');
                                                var component = Ext.create('Cms.view.resume.UploadDocument',{
                                                    resumeId: resumeId
                                                });
                                                
                                                me.createWindow(component, 'Resume Id: '+resumeId, 500, 570);
                                            }
                                    }
                            ]
                        }
                    ],
                    viewConfig: {

                    },
                    listeners: {
                        selectionchange: function(model, records){
                            if(records[0]){
                                Ext.getCmp('uploadButton').setDisabled(false);
                                if(Ext.getCmp('candidateInfoForm') != null){
                                    Ext.getCmp('candidateInfoForm').getForm().loadRecord(records[0]);
                                    var record = records[0];
                                }
                            }
                        }
                    },
                    dockedItems: [
                        {
                            xtype: 'toolbar',
                            dock: 'bottom',
                            layout: {
                                pack: 'start',
                                type: 'hbox'
                            },
                            items: [
                                {
                                    xtype: 'tbfill'
                                },
                                {
                                    xtype: 'button',
                                    text: 'Send Email to Candidates',
                                    iconCls: 'pipelineProcessingIcon',
                                    handler: function(){
                                        var grid = this.up('gridpanel');
                                        var records = grid.selModel.getSelection();
                                        
                                        
                                        var email = ''
                                        Ext.each(records, function (record) {
                                            email = email+","+record.get('email');
                                        }); 
                                        email = email.substring(1, email.length);
                                        //Ext.Msg.alert('Message', email);
                                        
                                        var component = Ext.create('Cms.view.massmail.MassMail',{
                                             emailIds: email
                                        });
                                                
                                        me.createWindow(component, 'Mass Email to Candidates', 750, 750);
                                   }
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