const app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    array: ["选择", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "A+"],
    score: ["2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"],
    settingInfo: {
      canDraw: false
    },
    data: []
  },

  /**Ï
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var matchId = options.matchId;
    var userId = app.globalData.loginUserInfo.id;
    //加载是否可以继续输入成绩， 比如最后一轮已经输入了，就不能输入
    var that = this;

    wx.showLoading({
      title: '加载中...',
      mask: true
    })

    var url = app.serverUrl + "match/manager/info/" + matchId;
    wx.request({
      url: url,
      success: function(res) {
        var settingInfo = res.data.resData;
        if (!settingInfo.hasSettingRule) {
          // 还没有设置比赛信息
          app.showErrorMsg2("请先设置比赛！", 2500);
          setTimeout(function() {
            wx.navigateTo({
              url: '../matchManager/matchManager?id=' + matchId
            })
          }, 2500);
          return;
        }

        if (settingInfo.currentTurn == 0) {
          app.showErrorMsg2("请排座位！", 2500);
          setTimeout(function() {
            wx.navigateTo({
              url: '../matchManager/matchManager?id=' + matchId
            })
          }, 2500);
          return;
        }

        //如果当前成绩已经录入，而且当前轮次等于总轮次则不能保存座位
        if (settingInfo.currentTurn == settingInfo.totalTurn && settingInfo.currentTurnHasInputScore) {
          app.showErrorMsg2("成绩已录入！", 2500);
          setTimeout(function() {
            wx.navigateTo({
              url: '../matchManager/matchManager?id=' + matchId
            })
          }, 2500);
          return;
        }

        that.setData({
          settingInfo: settingInfo,
          matchId: matchId
        });

        url = app.serverUrl + "match/manager/seat/detail/" + matchId + "/" + settingInfo.currentTurn;
        wx.request({
          url: url,
          success: function(_res) {
            wx.hideLoading();
            var data = _res.data.resData;
            for (var i = 0; i < data.length; i++) {
              data[i].idxA = 0;
              data[i].idxB = 0;
            }

            that.setData({
              data: data
            });
          }
        })
      }
    })

    //2 加载当前轮的成绩
    //3.加载是否可以平局
  },
  bindPickerChange: function(e) {
    console.log(e);
    var idx = parseInt(e.detail.value);
    var dataIdx = parseInt(e.currentTarget.dataset.zh) - 1;
    var group = e.currentTarget.dataset.group;

    var data = this.data.data;
    var canDraw = this.data.settingInfo.canDraw;
    if (group == "A") {
      //检查当前的值和队友的值是否一致
      if (!canDraw && data[dataIdx].idxB != 0 && idx == data[dataIdx].idxB) {
        app.showErrorMsg("级数不可平！");
        return;
      }
      data[dataIdx].idxA = idx;
    } else {
      //检查当前的值和队友的值是否一致
      if (!canDraw && data[dataIdx].idxA != 0 && idx == data[dataIdx].idxA) {
        app.showErrorMsg("级数不可平！");
        return;
      }
      data[dataIdx].idxB = idx;
    }

    this.setData({
      data: data
    });
  },
  save: function() {
    //坚持是否都输入了
    if (this.data.data.length <= 0) return;

    var resData = new Object();
    resData.matchId = this.data.matchId;
    resData.turnNumber = this.data.settingInfo.currentTurn;
    var id = [];
    var teamOneId = [];
    var teamOneScore = [];
    var teamTwoId = [];
    var teamTwoScore = [];

    for (var i = 0; i < this.data.data.length; i++) {
      if (this.data.data[i].idxA == 0 || this.data.data[i].idxB == 0) {
        app.showErrorMsg("请输入全部成绩！");
        return;
      }

      id.push(this.data.data[i].id);

      teamOneId.push(this.data.data[i].groupAId);
      teamTwoId.push(this.data.data[i].groupBId);

      teamOneScore.push(this.data.score[this.data.data[i].idxA - 1]);
      teamTwoScore.push(this.data.score[this.data.data[i].idxB - 1]);
    }

    resData.id = id.join(",");
    resData.teamOneId = teamOneId.join(",");
    resData.teamTwoId = teamTwoId.join(",");
    resData.teamOneScore = teamOneScore.join(",");
    resData.teamTwoScore = teamTwoScore.join(",");

    var that = this;
    var url = app.serverUrl + "match/manager/score/save";
    wx.showLoading({
      title: '保存中...',
      mask: true
    })

    wx.request({
      url: url,
      data: JSON.stringify(resData),
      method: "POST",
      dataType: "JSON",
      header: {
        contentType: 'application/json;charset=UTF-8'
      },
      success: function(res) {
        wx.hideLoading();
        console.log(res);
        if (JSON.parse(res.data).success) {
          wx.showToast({
            title: '保存成功！',
            mask: true,
            duration: 2000
          })

          setTimeout(function() {
            wx.navigateTo({
              url: '../matchManager/matchManager?id=' + that.data.matchId,
            })
          }, 2000);
        } else {
          app.showErrorMsg("保存失败！");
        }
      }
    })

  }
})