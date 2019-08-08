Ext.define('Cms.data.store.QualificationStore', {
    extend: 'Ext.data.Store',
    id: 'qualificationStore',
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'qualificationStore',
            proxy: {
                type: 'ajax',
                url: 'util/qualificationList',
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
