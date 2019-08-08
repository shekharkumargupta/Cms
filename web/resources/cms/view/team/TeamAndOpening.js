Ext.define('Cms.view.team.TeamAndOpening', {
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
        var teamOpeningStore = Ext.create('Cms.data.store.TeamOpeningStore',{
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
                                store: teamOpeningStore,
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
                                    },
                                listeners: {
                                        itemmouseup: function(node, data) {
                                            if(me.getOpeningId() > 0){
                                                //Ext.Msg.alert('Message', me.getOpeningId()+" / "+data.get('id'));
                                                if(isOpeningAlreadyAssignedToTeam(data.get('id'), me.getOpeningId()) == false){
                                                    var teamId = data.get('id');
                                                    var openingId = me.getOpeningId();
                                                    Ext.Ajax.request({
                                                        method: 'POST',
                                                        waitMsg: 'processing...',
                                                        url: 'team/addOpening?teamId='+teamId+"&openingId="+openingId,
                                                        success: function(){
                                                            Ext.Msg.alert('Message', 'Updated Successfully!');
                                                        },
                                                        failure: function(){
                                                            Ext.Msg.alert('Message', 'Failure!');
                                                        }
                                                    });
                                                    teamOpeningStore.load();
                                                }
                                            }else{
                                                
                                            }
                                            me.setOpeningId('0');
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
                                        teamOpeningStore.load(); 
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