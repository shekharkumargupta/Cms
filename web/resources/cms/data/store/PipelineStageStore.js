Ext.define('Cms.data.store.PipelineStageStore', {
    extend: 'Ext.data.Store',
    id: 'pipelineStageStore',
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'pipelineStageStore',
            proxy: {
                type: 'ajax',
                url: 'pipelineStage/pipelineStagesList',
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
                    name: 'name'
                },
                {
                    name: 'stepNo'
                },
                {
                    name: 'companyId'
                }
            ]
        }, cfg)]);
    }
});
