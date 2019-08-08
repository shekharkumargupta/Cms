Ext.define('Cms.data.store.BranchStore', {
    extend: 'Ext.data.Store',
    id: 'branchStore',
    
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'branchStore',
            proxy: {
                type: 'ajax',
                url: 'branch/branchList',
                reader: {
                    type: 'json',
                    root: 'data'
                }
            },
            fields: [
                {
                    name: 'branchId'
                },
                {
                    name: 'branchType'
                },
                {
                    name: 'branchName'
                },
                {
                    name: 'street1'
                },
                {
                    name: 'street2'
                },
                {
                    name: 'country'
                },
                {
                    name: 'city'
                },
                {
                    name: 'pinCode'
                },
                {
                    name: 'countryString'
                },
                {
                    name: 'cityString'
                },
                {
                    name: 'firstName'
                },
                {
                    name: 'middleInitial'
                },
                {
                    name: 'lastName'
                },
                {
                    name: 'email'
                },
                {
                    name: 'contactNo'
                }
                
            ]
        }, cfg)]);
    }
});
