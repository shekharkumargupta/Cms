Ext.define('Cms.data.store.EmployeeTypeStore', {
    extend: 'Ext.data.Store',
    id: 'employeeTypeStore',
    
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'employeeTypeStore',
            proxy: {
                type: 'ajax',
                url: 'util/employeeTypeList',
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
