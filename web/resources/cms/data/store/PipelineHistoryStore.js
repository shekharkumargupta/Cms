Ext.define('Cms.data.store.PipelineHistoryStore', {
    extend: 'Ext.data.Store',
    id: 'pipelineHistoryStore',
    
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'resumeStore',
            proxy: {
                type: 'ajax',
                url: 'history/historyList',
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
                    name: 'pipelineId'
                },
                {
                    name: 'pipelineStatusId'
                },
                {
                    name: 'pipelineStatusName'
                },
                {
                    name: 'remarks'
                },
                {
                    name: 'createdById'
                },
                {
                    name: 'createdByName'
                },
                {
                    name: 'createdAt'
                }
            ]
        }, cfg)]);
    }
});
