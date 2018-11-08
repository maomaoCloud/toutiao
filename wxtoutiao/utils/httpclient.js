var app = getApp();
function get(param) {
  var mydata = {};

  var header = param.header;
  if (!header) {
    header = {
      'content-type': 'application/json'
    }
  }

  mydata = param.data || {};
  mydata['sessionId'] = app.globalData.sessionId;

  wx.request({
    url: app.globalData.serverAddress + param.url,
    data: mydata,
    header: header,
    method: "GET",
    success: param.success,
    fail: param.fail,
    complete: function () {
      // complete
      if (param.complete) {
        param.complete();
      }
    }
  })
}
module.exports = {
  get: get
}