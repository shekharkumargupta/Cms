
var authenticationData = null;

function setAutenticationData(data){
    this.authenticationData = data;
}
function getAuthenticationData(){
    return this.authenticationData;
}

/*
getLoginInfo: function(){
    var jsonData = null;
      Ext.Ajax.request({
        url: 'login/loginInfo',
        method: 'GET',
        success: function(result, request){
            var responseData = result.responseText;
            var profileButton = Ext.getCmp('mainProfileButton');
            jsonData = Ext.JSON.decode(responseData); 
            //profileButton.setText(jsonData.data.loginId);
            profileButton.setText(jsonData.data.loginId);
        },
        failure: function(form, action){
               if(action.failureType == Ext.form.Action.CLIENT_INVALID){
                   Ext.Msg.alert("Cannot Submit", "Some fields are still invalid! ");
               }
               if(action.failureType == Ext.form.Action.CONNECT_FAILURE){
                   Ext.Msg.alert("Failure","Server communication failure: "+
                   action.response.status+' '+action.response.statusText);
               }
               if(action.failuretype == Ext.form.Action.SERVER_INVALID){
                   Ext.Mst.alert("Warning", "action.result.errormsg");
               }
         }
      }); 
      return jsonData;
}
*/