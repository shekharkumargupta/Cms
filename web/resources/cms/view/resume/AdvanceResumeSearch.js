Ext.define('Cms.view.resume.AdvanceResumeSearch', {
    extend: 'Ext.panel.Panel',
    //title: 'Advance Search',
    layout: 'border',
    
    showComponent: function(component){
        component.closable = true;
        
        var tabContainer = Ext.getCmp('advanceSearchResumePanel');
        tabContainer.add(component);
        tabContainer.setActiveTab(component);
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
        
        var conditionTypeStore = Ext.create('Cms.data.store.ConditionTypeStore',{
        });
        
        var salaryInLacsStore = Ext.create('Cms.data.store.SalaryInLacsStore',{
        });
        
        var expInYearStore = Ext.create('Cms.data.store.ExpInYearStore',{
        });
        
        var advanceResumeStore = Ext.create('Cms.data.store.AdvanceResumeStore',{
            
        });
        
        var me = this;

        Ext.applyIf(me, {
            items: [
                    {
                      xtype: 'tabpanel',
                      id: 'advanceSearchResumePanel',
                      //title: 'Advance Search',
                      region: 'center',
                      items: [
                              {
                                xtype: 'form',
                                width: 400,
                                height: 535,
                                title: 'Search',
                                items: [
                                           {
                                            xtype: 'fieldcontainer',
                                            height: 59,
                                            padding: 20,
                                            layout: {
                                                align: 'stretch',
                                                padding: 5,
                                                type: 'hbox'
                                            },
                                            fieldLabel: 'Branch',
                                            labelAlign: 'top',
                                            anchor: '100%',
                                            items: [
                                                {
                                                    xtype: 'combobox',
                                                    name: 'branchId',
                                                    margin: 2,
                                                    width: 320,
                                                    fieldLabel: '',
                                                    store: branchStore,
                                                    triggerAction: 'all',
                                                    displayField: 'branchName',
                                                    valueField: 'branchId',
                                                    editable: false,
                                                    allowBlank: false

                                                }
                                            ]
                                        },
                                        {
                                            xtype: 'fieldcontainer',
                                            height: 59,
                                            padding: 20,
                                            layout: {
                                                align: 'stretch',
                                                padding: 5,
                                                type: 'hbox'
                                            },
                                            fieldLabel: 'Current Designation',
                                            labelAlign: 'top',
                                            anchor: '100%',
                                            items: [
                                                {
                                                    xtype: 'textfield',
                                                    name: 'currentDesignation',
                                                    margin: 2,
                                                    width: 320,
                                                    fieldLabel: ''
                                                },
                                                {
                                                    xtype: 'combobox',
                                                    name: 'designationLogic',
                                                    height: 35,
                                                    margin: 2,
                                                    padding: '',
                                                    width: 130,
                                                    fieldLabel: 'And/OR',
                                                    labelPad: 10,
                                                    labelWidth: 45,
                                                    store: conditionTypeStore,
                                                    triggerAction: 'all',
                                                    displayField: 'value',
                                                    valueField: 'id',
                                                    editable: false
                                                }
                                            ]
                                        },
                                        {
                                            xtype: 'fieldcontainer',
                                            height: 59,
                                            padding: 20,
                                            layout: {
                                                align: 'stretch',
                                                padding: 5,
                                                type: 'hbox'
                                            },
                                            fieldLabel: 'Key Skills',
                                            labelAlign: 'top',
                                            anchor: '100%',
                                            items: [
                                                {
                                                    xtype: 'textfield',
                                                    name: 'keySkills',
                                                    margin: 2,
                                                    width: 320,
                                                    fieldLabel: ''
                                                },
                                                {
                                                    xtype: 'combobox',
                                                    name: 'keySkillsLogic',
                                                    height: 35,
                                                    margin: 2,
                                                    padding: '',
                                                    width: 130,
                                                    fieldLabel: 'And/OR',
                                                    labelPad: 10,
                                                    labelWidth: 45,
                                                    store: conditionTypeStore,
                                                    triggerAction: 'all',
                                                    displayField: 'value',
                                                    valueField: 'id',
                                                    editable: false
                                                }
                                            ]
                                        },
                                        {
                                            xtype: 'fieldcontainer',
                                            height: 59,
                                            padding: 20,
                                            layout: {
                                                align: 'stretch',
                                                padding: 5,
                                                type: 'hbox'
                                            },
                                            fieldLabel: 'Qualification',
                                            labelAlign: 'top',
                                            anchor: '100%',
                                            items: [
                                                {
                                                    xtype: 'textfield',
                                                    name: 'qualification',
                                                    margin: 2,
                                                    width: 320,
                                                    fieldLabel: ''
                                                },
                                                {
                                                    xtype: 'combobox',
                                                    name: 'qualificationLogic',
                                                    height: 35,
                                                    margin: 2,
                                                    padding: '',
                                                    width: 130,
                                                    fieldLabel: 'And/OR',
                                                    labelPad: 10,
                                                    labelWidth: 45,
                                                    store: conditionTypeStore,
                                                    triggerAction: 'all',
                                                    displayField: 'value',
                                                    valueField: 'id',
                                                    editable: false
                                                }
                                            ]
                                        },
                                        {
                                            xtype: 'fieldcontainer',
                                            height: 59,
                                            padding: 20,
                                            layout: {
                                                align: 'stretch',
                                                padding: 5,
                                                type: 'hbox'
                                            },
                                            fieldLabel: 'Location',
                                            labelAlign: 'top',
                                            anchor: '100%',
                                            items: [
                                                {
                                                    xtype: 'textfield',
                                                    name: 'location',
                                                    margin: 2,
                                                    width: 320,
                                                    fieldLabel: ''
                                                },
                                                {
                                                    xtype: 'combobox',
                                                    name: 'locationLogic',
                                                    height: 35,
                                                    margin: 2,
                                                    padding: '',
                                                    width: 130,
                                                    fieldLabel: 'And/OR',
                                                    labelPad: 10,
                                                    labelWidth: 45,
                                                    store: conditionTypeStore,
                                                    triggerAction: 'all',
                                                    displayField: 'value',
                                                    valueField: 'id',
                                                    editable: false
                                                }
                                            ]
                                        },
                                        {
                                            xtype: 'fieldcontainer',
                                            height: 59,
                                            padding: 20,
                                            layout: {
                                                align: 'stretch',
                                                padding: 5,
                                                type: 'hbox'
                                            },
                                            fieldLabel: 'Salary Between',
                                            labelAlign: 'top',
                                            anchor: '100%',
                                            items: [
                                                {
                                                    xtype: 'combobox',
                                                    name: 'minSalary',
                                                    margin: 2,
                                                    width: 125,
                                                    fieldLabel: '',
                                                    store: salaryInLacsStore,
                                                    triggerAction: 'all',
                                                    displayField: 'value',
                                                    valueField: 'id',
                                                    editable: false
                                                },
                                                {
                                                    xtype: 'combobox',
                                                    name: 'maxSalary',
                                                    height: 35,
                                                    margin: 2,
                                                    padding: '',
                                                    width: 125,
                                                    fieldLabel: '',
                                                    labelPad: 10,
                                                    labelWidth: 45,
                                                    store: salaryInLacsStore,
                                                    triggerAction: 'all',
                                                    displayField: 'value',
                                                    valueField: 'id',
                                                    editable: false
                                                }
                                            ]
                                        },
                                        {
                                            xtype: 'fieldcontainer',
                                            height: 59,
                                            padding: 20,
                                            layout: {
                                                align: 'stretch',
                                                padding: 5,
                                                type: 'hbox'
                                            },
                                            fieldLabel: 'Experience Between',
                                            labelAlign: 'top',
                                            anchor: '100%',
                                            items: [
                                                {
                                                    xtype: 'combobox',
                                                    name: 'minExp',
                                                    margin: 2,
                                                    width: 125,
                                                    store: expInYearStore,
                                                    triggerAction: 'all',
                                                    displayField: 'value',
                                                    valueField: 'id',
                                                    editable: false
                                                },
                                                {
                                                    xtype: 'combobox',
                                                    name: 'maxExp',
                                                    height: 35,
                                                    margin: 2,
                                                    padding: '',
                                                    width: 125,
                                                    fieldLabel: '',
                                                    labelPad: 10,
                                                    labelWidth: 45,
                                                    store: expInYearStore,
                                                    triggerAction: 'all',
                                                    displayField: 'value',
                                                    valueField: 'id',
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
                                                pack: 'end',
                                                type: 'hbox'
                                            },
                                            items: [
                                                {
                                                    xtype: 'button',
                                                    text: 'Search',
                                                    formBind: true,
                                                    handler: function(){
                                                           var form = this.up('form').getForm();
                                                           var branchId = form.findField('branchId').getValue();
                                                           var keySkills = form.findField('keySkills').getValue();
                                                           var keySkillsLogic = form.findField('keySkillsLogic').getValue();
                                                           var qualification = form.findField('qualification').getValue();
                                                           var qualificationLogic = form.findField('qualificationLogic').getValue();
                                                           var location = form.findField('location').getValue();
                                                           var locationLogic = form.findField('locationLogic').getValue();
                                                           var designation = form.findField('currentDesignation').getValue();
                                                           var designationLogic = form.findField('designationLogic').getValue();
                                                           var minSalary = form.findField('minSalary').getValue();
                                                           var maxSalary = form.findField('maxSalary').getValue();
                                                           var minExp = form.findField('minExp').getValue();
                                                           var maxExp = form.findField('maxExp').getValue();
                                                           
                                                           /*
                                                           Ext.Msg.alert('Message', branchId+" | "+
                                                                                    keySkills+" | "+
                                                                                    keySkillsLogic+" | "+
                                                                                    qualification+" | "+
                                                                                    qualificationLogic+" | "+
                                                                                    location+" | "+
                                                                                    locationLogic+" | "+
                                                                                    minSalary+" | "+
                                                                                    maxSalary+" | "+
                                                                                    minExp+" | "+
                                                                                    maxExp
                                                                                    );
                                                           */
                                                          
                                                           advanceResumeStore.load({
                                                               params:{
                                                                   branchId         : branchId,
                                                                   designation      : designation,
                                                                   designationLogic : designationLogic,
                                                                   keySkills        : keySkills,
                                                                   keySkillsLogic   : keySkillsLogic,
                                                                   qualification    : qualification,
                                                                   qualificationLogic: qualificationLogic,
                                                                   location         : location,
                                                                   locationLogic    : locationLogic,
                                                                   minSalary        : minSalary,
                                                                   maxSalary        : maxSalary,
                                                                   minExp           : minExp,
                                                                   maxExp           : maxExp
                                                               }
                                                           });
                                                            var tabContainer = Ext.getCmp('advanceSearchResumePanel');
                                                            tabContainer.setActiveTab(1);
                                                    }
                                                },
                                                {
                                                    xtype: 'tbseparator'
                                                },
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
                            xtype: 'gridpanel',
                            title: 'Result',
                            store: advanceResumeStore,
                            region: 'center',
                            columnLines: true,
                            columns: [
                                {
                                    xtype: 'actioncolumn',
                                    width: 30,
                                    text: '',
                                    align: 'center',
                                    items:[
                                            {
                                            //icon   : '../images/word.jpg',
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
                                    width: 200,
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
                                }
                            ],
                            viewConfig: {

                            },
                            listeners: {
                                selectionchange: function(model, records){
                                    if(records[0]){
                                        if(Ext.getCmp('candidateInfoForm') != null){
                                            Ext.getCmp('candidateInfoForm').getForm().loadRecord(records[0]);
                                            var record = records[0];
                                        }
                                    }
                                }
                            }
                        }
                    ]
                }
                
            ]});

        me.callParent(arguments);
    }
});