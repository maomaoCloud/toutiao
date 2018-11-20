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
    if (vIndex != null && vIndex != "" && typeof (vIndex) != undefined) {
      index = parseInt(vIndex);
    }
    
    var currentData = [];
    var myPublish = app.globalData.myPublish;
    switch(index){
      case 0: currentData = myPublish.all;break;
      case 1: currentData = myPublish.notStart; break;
      case 2: currentData = myPublish.underWay; break;
      case 3: currentData = myPublish.end; break;
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
    var myPublish = app.globalData.myPublish;
    switch (index) {
      case 0: currentData = myPublish.all; break;
      case 1: currentData = myPublish.notStart; break;
      case 2: currentData = myPublish.underWay; break;
      case 3: currentData = myPublish.end; break;
    }
    this.setData({
      index: index,
      currentData: currentData
    });
  },/**跳转到比赛管理*/
  toMatchManager:function(e){
     var id = e.target.dataset.id;
     wx.navigateTo({
       url: '../matchManager/matchManager?id='+id
     })
  }, matchDetail:function(e){
    var id = e.target.dataset.id;
    if (id == null || id == "") {
      id = e.currentTarget.dataset.id;
    }

    wx.navigateTo({
      url: '../matchDetail/matchDetail?id=' + id
    })
  },
  /**查看成绩*/
  showScore: function (e) {
    console.log(e);
    var matchId = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: '../score/score?matchId=' + matchId
    })
  }
})