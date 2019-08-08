Ext.define('Cms.view.team.Team', {
    extend: 'Ext.panel.Panel',
    
    height: 420,
    layout: {
        type: 'border'
    },
    //title: 'Create Designation
    iconCls: 'bookIcon',
    employeeId: '0',
    
    getEmployeeId: function(){
      return this.employeeId;  
    },
    
    setEmployeeId: function(employeeId){
      this.employeeId = employeeId;  
    },
    
    
    createTeamTree : function(memberStore){
        var memberId = "0_0";
        var teamId = 0;
        var tree = Ext.create('Ext.tree.Panel', {
            id:'teamTree',
            rootVisible:false,
            lines:false,
            height: 410,
            width: '100%',
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
                    if(rec.isLeaf() == true){
                        var data = rec.get('id');
                        memberId = data.substring(2, data.length);
                    }
                },
                itemmouseup: function(view,rec,item,index,eventObj){
                    if(rec.isLeaf() == false){
                        teamId = rec.get('id');
                    }
                    //Ext.Msg.alert('Message', memberId+"/"+teamId);
                    if(memberId > 0 && teamId > 0){
                        Ext.Ajax.request({
                            method: 'POST',
                            waitMsg: 'processing...',
                            url: 'team/addMember?teamId='+teamId+"&memberId="+memberId,
                            success: function(){
                                //Ext.Msg.alert('Message', 'Updated Successfully!');
                            },
                            failure: function(){
                                Ext.Msg.alert('Message', 'Failure!');
                            }
                        })
                    }else{
                        //Ext.Msg.alert('Alert', 'Please properlly select an Employee and a Team!');
                    }
                    memberId = 0;
                    teamId = 0;
                }
            }
        });
        return tree;
    },

    initComponent: function() {
        var me = this;
        var memberStore = Ext.create('Cms.data.store.TeamMemberStore',{
        });
        
        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'panel',
                    bodyPadding: 10,
                    collapsible: true,
                    title: 'Teams & Members',
                    titleCollapse: true,
                    region: 'center',
                    items: [
                                {
                                    xtype : 'form',
                                    id: 'teamForm',
                                    height: 40,
                                    border: false,
                                    items: [
                                        {
                                            xtype: 'hidden',
                                            name: 'id',
                                            value: '0'
                                        },
                                        {
                                            xtype: 'textfield',
                                            name: 'name',
                                            labelWidth: 80,
                                            fieldLabel: 'Team Name',
                                            anchor: '100%',
                                            allowBlank: false
                                        }
                                    ]
                                },
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
                                {
                                    xtype: 'button',
                                    text: 'Create',
                                    iconCls: 'saveIcon',
                                    formBind: true,
                                    handler: function(){
                                        var form = Ext.getCmp('teamForm').getForm();
                                            if(form.isValid()){
                                                form.submit({
                                                   url: 'team/create',
                                                   method:'POST',
                                                   waitMsg: 'Processing...',
                                                   success: function(form, action){
                                                       var responseData = action.result.data;
                                                       Ext.Msg.alert('Success',responseData.name);
                                                       memberStore.load();
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
                }
                
            ]
        });

        me.callParent(arguments);
    }
});