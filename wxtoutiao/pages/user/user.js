const app = getApp()

Page({

  data: {
    userInfo: null,
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    withdraw: false,
    errorMsg: "",
    showErrorMsg: false,
    loginUserInfo: {},
    withdrawAmount: null
  },

  onLoad: function(options) {
    var that = this;
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          app.getLoginUserInfo(function(res) {
            that.setData({
              hasUserInfo: true,
              loginUserInfo: res,
              userInfo: res
            });
          });
        } else {
          app.getLoginUserInfo(function(res) {
            that.setData({
              hasUserInfo: false,
              loginUserInfo: res,
              userInfo: res
            });
          });
        }
      }
    })
  },
  onShow: function() {
    var that = this;
    wx.showLoading({})

    var userInfo = app.globalData.userInfo;
    if (userInfo != null) {
      this.setData({
        hasUserInfo: true,
        userInfo: userInfo
      });
    }

    app.getLoginUserInfo(function(loginUserInfo) {
      that.setData({
        loginUserInfo: loginUserInfo,
        withdraw: false
      });

      var totalTask = 0;

      var url = app.serverUrl + "user/centerInfo/" + loginUserInfo.id;
      wx.request({
        url: url,
        method: "POST",
        success: function(res) {
          totalTask++;

          if (totalTask == 2) {
            wx.hideLoading();
          }

          if (res.data.success) {
            var advertList = res.data.resData.advertList;
            for (var i = 0; i < advertList.length; i++) {
              advertList[i].linkUrl = escape(advertList[i].linkUrl); //encodeURI(advertList[i].linkUrl);
            }

            var myOrder = res.data.resData.myOrder;
            var myPublish = res.data.resData.myPublish;
            var orderNotStart = myOrder.notStart.length;
            var orderUnderWay = myOrder.underWay.length;
            var orderEnd = myOrder.end.length;

            var publishNotStart = myPublish.notStart.length;
            var publishUnderWay = myPublish.underWay.length;
            var publishEnd = myPublish.end.length;

            app.globalData.myPublish = myPublish;
            app.globalData.myOrder = myOrder;

            that.setData({
              advertList: advertList,
              orderNotStart: orderNotStart,
              orderUnderWay: orderUnderWay,
              orderEnd: orderEnd,
              publishNotStart: publishNotStart,
              publishUnderWay: publishUnderWay,
              publishEnd: publishEnd
            });
          } else {
            app.showErrorMsg("网络繁忙！");
          }
        }
      })


      //加载金额相关信息
      url = app.serverUrl + "user/income/" + loginUserInfo.id + "/0";
      wx.request({
        url: url,
        method: "POST",
        success: function(res) {
          console.log(res);
          totalTask++;
          if (totalTask == 2) {
            wx.hideLoading();
          }

          if (res.data.success) {
            that.setData({
              income: res.data.resData
            });
          } else {
            app.showErrorMsg("网络繁忙！");
          }

        }
      })
    });

  },
  getUserInfo: function(e) {
    var that = this;
    app.getUserInfoDo(e, function() {
      app.globalData.userInfo = e.detail.userInfo
      that.setData({
        userInfo: e.detail.userInfo,
        hasUserInfo: true
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
  /**显示我的报名*/
  myApply: function() {
    wx.navigateTo({
      url: '../myApply/myApply'
    })
  },
  /**显示我的发布*/
  myPublish: function() {
    wx.navigateTo({
      url: '../myPublish/myPublish'
    })
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

    if (val > this.data.income.availableWithdraw) {
      this.setData({
        showErrorMsg: true,
        errorMsg: "您最多可提现金额为：" + this.data.income.availableWithdraw + "元"
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
  toMyApply: function(e) {
    var index = e.currentTarget.dataset.idx;
    wx.navigateTo({
      url: '../myApply/myApply?index=' + index,
    })
  },
  toMyPublish: function(e) {
    var index = e.currentTarget.dataset.idx;
    wx.navigateTo({
      url: '../myPublish/myPublish?index=' + index,
    })
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

    if (this.data.withdrawAmount > this.data.income.availableWithdraw) {
      app.showErrorMsg("不得超过：" + this.data.income.availableWithdraw + "元");
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
  }

})