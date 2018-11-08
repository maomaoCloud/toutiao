const app = getApp();
Page({
  data: {
    helpData:[]
  }, 

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    wx.showLoading({
      title: '加载中...',
      mask: true
    })

    var url = app.serverUrl + "help/list";
    var that = this;
    wx.request({
      url: url,
      method:"GET",
      success:function(res){
        console.log(res);
        wx.hideLoading();
        that.setData({
          helpData:res.data.resData
        });

      },fail:function(){
        app.showErrorMsg("网络繁忙！");
      }
    })

  },
  showHelpDetail: function(e) {
    var hid = e.target.dataset.id;
    if (hid == undefined || hid == null) {
      hid = e.currentTarget.dataset.id;
    }

    wx.navigateTo({
      url: '../helpDetail/helpDetail?id=' + hid,
    })
  }
})