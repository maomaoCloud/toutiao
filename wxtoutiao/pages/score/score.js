const app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    name: ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十"]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var matchId = options.matchId;
    this.setData({
      matchId: matchId
    });

    

  },
  onShow:function(){
    var matchId = this.data.matchId;
    var that = this;

    wx.showLoading({
      title: '加载中...',
      mask: true
    })

    var url = app.serverUrl + "match/manager/info/" + matchId;
    wx.request({
      url: url,
      success: function (res) {
        var settingInfo = res.data.resData;
        if (!settingInfo.hasSettingRule) {
          // 还没有设置比赛信息
          app.showErrorMsg2("请先设置比赛！", 2500);
          setTimeout(function () {
            wx.navigateTo({
              url: '../matchManager/matchManager?id=' + matchId
            })
          }, 2500);
          return;
        }

        if (settingInfo.currentTurn == 0) {
          app.showErrorMsg2("请先排座位！", 2500);
          setTimeout(function () {
            wx.navigateTo({
              url: '../matchManager/matchManager?id=' + matchId
            })
          }, 2500);
          return;
        }

        //如果当前成绩已经录入，而且当前轮次等于总轮次则不能保存座位
        if (settingInfo.currentTurn == 0 && !settingInfo.currentTurnHasInputScore) {
          app.showErrorMsg2("还未录入成绩！", 2500);
          setTimeout(function () {
            wx.navigateTo({
              url: '../matchManager/matchManager?id=' + matchId
            })
          }, 2500);
          return;
        }

        if (settingInfo.currentTurn > 1 && !settingInfo.currentTurnHasInputScore) {
          settingInfo.currentTurn = settingInfo.currentTurn - 1;
        }

        that.setData({
          settingInfo: settingInfo,
          matchId: matchId
        });

        url = app.serverUrl + "match/manager/score/show/" + matchId + "/" + settingInfo.currentTurn;
        wx.request({
          url: url,
          success: function (_res) {
            wx.hideLoading();
            var nameList = _res.data.resData.nameList;
            var dataList = _res.data.resData.apiResultList;
            var score;
            var scoreData;

            if (dataList.length <= 0){
              app.showErrorMsg2("还未录入成绩！", 2500);
              setTimeout(function () {
                wx.navigateTo({
                  url: '../matchManager/matchManager?id=' + matchId
                })
              }, 2500);
            }

            for (var i = 0; i < dataList.length; i++) {
              score = dataList[i].resultString.split(",");

              scoreData = [];
              for (var j = 0; j < score.length; j++) {
                scoreData.push({
                  t: nameList[j],
                  v: score[j]
                });
              }
              dataList[i].scoreData = scoreData;
            }

            that.setData({
              dataList: dataList
            });
          }
        })
      }
    })
  }

})