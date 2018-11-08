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
  onLoad: function (options) {
    var userId = options.userId;
    var id = options.id;
    var loginUserInfo = app.globalData.loginUserInfo;
    var avatarUrl = loginUserInfo.avatarUrl;
    var height = wx.getSystemInfoSync().windowHeight;
    this.setData({
      avatarUrl: avatarUrl,
      userId: userId,
      id: id,
      height: height - 100
    });
  },
  /**点击取消之后*/
  cancelLogin: function () {
    wx.navigateBack({
      data: 1
    });
  },
  /**确认登录*/
  sureLogin: function () {
    var userId = this.data.userId;
    var id = this.data.id;
    var url = app.serverUrl + "/wx/login/confirm?userId=" + userId + "&id=" + id;
    wx.showLoading({
      mask: true
    })
    wx.request({
      url: url,
      success: function (res) {
        wx.hideLoading();
        if (res.data.success) {
          if (res.data.resData.isOk) {
            wx.showLoading({
              title: "授权成功！",
              mask: true
            })

            setTimeout(function () {
              wx.hideLoading();
              wx.switchTab({
                url: '../match/match'
              })
            }, 2000);
          } else {
            app.showErrorMsg(res.data.resData.msg);
          }
        } else {
          app.showErrorMsg("网络繁忙");
        }

      },
      fail: function () {
        wx.hideLoading();
        app.showErrorMsg("网络繁忙");
      }
    })
  }
})