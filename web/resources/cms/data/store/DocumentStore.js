Ext.define('Cms.data.store.DocumentStore',{
    extend: 'Ext.data.Store',
    id: 'documentStore',
    

    constructor: function(cfg){
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'documentStore',
            proxy:{
                type: 'ajax',
                url: 'document/documentByResumeId',
                reader: {
                    type: 'json',
                    root: 'data'
                }
            },
            fields:[
                {
                    name: 'id'
                },
                {
                    name: 'documentTypeId'
                },
                {
                    name: 'documentTypeName'
                },
                {
                    name: 'fileName'
                },
                {
                    name: 'fileType'
                },
                {
                    name: 'fileSize'
                },
                {
                    name: 'uploadedById'
                },
                {
                    name: 'updatedAt'
                },
                {
                    name: 'branchId'
                },
                {
                    name: 'companyId'
                },
                {
                    name: 'resumeId'
                }
            ]
        }, cfg)])
    }
})

