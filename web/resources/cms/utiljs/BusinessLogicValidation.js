function isExistingEmail(emailId){
    var status = false;
    Ext.Ajax.request({
       method: 'GET',
       url: 'login/isExistingUser',
       params:{emailId : emailId},
       waitMsg: 'Processing...',
       success: function ( result, request ) {
              //Ext.Msg.alert('Message', result.responseText);
              var jsonData = Ext.JSON.decode(result.responseText);
              var resultMessage = jsonData.data;
              if(resultMessage == 'YES'){
                   Ext.Msg.alert('Message', "<b>"+emailId+'</b> already registered.');
                   status = true;
              }
       },
       failure: function ( result, request ) {
           var jsonData = Ext.JSON.decode(result.responseText);
           var resultMessage = jsonData.data;
           Ext.Msg.alert('Error', resultMessage);
       }
    });
    return status;
}

function isExistingResume(emailId, contactNo){
    var status = false;
    Ext.Ajax.request({
       method: 'GET',
       url: 'resume/isExistingResume',
       params:{
                emailId : emailId,
                contactNo: contactNo
              },
       waitMsg: 'Processing...',
       success: function ( result, request ) {
              var jsonData = Ext.JSON.decode(result.responseText);
              var resultMessage = jsonData.data;
              //Ext.Msg.alert('Message', resultMessage);
              if(resultMessage == 'YES'){
                   Ext.Msg.alert('Message', "Already Existing Record.");
                   status = true;
              }
       },
       failure: function ( result, request ) {
           var jsonData = Ext.JSON.decode(result.responseText);
           var resultMessage = jsonData.data;
           Ext.Msg.alert('Error', resultMessage);
       }
    });
    return status;
}

function isOpeningAlreadyAssignedToTeam(teamId, openingId){
    var status = false;
    Ext.Ajax.request({
       method: 'GET',
       url: 'opening/isAlreadyAssignedToTeam',
       params:{
                openingId : openingId,
                teamId: teamId
              },
       waitMsg: 'Processing...',
       success: function ( result, request ) {
              //Ext.Msg.alert('Message', result.responseText);
              var jsonData = Ext.JSON.decode(result.responseText);
              var resultMessage = jsonData.data;
              if(resultMessage == 'YES'){
                   Ext.Msg.alert('Alert', "OpeningId: <b>"+openingId+'</b> is already assigned to this team.');
                   status = true;
              }
       },
       failure: function ( result, request ) {
           var jsonData = Ext.JSON.decode(result.responseText);
           var resultMessage = jsonData.data;
           Ext.Msg.alert('Error', resultMessage);
       }
    });
    return status;
}

function isOpeningAlreadyAssignedToEmployee(employeeId, openingId){
    Ext.Ajax.request({
       method: 'GET',
       url: 'opening/isAlreadyAssignedToEmployee',
       params:{
                openingId : openingId,
                employeeId: employeeId
              },
       waitMsg: 'Processing...',
       success: function ( result, request ) {
              var jsonData = Ext.JSON.decode(result.responseText);
              var status = jsonData.data;
              if(status == 'YES'){
                  Ext.Msg.alert('Message','Opening Id: '+openingId+" is already assigned to employee "+employeeId);
              }
       },
       failure: function ( result, request ) {
       }
    });
    
}

function isResumeAlreadyPipelined(resumeId, openingId){
    var status = false;
    Ext.Ajax.request({
       method: 'GET',
       url: 'resume/isResumeAlreadyPipelined',
       params:{
                resumeId: resumeId,
                openingId : openingId
              },
       waitMsg: 'Processing...',
       success: function ( result, request ) {
              //Ext.Msg.alert('Message', result.responseText);
              var jsonData = Ext.JSON.decode(result.responseText);
              var resultMessage = jsonData.data;
              if(resultMessage == 'YES'){
                   Ext.Msg.alert('Alert', "Resume Id: <b>"+resumeId+'</b> has already been piplined for the Opening Id: <b>'+openingId+"</b>");
                   status = true;
              }
       },
       failure: function ( result, request ) {
           var jsonData = Ext.JSON.decode(result.responseText);
           var resultMessage = jsonData.data;
           Ext.Msg.alert('Error', resultMessage);
       }
    });
    return status;
}