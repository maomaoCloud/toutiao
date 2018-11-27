const app = getApp();

Page({
  data: {
    resData: {},
    match: {},
    withdraw: false,
    errorMsg: "",
    showErrorMsg: false,
    withdrawAmount: null,
    left: "",
    top: "",
  },
  /**切换比赛*/
  matchChange: function(e) {
    const val = e.detail.value[0];
    var match = this.data.resData.matchList[val];
    this.setData({
      match: match
    });
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    wx.showLoading({
      title: '加载中...'
    })

    const sysInfo = wx.getSystemInfoSync()
    var screenWidth = sysInfo.windowWidth
    var screenHeight = sysInfo.windowHeight
    this.setData({
      screenWidth: screenWidth,
      screenHeight: screenHeight
    });



    var match_id = options.id;
    var that = this;
    app.getLoginUserInfo(function(loginUserInfo) {
      var userId = loginUserInfo.id;
      var url = app.serverUrl + "match/manage/" + userId;

      wx.request({
        url: url,
        success: function(res) {
          wx.hideLoading();
          if (res.data.success) {
            var resData = res.data.resData;
            //查看是否有数据
            if (resData.matchList.length <= 0) {
              wx.showLoading({
                title: '您还未创建比赛！',
                mask: true
              })

              setTimeout(function() {
                wx.navigateBack({
                  data: 1
                });
              }, 1500);
            }


            //把index match 放到第一个
            var index = resData.index;

            if (match_id != undefined && match_id != "" && match_id != null) {
              for (var i = 0; i < resData.matchList.length; i++) {
                if (resData.matchList[i].id == match_id) {
                  index = i;
                  break;
                }
              }
            }

            var temMatch = resData.matchList[0];
            resData.matchList[0] = resData.matchList[index];
            resData.matchList[index] = temMatch;

            that.setData({
              resData: resData,
              match: resData.matchList[0]
            });
          } else {
            app.showErrorMsg("网络繁忙！");
          }
        }
      })

    });
  },
  /***输入提现金额改变的时候*/
  withdrawInputChange: function(e) {
    this.setData({
      showErrorMsg: false
    });
    var val = parseFloat(e.detail.value);
    if (val > 20000.00) {
      this.setData({
        showErrorMsg: true,
        errorMsg: "单笔金额不得超过2w"
      });
      return;
    }

    if (val > this.data.resData.availableWithdraw) {
      this.setData({
        showErrorMsg: true,
        errorMsg: "您最多可提现金额为：" + this.data.resData.availableWithdraw + "元"
      });
      return;
    }

    if (!isNaN(val)) {
      console.log(val);
      this.setData({
        withdrawAmount: val
      });
    }
  },
  applyDo: function() {
    if (this.data.withdrawAmount == null) {
      app.showErrorMsg("请输入提现金额");
      return;
    }

    if (this.data.withdrawAmount <= 0) {
      app.showErrorMsg("提现金额需大于0");
      return;
    }

    if (this.data.withdrawAmount > 20000.00) {
      app.showErrorMsg("单笔不得操作2w元");
      return;
    }

    if (this.data.withdrawAmount > this.data.resData.availableWithdraw) {
      app.showErrorMsg("不得超过：" + this.data.resData.availableWithdraw + "元");
      return;
    }

    var that = this;
    app.getLoginUserInfo(function(loginUserInfo) {
      var useId = loginUserInfo.id;
      var openId = loginUserInfo.openId;
      var money = that.data.withdrawAmount;
      var url = app.serverUrl + "wx/pay/withdraw?userId=" + useId + "&openId=" + openId + "&money=" + money;

      wx.showLoading({
        title: '提现处理中...'
      })
      wx.request({
        url: url,
        success: function(res) {
          wx.hideLoading();
          var data = res.data;
          if (data.success) {
            if (data.resData.isSuccess) {
              that.setData({
                withdraw: false
              });
              wx.showToast({
                title: '提现成功',
                duration: 2000,
                mask: true
              })

              setTimeout(function() {
                that.onShow(); //重新加载页面
              }, 2000);
            } else {
              app.showErrorMsg("提现失败！");
            }
          } else {
            wx.showToast({
              title: data.msg,
              icon: "none"
            })
          }
        }
      })
    });
  },
  /**提现*/
  withdraw: function() {
    wx.showLoading({});

    var that = this;

    wx.showLoading({});
    //加载用户信息
    var that = this;
    app.getLoginUserInfo(function(loginUserInfo) {
      wx.hideLoading();

      var animation = wx.createAnimation({
        duration: 200,
        timingFunction: 'linear'
      })
      that.animation = animation
      animation.translateY(550).step()
      that.setData({
        animationData: animation.export(),
        withdraw: true
      })

      setTimeout(function() {
        animation.translateY(0).step()
        that.setData({
          animationData: animation.export()
        })
      }, 200);
    });

  },
  /**隐藏弹出层*/
  hideMask: function() {
    var that = this;
    that.setData({
      withdraw: false
    });
  },




  /**显示历史比赛*/
  showHistoryMatch: function() {
    wx.showLoading({})
    app.getLoginUserInfo(function(loginUserInfo) {
      var url = app.serverUrl + "user/centerInfo/" + loginUserInfo.id;
      wx.request({
        url: url,
        method: "POST",
        success: function(res) {
          wx.hideLoading();
          if (res.data.success) {
            var myOrder = res.data.resData.myOrder;
            var myPublish = res.data.resData.myPublish;
            app.globalData.myPublish = myPublish;
            app.globalData.myOrder = myOrder;
            wx.navigateTo({
              url: '../myPublish/myPublish?index=3'
            })
          } else {
            app.showErrorMsg("网络繁忙！");
          }
        }
      })
    });
  },
  /**查看我的成绩*/
  showMyScore: function() {
    var matchId = this.data.match.id;
    wx.navigateTo({
      url: '../score/score?matchId=' + matchId,
    })
  },
  /**成绩录入*/
  showInputScore: function() {
    var matchId = this.data.match.id;
    wx.navigateTo({
      url: '../inputScore/inputScore?matchId=' + matchId,
    })
  },
  /**查看签到情况*/
  showSignDetail: function() {
    var matchId = this.data.match.id;
    wx.navigateTo({
      url: '../signDetail/signDetail?matchId=' + matchId,
    })
  },
  /**查看帮助中心*/
  showHelp: function() {
    wx.navigateTo({
      url: '../help/help'
    })
  },
  /***查看比赛设置*/
  showSetting: function() {
    var matchId = this.data.match.id;
    wx.navigateTo({
      url: '../setting/setting?matchId=' + matchId,
    })
  },
  /**编排座位*/
  sort: function() {
    var matchId = this.data.match.id;
    //查看当前座位第几轮
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
          return;
        }

        if (settingInfo.currentTurn == 0) {
          wx.navigateTo({
            url: '../sort/sort?matchId=' + matchId,
          })
        } else {
          wx.navigateTo({
            url: '../sort2/sort?matchId=' + matchId,
          })
        }
      }
    })
  },
  checkCanDo: function(matchId, fn) {
    var url = app.serverUrl + "match/manager/info/" + matchId;
    wx.showLoading({
      mask: true
    })

    wx.request({
      url: url,
      success: function(res) {
        wx.hideLoading();
        var settingInfo = res.data.resData;
        if (!settingInfo.hasSettingRule) {
          // 还没有设置比赛信息
          app.showErrorMsg2("请先设置比赛！", 2500);
          return;
        }

        if (settingInfo.currentTurn == 0) {
          app.showErrorMsg2("请排座位！", 2500);
          return;
        }

        fn(res);
      }
    })
  },
  move: function(e) {
    var left = e.touches[0].clientX - 30;
    var top = e.touches[0].clientY - 30;


    console.log(top);

    if (left < 5) return;
    if (left > this.data.screenWidth - 60) return;
    if (this.data.screenHeight - top <= 48) return;
    if (top < 0) return;

    this.setData({
      left: left,
      top: top
    })
  },toIndex:function(){
      wx.switchTab({
        url: '../match/match'
      })
  }
})