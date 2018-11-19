const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var matchId = options.matchId;
    var url = app.serverUrl + "match/manager/sign/detail/" + matchId;

    var that = this;
    wx.request({
      url: url,
      success: function(res) {
        var data = res.data.resData;
        that.setData({
          data: data
        });
      }
    })
  }
})