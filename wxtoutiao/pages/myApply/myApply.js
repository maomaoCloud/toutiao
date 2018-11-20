const app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    index: 0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var vIndex = options.index;
    var index = this.data.index;
    if (vIndex != null && vIndex != "" && typeof(vIndex) != undefined) {
      index = parseInt(vIndex);
    }

    var currentData = [];
    var myOrder = app.globalData.myOrder;
    switch (index) {
      case 0:
        currentData = myOrder.all;
        break;
      case 1:
        currentData = myOrder.notStart;
        break;
      case 2:
        currentData = myOrder.underWay;
        break;
      case 3:
        currentData = myOrder.end;
        break;
    }

    this.setData({
      currentData: currentData,
      index: index
    });
  },
  /**切换tab*/
  switchNav: function(e) {
    var index = parseInt(e.target.dataset.index);
    var currentData = [];
    var myOrder = app.globalData.myOrder;
    switch (index) {
      case 0:
        currentData = myOrder.all;
        break;
      case 1:
        currentData = myOrder.notStart;
        break;
      case 2:
        currentData = myOrder.underWay;
        break;
      case 3:
        currentData = myOrder.end;
        break;
    }
    this.setData({
      index: index,
      currentData: currentData
    });
  },
  /**查看比赛详情*/
  showMatchDetail: function(e) {
    var id = e.currentTarget.dataset.id;

    wx.navigateTo({
      url: '../matchDetail/matchDetail?id=' + id
    })
  },
  /**查看成绩*/
  showScore: function(e) {
    console.log(e);
    var matchId = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: '../score/score?matchId=' + matchId
    })
  }
})