const app = getApp();

Page({

  /**
   * 页面的初始数据  1  5  9
   */
  data: {
    hidden: true,
    flag: false,
    x: 0,
    y: 0,
    data: [],
    disabled: true,
    elements: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var matchId = options.matchId;

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

        //如果当前成绩已经录入，而且当前轮次等于总轮次则不能保存座位
         if (settingInfo.currentTurn == settingInfo.totalTurn && settingInfo.currentTurnHasInputScore){
           app.showErrorMsg2("成绩已录入！",2500);
           setTimeout(function () {
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
            var _data = _res.data.resData;
            var data = [];
            var item;
            var index = 0;
            for (var i = 0; i < _data.length; i++) {
              item = new Object();
              item.index = ++index;
              item.name = _data[i].groupAUserAName;
              item.name2 = _data[i].groupAUserBName;
              item.number = _data[i].groupANumber;
              data.push(item);

              item = new Object();
              item.index = ++index;
              item.name = _data[i].groupBUserAName;
              item.name2 = _data[i].groupBUserBName;
              item.number = _data[i].groupBNumber;
              data.push(item);
            }

            that.setData({
              data: data
            });

            var query = wx.createSelectorQuery();
            var nodesRef = query.selectAll(".item");
            nodesRef.fields({
              dataset: true,
              rect: true

            }, (result) => {
              that.setData({
                elements: result
              })
            }).exec()
          }
        })
      }
    })
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

  },
  onPageScroll: function(obj) {

  },
  //长按
  _longtap: function(e) {
    const detail = e.detail;
    this.setData({
      x: e.currentTarget.offsetLeft,
      y: e.currentTarget.offsetTop
    })
    this.setData({
      hidden: false,
      flag: true
    })

  },
  //触摸开始
  touchs: function(e) {

    this.setData({
      beginIndex: e.currentTarget.dataset.index
    })
  },
  //触摸结束
  touchend: function(e) {
    if (!this.data.flag) {
      return;
    }

    const x = e.changedTouches[0].pageX
    const y = e.changedTouches[0].pageY
    const list = this.data.elements;
    let data = this.data.data
    for (var j = 0; j < list.length; j++) {
      const item = list[j];
      if (x > item.left && x < item.right && y > item.top && y < item.bottom) {
        const endIndex = item.dataset.index;
        const beginIndex = this.data.beginIndex;

        //  交换这两个元素
        if (endIndex != beginIndex) {
          var _item = this.data.data[beginIndex];
          this.data.data[beginIndex] = this.data.data[endIndex];
          this.data.data[endIndex] = _item;
          var _data = this.data.data;
          this.setData({
            data: _data
          });
        }

        /*
        //向后移动
        if (beginIndex < endIndex) {
          let tem = data[beginIndex];
          for (let i = beginIndex; i < endIndex; i++) {
            data[i] = data[i + 1]
          }
          data[endIndex] = tem;
        }
        //向前移动
        if (beginIndex > endIndex) {
          let tem = data[beginIndex];
          for (let i = beginIndex; i > endIndex; i--) {
            data[i] = data[i - 1]
          }
          data[endIndex] = tem;
        }
        */

        this.setData({
          data: data
        })
      }
    }
    this.setData({
      hidden: true,
      flag: false
    })
  },
  //滑动
  touchm: function(e) {
    if (this.data.flag) {
      const x = e.touches[0].pageX
      const y = e.touches[0].pageY
      this.setData({
        x: x - 90,
        y: y - 20
      })
    }
  },
  /**保存座位*/
  save: function() {
    wx.showLoading({
      title: '保存中...',
      mask: true
    })

    var matchId = this.data.matchId;
    var turn = this.data.settingInfo.currentTurn;
    var res = [];
    for (var i = 0; i < this.data.data.length; i++) {
      res.push(this.data.data[i].number);
    }
    var saveData = {};
    saveData.matchId = matchId;
    saveData.turn = turn;
    saveData.res = res;

    var url = app.serverUrl + 'match/manager/seat/save'
    wx.request({
      data: JSON.stringify(saveData),
      method: "POST",
      dataType: "JSON",
      header: {
        contentType: 'application/json;charset=UTF-8'
      },
      url: url,
      success: function(res) {
        wx.hideLoading();
        var data = JSON.parse(res.data);
        if (data.success) {
          wx.showToast({
            title: '保存成功！',
            mask: true,
            duration: 2500
          })

          setTimeout(function() {
            wx.navigateTo({
              url: '../matchManager/matchManager?id=' + matchId
            })
          }, 2500);
        } else {
          app.showErrorMsg("发生错误！");
        }


      }
    })
  }
})