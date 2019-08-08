Ext.define('Cms.chart.pipeline.CountPipelineStore', {
    extend: 'Ext.data.Store',
    id: 'countPipelineStore',
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'countPipelineStore',
            proxy: {
                type: 'ajax',
                url: 'pipeline/countByDate',
                params:{
                    fromDate: '',
                    toDate: '',
                    teamId: '',
                    employeeId: '',
                    companyId: ''
                },
                reader: {
                    type: 'json',
                    root: 'data'
                }
            },
            fields: [
                {
                    name: 'x'
                },
                {
                    name: 'y',
                    type: 'int'
                }
            ]
        }, cfg)]);
    }
});
