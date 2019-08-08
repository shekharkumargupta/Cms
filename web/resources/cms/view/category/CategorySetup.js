Ext.define('Cms.view.category.CategorySetup', {
    extend: 'Ext.panel.Panel',

    height: 800,
    layout: {
        type: 'border'
    },
    title: 'Category Setup',

    initComponent: function() {
        
        var designationStore = Ext.create('Cms.data.store.DesignationStore',{
            url: 'designation/designationList'
        });
        
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'panel',
                    width: 150,
                    title: 'Category',
                    region: 'west',
                    split: true,
                    items: [
                        {
                            xtype: 'form',
                            bodyPadding: 10,
                            title: 'My Form',
                            items: [
                                {
                                    xtype: 'textfield',
                                    fieldLabel: 'Category Name',
                                    labelAlign: 'top',
                                    anchor: '100%'
                                },
                                {
                                    xtype: 'button',
                                    text: 'Save'
                                }
                            ]
                        },
                        {
                            xtype: 'gridpanel',
                            title: 'My Grid Panel',
                            store: designationStore,
                            columns: [
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'string',
                                    text: 'String'
                                },
                                {
                                    xtype: 'numbercolumn',
                                    dataIndex: 'number',
                                    text: 'Number'
                                },
                                {
                                    xtype: 'datecolumn',
                                    dataIndex: 'date',
                                    text: 'Date'
                                },
                                {
                                    xtype: 'booleancolumn',
                                    dataIndex: 'bool',
                                    text: 'Boolean'
                                }
                            ],
                            viewConfig: {

                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    width: 150,
                    title: 'Qualification',
                    region: 'east',
                    split: true,
                    items: [
                        {
                            xtype: 'form',
                            bodyPadding: 10,
                            title: 'My Form',
                            items: [
                                {
                                    xtype: 'textfield',
                                    fieldLabel: 'Qualification',
                                    labelAlign: 'top',
                                    anchor: '100%'
                                },
                                {
                                    xtype: 'button',
                                    text: 'Save'
                                }
                            ]
                        },
                        {
                            xtype: 'gridpanel',
                            title: 'My Grid Panel',
                            store: designationStore,
                            columns: [
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'string',
                                    text: 'String'
                                },
                                {
                                    xtype: 'numbercolumn',
                                    dataIndex: 'number',
                                    text: 'Number'
                                },
                                {
                                    xtype: 'datecolumn',
                                    dataIndex: 'date',
                                    text: 'Date'
                                },
                                {
                                    xtype: 'booleancolumn',
                                    dataIndex: 'bool',
                                    text: 'Boolean'
                                }
                            ],
                            viewConfig: {

                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    title: 'Positions againts category',
                    region: 'center',
                    split: true,
                    items: [
                        {
                            xtype: 'form',
                            bodyPadding: 10,
                            title: 'My Form',
                            items: [
                                {
                                    xtype: 'textfield',
                                    fieldLabel: 'Position Name',
                                    labelAlign: 'top',
                                    anchor: '100%'
                                },
                                {
                                    xtype: 'button',
                                    text: 'Save'
                                }
                            ]
                        },
                        {
                            xtype: 'gridpanel',
                            title: 'My Grid Panel',
                            store: designationStore,
                            columns: [
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'string',
                                    text: 'String'
                                },
                                {
                                    xtype: 'numbercolumn',
                                    dataIndex: 'number',
                                    text: 'Number'
                                },
                                {
                                    xtype: 'datecolumn',
                                    dataIndex: 'date',
                                    text: 'Date'
                                },
                                {
                                    xtype: 'booleancolumn',
                                    dataIndex: 'bool',
                                    text: 'Boolean'
                                }
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