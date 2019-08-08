Ext.define('Cms.data.store.BranchTypeStore', {
    extend: 'Ext.data.Store',
    id: 'branchTypeStore',
    
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'branchTypeStore',
            proxy: {
                type: 'ajax',
                url: 'util/branchTypeList',
                reader: {
                    type: 'json',
                    root: 'data'
                }
            },
            fields: [
                {
                    name: 'id'
                },
                {
                    name: 'value'
                }
            ]
        }, cfg)]);
    }
});
