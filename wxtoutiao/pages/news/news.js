const app = getApp();

Page({
  data: {
    newsData: [],
    typeId: "1",
    currentPage: 0,
    pageSize: 10,
    hasMore: true
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.loadNews(true);
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {
    this.setData({
      currentPage: 0,
      hasMore: true
    });
    this.loadNews(true);
    wx.stopPullDownRefresh();
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {
    this.loadNews(false);
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {},

  /**
   * 加载新闻
   */
  loadNews: function(isReload) {
    var that = this;
    if (!that.data.hasMore) return;
    wx.showLoading({
      title: '加载新闻中...'
    });
    wx.showNavigationBarLoading();

    var url = app.serverUrl + "news/" + that.data.typeId + "/" + (that.data.currentPage + 1) + "/" + that.data.pageSize;
    console.log(url);
    wx.request({
      url: url,
      success: function(data) {
        var jsonData = data.data;
        console.log(jsonData);
        if (jsonData.code == 200) {
          var _newsData = jsonData.resData.data;
          for (var i = 0; i < _newsData.length; i++) {
            if (_newsData[i].newsType == "2"){
              //表示广告
              _newsData[i].url = encodeURI(_newsData[i].url);
              console.log(_newsData[i]);
            }
          }

          var newsData = isReload ? _newsData : that.data.newsData.concat(_newsData);
          var hasMore = jsonData.resData.hasMore;
          var currentPage = that.data.currentPage + 1;

          that.setData({
            newsData: newsData,
            hasMore: hasMore,
            currentPage: currentPage
          });

          wx.hideLoading();
          wx.hideNavigationBarLoading();
        } else {
          wx.showToast({
            title: jsonData.msg
          })
        }

      },
      fail: function() {
        wx.showToast({
          title: "加载失败，请稍后重试。"
        })
      }
    })
  }
})