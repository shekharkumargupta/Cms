Ext.define('Cms.view.team.EmployeeAndOpening', {
    extend: 'Ext.panel.Panel',
    
    layout: {
        type: 'border'
    },
    //title: 'Create Designation
    iconCls: 'bookIcon',
    itemCatched: false,
    openingId: '0',
    
    getItemCatched: function(){
        return this.itemCatched;
    },
    
    setItemCatched: function(status){
        this.itemCatched = status;
    },
    
    getOpeningId: function(){
        return this.openingId;
    },
    
    setOpeningId: function(opnId){
        this.openingId = opnId;
    },


    initComponent: function() {
        var me = this;
        var employeeOpeningStore = Ext.create('Cms.data.store.EmployeeOpeningStore',{
        });
        //Ext.Msg.alert('Message', me.getOpeningId());
        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'panel',
                    bodyPadding: 0,
                    //title: 'Teams & Members',
                    //titleCollapse: true,
                    region: 'center',
                    split: true,
                    items: [
                                new Ext.create('Ext.tree.Panel', {
                                id:'teamTree',
                                rootVisible:false,
                                lines:false,
                                height: 280,
                                width: '100%',
                                split:true,
                                autoScroll:true,
                                useArrows:true,
                                store: employeeOpeningStore,
                                columns: [
                                        {
                                            xtype: 'treecolumn', //this is so we know which column will show the tree
                                            text: 'Name',
                                            sortable: true,
                                            dataIndex: 'text',
                                            width: 180
                                        }
                                        ,{
                                            //xtype: 'treecolumn',
                                            text: 'Details',
                                            dataIndex: 'details',
                                            sortable: true,
                                            hidden: false,
                                            width: 180
                                        }],
                                viewConfig: {
                                    plugins: {
                                        ptype: 'treeviewdragdrop',
                                        ddGroup:'openingDDGroup'
                                    }
                                },
                                listeners: {
                                    itemmouseup: function(node, data) { 
                                        if(me.getOpeningId() > 0){
                                            //var result = isOpeningAlreadyAssignedToEmployee(data.get('id'), me.getOpeningId());
                                            var employeeId = data.get('id');
                                            var openingId = me.getOpeningId();
                                            Ext.Ajax.request({
                                                method: 'POST',
                                                waitMsg: 'processing...',
                                                url: 'user/addOpening?employeeId='+employeeId+"&openingId="+openingId,
                                                success: function(){
                                                    //Ext.Msg.alert('Message', 'Updated Successfully!');
                                                    me.setOpeningId('0');
                                                },
                                                failure: function(){
                                                    Ext.Msg.alert('Message', 'Failure!');
                                                    me.setOpeningId('0');
                                                }
                                            });
                                            employeeOpeningStore.load();
                                        }
                                    }
                                }
                            })
                    ],
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
                                        employeeOpeningStore.load(); 
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