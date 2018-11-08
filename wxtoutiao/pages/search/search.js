var WxSearch = require('../../wxSearchView/wxSearchView.js');
const app = getApp();

Page({
  data: {
    hasData:true
  },

  onLoad: function() {
    var that = this;
    var url = app.serverUrl + "search/match/hotKw";
    wx.showLoading({});
    wx.request({
      url: url,
      success: function(res) {
        wx.hideLoading();
        var data = res.data;
        if (data.success) {
          WxSearch.init(
            that, // 本页面一个引用
            data.resData,
            function(kw){
              that.mySearch(kw);
            }, function (kw) {
              that.mySearch(kw);
            }
          );
        }
      }
    })
  },
  wxSearchKeyTap: WxSearch.wxSearchKeyTap,
  wxSearchClear: WxSearch.wxSearchClear,
  wxSearchInput:function(e){
    this.setData({
      matchData:[]
    });
  },
  wxSearchConfirm: WxSearch.wxSearchConfirm,
  
  mySearch: function(value) {
    var that = this;
    that.setData({
      hasData:true
    });
    wx.showLoading({});
    app.getLoginUserInfo(function(loginUserInfo) {
      var userId = loginUserInfo.id;
      var url = app.serverUrl + "search/match/" + userId + "/" + value;
      wx.request({
        url: url,
        success:function(res){
            wx.hideLoading();
            var data = res.data;
            if(data.success){
              that.setData({
                hasData:data.resData.hasData,
                matchData:data.resData.data
              });
            }
        }
      })
    });
  },/**显示新闻详情*/
  showMatchDetail: function (e) {
    var id = e.target.dataset.id;
    wx.navigateTo({
      url: '../matchDetail/matchDetail?id=' + id
    })
  }
})