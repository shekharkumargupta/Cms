Ext.define('Cms.chart.pipeline.EmployeeWisePipelinedChart', {
    extend: 'Ext.chart.Chart',

    draggable: true,
    height: 300,
    width: 400,
    animate: true,
    insetPadding: 30,
    fromDate: '0',
    toDate: '0',
    teamId: '0',
    employeeId: '0',
    companyId: '0',
    
    initComponent: function() {
        var me = this;
        
        var countPipelineStore = Ext.create('Cms.chart.pipeline.CountPipelineStore',{
            proxy: {
                type: 'ajax',
                url: 'pipeline/countByDate',
                params:{
                    fromDate: me.fromDate,
                    toDate: me.toDate,
                    teamId: me.teamId,
                    employeeId: me.employeeId,
                    companyId: me.companyId
                },
                reader: {
                    type: 'json',
                    root: 'data'
                }
            }
        });

        Ext.applyIf(me, {
            store: countPipelineStore,
            axes: [
                {
                    type: 'Numeric',
                    grid: true,
                    position: 'left',
                    fields: ['y'],
                    title: 'Pipeline Resumes',
                    grid: {
                        odd: {
                            opacity: 1,
                            fill: '#ddd',
                            stroke: '#bbb',
                            'stroke-width': 1
                        }
                    },
                    minimum: 0,
                    adjustMinimumByMajorUnit: 0
                },
                {
                    type: 'Category',
                    position: 'bottom',
                    fields: ['x'],
                    title: 'Stages',
                    grid: true,
                    label: {
                        rotate: {
                            degrees: 270
                        }
                    }
                }
            ],
            series: [
                {
                    type: 'column',
                    axis: 'left',
                    highlight: true,
                    tips: {
                      trackMouse: true,
                      width: 140,
                      height: 28,
                      renderer: function(storeItem, item) {
                        this.setTitle(storeItem.get('x') + ': ' + storeItem.get('y'));
                      }
                    },
                    label: {
                        display: 'insideEnd',
                        field: 'y',
                        orientation: 'horiozontal',
                        color: '#333',
                        'text-anchor': 'middle'
                    },
                    xField: 'x',
                    yField: ['y'],
                    style: {
                        opacity: 0.93
                    }
                }
            ]
        });

        me.callParent(arguments);
    }
});