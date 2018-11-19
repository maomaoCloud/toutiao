var WxParse = require('../../wxParse/wxParse.js');
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
    wx.showLoading({
      mask: true
    })

    var id = options.id;
    var that = this;
    var url = app.serverUrl + "match/manager/help/detail/" + id;
    wx.request({
      url: url,
      method:"GET",
      success:function(res){
        wx.hideLoading();
        var data = res.data.resData;
        that.setData({
          data:data
        });
        that.parseHtml(data.helpContent);
      },fail(){
        app.showErrorMsg("网络繁忙！");
      }
    })

  },
  parseHtml: function (html) {
    var that = this;
    WxParse.wxParse('article', 'html', html, that, 0);
  }
})