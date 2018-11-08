const app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    array: ["2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "A+"]
  },

  /**Ï
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var matchId = options.matchId;
    var userId = app.globalData.loginUserInfo.id;
    //加载是否可以继续输入成绩， 比如最后一轮已经输入了，就不能输入
    //2 加载当前轮的成绩
    //3.加载是否可以平局

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

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})