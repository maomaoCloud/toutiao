var WxParse = require('../../wxParse/wxParse.js');
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    title: "",
    date: "",
    newsId: null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var newsId = options.newsId;
    var url = app.serverUrl + "news/detail/" + newsId;
    wx.showLoading({
      title: '加载中...'
    });
    var that = this;
    wx.request({
      url: url,
      method: "GET",
      success: function(res) {
        var data = res.data;
        if (data.success) {
          var title = data.resData.title;
          var date = data.resData.addTime;
          var content = data.resData.content;
          that.parseHtml(content);
          that.setData({
            title: title,
            date: date,
            newsId: newsId
          });
        }
        wx.hideLoading();
      }
    })
  },
  parseHtml: function(html) {
    var that = this;
    WxParse.wxParse('article', 'html', html, that, 0);
  }
})