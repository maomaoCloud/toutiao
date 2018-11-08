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
  onLoad: function () {
    var loginUserInfo = app.globalData.loginUserInfo;
    var userId = loginUserInfo.id;
    var height = wx.getSystemInfoSync().windowHeight;
    this.setData({
      avatarUrl: "../../resource/head.png",
      userId: userId,
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
  }
   
})