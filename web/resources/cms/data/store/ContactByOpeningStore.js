Ext.define('Cms.data.store.ContactByOpeningStore', {
    extend: 'Ext.data.Store',
    id: 'contactByOpeningStore',
    
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'contactByOpeningStore',
            proxy: {
                type: 'ajax',
                url: 'clientContact/contactListByOpeningId',
                reader: {
                    type: 'json',
                    root: 'data'
                }
            },
            fields: [
                {
                    name: 'contactId'
                },
                {
                    name: 'contactType'
                },
                {
                    name: 'contactTypeName'
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
                    name: 'designation'
                },
                {
                    name: 'email'
                },
                {
                    name: 'phone1'
                },
                {
                    name: 'phone2'
                },
                {
                    name: 'dateOfBirth'
                },
                {
                    name: 'dateOfAnniversary'
                }
            ]
        }, cfg)]);
    }
});
