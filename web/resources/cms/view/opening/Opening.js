Ext.define('Cms.view.opening.Opening', {
    extend: 'Ext.panel.Panel',

    autoHeight: true,
    width: '100%',
    layout: {
        type: 'border'
    },
    title: 'Opening',
    openingId: '0',
    
    setOpeningId: function(openingId){
        this.openingId = openingId;
    },
    getOpeningId: function(){
        return this.openingId;
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
        
        var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
            clicksToEdit: 1
        });
        
        var salaryInLacsStore = Ext.create('Cms.data.store.SalaryInLacsStore',{
        });
        
        var salaryInThousandsStore = Ext.create('Cms.data.store.SalaryInThousandsStore',{
        });
        
        var qualificationStore = Ext.create('Cms.data.store.QualificationStore',{
        });
        
        var expInYearStore = Ext.create('Cms.data.store.ExpInYearStore',{
        });
        
        var expInMonthStore = Ext.create('Cms.data.store.ExpInMonthStore',{
        });
        
        var openingStatusStore = Ext.create('Cms.data.store.OpeningStatusStore',{
        });
        
        var openingStore = Ext.create('Cms.data.store.OpeningStore',{
        });
        
        var clientStore = Ext.create('Cms.data.store.ClientStore',{
        });
        
        var teamStore = Ext.create('Cms.data.store.TeamStore',{
        });
        
        var teamTree = Ext.create('Cms.view.team.TeamAndOpening',{
            title: 'Teams & Openings',
            collapsible: true,
            animCollapse: true,
            collapsed: true,
            collapseDirection: 'left',
            split: true,
            width: 350,
            region: 'east'
        });
        
        
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'panel',
                    region: 'west',
                    title: 'Opening Information',
                    split: true,
                    collapsible: true,
                    animCollapse: true,
                    width: 300,
                    activeTab: 0,
                    items: [
                        {
                            xtype: 'form',
                            id: 'clientForm',
                            width: 300,
                            bodyPadding: 10,
                            //title: 'Opening Info',
                            items: [
                                /*
                                {
                                    xtype: 'datefield',
                                    name: 'createdDate',
                                    fieldLabel: 'Created Date',
                                    labelAlign: 'right',
                                    labelWidth: 80
                                },
                                */
                                {
                                    xtype: 'hidden',
                                    name: 'openingId',
                                    fieldLabel: 'Opening Id',
                                    labelAlign: 'right',
                                    value: '0'
                                },
                                {
                                    xtype: 'combo',
                                    fieldLabel: 'Client',
                                    name: 'clientId',
                                    labelAlign: 'right',
                                    labelWidth: 80,
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
                                },
                                {
                                    xtype: 'textfield',
                                    name: 'positionName',
                                    fieldLabel: 'Position',
                                    labelAlign: 'right',
                                    allowBlank: false,
                                    msgTarget: 'under',
                                    labelWidth: 80,
                                    emptyText: 'Sr. Sales Manager',
                                    anchor: '100%'
                                },
                                {
                                    xtype: 'combo',
                                    name: 'qualification',
                                    fieldLabel: 'Qualification',
                                    labelAlign: 'right',
                                    allowBlank: false,
                                    msgTarget: 'under',
                                    labelWidth: 80,
                                    emptyText: 'Select Qualifications',
                                    anchor: '100%',
                                    store: qualificationStore,
                                    valueField: 'value',
                                    displayField: 'value',
                                    triggerAction: 'all',
                                    editable: true,
                                    //for autocomplete
                                    typeAhead: true,
                                    mode: 'remote',
                                    minChars:1,
                                    forceSelection: true,
                                    hideTrigger: true,
                                    //for multiselection
                                    multiSelect: true
                                },
                                {
                                    xtype: 'textfield',
                                    fieldLabel: 'Location',
                                    name: 'location',
                                    labelAlign: 'right',
                                    labelWidth: 80,
                                    anchor: '100%',
                                    allowBlank: false,
                                    emptyText: 'Delhi',
                                    msgTarget: 'under',
                                    blankText: 'Must enter the location name',
                                    minLength: 3,
                                    minLengthText: 'Location name can\'t be less than 3 character.',
                                    maxLength: 30,
                                    maxLengthText: 'Location name can\'t be greater than 30 character.'
                                },
                                {
                                    xtype: 'fieldset',
                                    title: 'Salary & Experience',
                                    collapsible: true,
                                    collapsed: false,
                                    animCollapse: true,
                                    items: [
                                        {
                                            xtype: 'fieldcontainer',
                                            height: 30,
                                            margin: '',
                                            padding: 5,
                                            anchor: '100%',
                                            /*
                                            fieldLabel: 'Min. Salary',
                                            labelWidth: 130,
                                            labelAlign: 'top',
                                            */
                                            layout: {
                                                type: 'table'
                                            },
                                            items: [
                                                {
                                                    xtype: 'displayfield',
                                                    value: 'Min. Salary: ',
                                                    width: 70
                                                },
                                                {
                                                    xtype: 'combo',
                                                    name: 'minLacs',
                                                    store: salaryInLacsStore,
                                                    valueField: 'id',
                                                    displayField: 'value',
                                                    triggerAction: 'all',
                                                    allowBlank: true,
                                                    msgTarget: 'under',
                                                    editable: false,
                                                    margin: '2,2,2,2',
                                                    emptyText: 'Lacs.',
                                                    width: 60
                                                },
                                                {
                                                    xtype: 'combo',
                                                    name: 'minThousands',
                                                    store: salaryInThousandsStore,
                                                    valueField: 'id',
                                                    displayField: 'value',
                                                    triggerAction: 'all',
                                                    allowBlank: true,
                                                    msgTarget: 'under',
                                                    editable: false,
                                                    margin: '2,2,2,2',
                                                    emptyText: 'Thousand',
                                                    width: 110
                                                }
                                            ]
                                        },
                                        {
                                            xtype: 'fieldcontainer',
                                            height: 45,
                                            margin: '',
                                            padding: 5,
                                            /*
                                            fieldLabel: 'Max. Salary',
                                            labelWidth: 130,
                                            labelAlign: 'top',
                                            */
                                            layout: {
                                                type: 'table'
                                            },
                                            items: [
                                                {
                                                    xtype: 'displayfield',
                                                    value: 'Max. Salary: ',
                                                    width: 70
                                                },
                                                {
                                                    xtype: 'combo',
                                                    name: 'maxLacs',
                                                    store: salaryInLacsStore,
                                                    valueField: 'id',
                                                    displayField: 'value',
                                                    triggerAction: 'all',
                                                    allowBlank: true,
                                                    msgTarget: 'under',
                                                    editable: false,
                                                    margin: '2,2,2,2',
                                                    emptyText: 'Lacs.',
                                                    width: 60
                                                },
                                                {
                                                    xtype: 'combo',
                                                    name: 'maxThousands',
                                                    store: salaryInThousandsStore,
                                                    valueField: 'id',
                                                    displayField: 'value',
                                                    triggerAction: 'all',
                                                    allowBlank: true,
                                                    msgTarget: 'under',
                                                    editable: false,
                                                    margin: '2,2,2,2',
                                                    emptyText: 'Thousand',
                                                    width: 110
                                                }
                                            ]
                                        },
                                        {
                                            xtype: 'fieldcontainer',
                                            height: 30,
                                            anchor: '100%',
                                            margin: '',
                                            padding: 5,
                                            layout: {
                                                type: 'table'
                                            },
                                            items: [
                                                {
                                                    xtype: 'displayfield',
                                                    value: 'Min. Exp.: ',
                                                    width: 70
                                                },
                                                {
                                                    xtype: 'combo',
                                                    name: 'minYrs',
                                                    store: expInYearStore,
                                                    valueField: 'id',
                                                    displayField: 'value',
                                                    triggerAction: 'all',
                                                    allowBlank: true,
                                                    msgTarget: 'under',
                                                    editable: false,
                                                    margin: '2,2,2,2',
                                                    emptyText: 'Yrs',
                                                    width: 60
                                                },
                                                {
                                                    xtype: 'combo',
                                                    name: 'minMonths',
                                                    store: expInMonthStore,
                                                    valueField: 'id',
                                                    displayField: 'value',
                                                    triggerAction: 'all',
                                                    allowBlank: true,
                                                    msgTarget: 'under',
                                                    editable: false,
                                                    margin: '2,2,2,2',
                                                    emptyText: 'Months',
                                                    width: 110
                                                }
                                            ]
                                        },
                                        {
                                            xtype: 'fieldcontainer',
                                            height: 45,
                                            anchor: '100%',
                                            margin: '',
                                            padding: 5,
                                            layout: {
                                                type: 'table'
                                            },
                                            items: [
                                                {
                                                    xtype: 'displayfield',
                                                    value: 'Max. Exp.: ',
                                                    width: 70
                                                },
                                                {
                                                    xtype: 'combo',
                                                    name: 'maxYrs',
                                                    store: expInYearStore,
                                                    valueField: 'id',
                                                    displayField: 'value',
                                                    triggerAction: 'all',
                                                    allowBlank: true,
                                                    msgTarget: 'under',
                                                    editable: false,
                                                    margin: '2,2,2,2',
                                                    emptyText: 'Yrs',
                                                    width: 60
                                                },
                                                {
                                                    xtype: 'combo',
                                                    name: 'maxMonths',
                                                    store: expInMonthStore,
                                                    valueField: 'id',
                                                    displayField: 'value',
                                                    triggerAction: 'all',
                                                    allowBlank: true,
                                                    msgTarget: 'under',
                                                    editable: false,
                                                    margin: '2,2,2,2',
                                                    emptyText: 'Months',
                                                    width: 110
                                                }
                                            ]
                                        }
                                    ]
                                },
                                //keyskills
                                {
                                    xtype: 'textarea',
                                    name: 'keySkills',
                                    fieldLabel: 'Key Skills',
                                    labelAlign: 'top',
                                    height: 80,
                                    anchor: '100%'
                                },
                                {
                                    xtype: 'combobox',
                                    name: 'openingStatusId',
                                    fieldLabel: 'Status',
                                    labelAlign: 'right',
                                    allowBlank: false,
                                    editable:false,
                                    labelWidth: 80,
                                    store: openingStatusStore,
                                    displayField: 'value',
                                    valueField: 'id',
                                    triggerAction: 'all',
                                    emptyText: 'Select Status',
                                    anchor: '100%'
                                }
                            ],
                            dockedItems: [
                                {
                                    xtype: 'toolbar',
                                    dock: 'bottom',
                                    layout: {
                                        pack: 'end',
                                        type: 'hbox'
                                    },
                                    items: [
                                        {
                                            //xtype: 'button',
                                            text: 'Save',
                                            iconCls: 'saveIcon',
                                            formBind: true,
                                            handler: function(){
                                                var form = this.up('form').getForm();
                                                if(form.isValid()){
                                                    form.submit({
                                                       url: 'opening/create',
                                                       method:'POST',
                                                       waitMsg: 'Processing...',
                                                       success: function(form, action){
                                                           var responseData = action.result.data;
                                                            Ext.Msg.alert('Success',"Opening: <b>"+responseData.positionName+"</b> is created!");
                                                            
                                                            Ext.getStore('openingStore').load({
                                                                params: {
                                                                    clientId: form.findField('clientId').getValue()
                                                                }
                                                            });
                                                   },
                                                       failure: function(form, action){
                                                           if(action.failureType == Ext.form.Action.CLIENT_INVALID){
                                                               Ext.Msg.alert("Cannot Submit", "Some fields are still invalid! ");
                                                           }
                                                           if(action.failureType == Ext.form.Action.CONNECT_FAILURE){
                                                               Ext.Msg.alert("Failure","Server communication failure: "+
                                                               action.response.status+' '+action.response.statusText);
                                                           }
                                                           if(action.failuretype == Ext.form.Action.SERVER_INVALID){
                                                               Ext.Mst.alert("Warning", "action.result.errormsg");
                                                           }
                                                       }
                                                    });
                                                }
                                            }
                                        },
                                        {
                                            xtype: 'tbseparator'
                                        },
                                        {
                                            xtype: 'button',
                                            text: 'Reset',
                                            handler: function(){
                                                        this.up('form').getForm().reset();
                                                     }
                                        }
                                    ]
                                }
                            ]
                        }
                    ]
                },
                
                {
                    xtype: 'panel',
                    id: 'openingListCenterPanel',
                    region: 'center',
                    split: true,
                    layout: 'border',
                    items: [
                        {
                            xtype: 'gridpanel',
                            id: 'openingGrid',
                            title: 'Opening List',
                            store: openingStore,
                            //width: '100%',
                            height: 250,
                            columnLines: true,
                            enableDragDrop: true,
                            collapsible: true,
                            animCollapse: true,
                            collapsed: false,
                            collapseDirection: 'top',
                            titleCollapse: true,
                            plugins: [cellEditing],
                            region: 'north',
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
                                                tooltip: 'Enter Details',
                                                handler: function(grid, rowIndex, colIndex){
                                                        var record = grid.getStore().getAt(rowIndex);
                                                        var openingId = record.get('openingId');
                                                        var component = Ext.create('Cms.view.opening.OpeningDetails',{

                                                        });
                                                        me.createWindow(component, 'Opening Id: '+openingId, 600, 600);
                                                        component.setOpeningId(openingId);
                                                }
                                            }
                                    ]
                                },
                                {
                                    xtype: 'templatecolumn',
                                    dataIndex: 'openingId',
                                    text: 'OpeningId',
                                    width: 70,
                                    tpl: '<b>{openingId}</b>'
                                },
                                {
                                    xtype: 'templatecolumn',
                                    dataIndex: 'positionName',
                                    text: 'Opening',
                                    width: 150,
                                    tpl: '<b>{positionName}</b><br>'+
                                         'Exp.: {minimumExp} - {maximumExp} Yrs <br>'+
                                         '{qualification}<br>'
                                         //'{keySkills}'

                                         
                                },
                                {
                                    xtype: 'templatecolumn',
                                    dataIndex: 'minimumExp',
                                    text: 'Details',
                                    width: 180,
                                    tpl: '<b>{clientName}</b><br>'+
                                         '{location}<br>'+
                                         'Salary: {minimumSalary} - {maximumSalary}<br>'
                                },
                                {
                                    xtype: 'templatecolumn',
                                    tpl: '<img src="../images/{openingStatusId}.jpg" />',
                                    width: 30
                                },
                                /*
                                {
                                    text:'Team',
                                    dataIndex:'openingId',
                                    field: {
                                            xtype: 'combo',
                                            name: 'clientId',
                                            transform:'state',
                                            listWidth: 200,
                                            allowBlank: false,
                                            store: teamStore,
                                            valueField: 'id',
                                            displayField: 'name',
                                            triggerAction: 'all',
                                            editable: false,
                                            
                                            listeners: function(combo, rec, index){
                                            }
                                    }
                                },
                                */    
                                {
                                    xtype: 'templatecolumn',
                                    dataIndex: 'createdDate',
                                    text: 'Created Date',
                                    width: 130,
                                    tpl: '<b>{createdByName}</b><br/>'+
                                          '{createdDate}'
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
                                    var form = Ext.getCmp('clientForm').getForm();
                                    if(records[0]){
                                        form.loadRecord(records[0]);
                                    }
                                    var grid = Ext.getCmp('openingGrid');
                                    //var arraySelected = grid.getSelectionModel().getSelection();
                                    var record = records[0];
                                    var openingId = record.get('openingId');
                                    
                                    var qualification = record.get('qualification');
                                    var qualifications = qualification.split(",");
                                    
                                    form.findField('qualification').setValue(qualifications[0]);
                                    form.findField('minLacs').setRawValue(record.get('minLacs'));
                                    form.findField('maxLacs').setRawValue(record.get('maxLacs'));
                                    
                                    form.findField('minThousands').setRawValue(record.get('minThousands'));
                                    form.findField('maxThousands').setRawValue(record.get('maxThousands'));
                                    
                                    form.findField('maxYrs').setRawValue(record.get('maxYrs'));
                                    form.findField('maxMonths').setRawValue(record.get('maxMonths'));
                                    
                                    form.findField('minYrs').setRawValue(record.get('minYrs'));
                                    form.findField('minMonths').setRawValue(record.get('minMonths'));
                                    
                                    me.setOpeningId(openingId);
                                    teamTree.setOpeningId(openingId);
                                    
                                    /*
                                     *Dont remove this code
                                     *this is usefull for Edited Cell Combobox
                                     *for Team List
                                    teamStore.load({
                                        params:{
                                                openingId: openingId
                                        }
                                    });
                                    */
                                   
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
                            }
                        },
                        {
                            xtype: 'panel',
                            id: 'openingDetailsPanel',
                            title: 'Opening Details',
                            split: true,
                            height: '90%',
                            autoScroll: true,
                            region: 'center'
                        }
                    ]
                },
                teamTree
                /*
                {
                    xtype: 'panel',
                    title: 'Teams and Openings',
                    region: 'east',
                    activeTab: 0,
                    width: '100%',
                    collapsible: true,
                    animCollapse: true,
                    collapsed: true,
                    collapseDirection: 'right',
                    split: true,
                    items: [
                        teamTree
                    ]
                }*/
            ]
        });
        me.callParent(arguments);
    }
});