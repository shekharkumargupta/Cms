Ext.define('Cms.data.store.ConditionTypeStore', {
    extend: 'Ext.data.Store',
    id: 'conditionTypeStore',
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'conditionTypeStore',
            proxy: {
                type: 'ajax',
                url: 'util/conditionTypeList',
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
