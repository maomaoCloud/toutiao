const app = getApp();
Page({
  /**
   * 页面的初始数据
   */
  data: {
    canSgin: false,
    select: false,
    showAUsers: false,
    avatarUrl: null,
    userId: null,
    id: null,
    theme: null,
    fee: 0,
    height: 0,
    search: false,
    aUsers: [],
    showSeeAllBtn: false,
    showConfirmBtn: false,
    users: null,
    selectUserId: "",
    showData: false,

    addActive: false,
    animationData: {},
    userName: "",
    userPhone: "",
    userIdCard:""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    wx.showLoading({
      title: '加载签到信息中...',
      mask: true
    })

    var userId = options.userId;
    var id = options.id;
    var avatarUrl = app.globalData.loginUserInfo.avatarUrl;
    var height = wx.getSystemInfoSync().windowHeight;
    var userIdCard = app.globalData.loginUserInfo.userIdCard;


    this.setData({
      userId: userId,
      id: id,
      avatarUrl: avatarUrl,
      height: height - 100,
      userName: app.globalData.loginUserInfo.trueName,
      userPhone: app.globalData.loginUserInfo.phone,
      userIdCard: userIdCard
    });

    var that = this;
    //发送请求获取签到信息
    var url = app.serverUrl + "match/sign/userId/" + userId + "/id/" + id;
    wx.request({
      url: url,
      success: function(res) {
        wx.hideLoading();
        var data = res.data;
        if (data.success) {
          that.setData({
            theme: res.data.resData.matchTheme,
            fee: res.data.resData.fee
          });

          if (data.resData.hasApply) {
            if (!data.resData.canSign) {
              app.showErrorMsg("您已签到！");
              setTimeout(function() {
                wx.navigateBack({
                  delta: 1
                })
              }, 1500);
            } else {
              //这里可以签到
              that.setData({
                canSgin: true
              });
            }
          } else {
            //B用户或者没有报名的用户
            console.log(data.resData.aUsers);
            that.setData({
              canSgin: false,
              select: true,
              aUsers: data.resData.aUsers
            });

          }

        } else {
          app.showErrorMsg("网络繁忙！");
          setTimeout(function() {
            wx.navigateBack({
              delta: 1
            })
          }, 1500);
        }
      }
    })

  },
  /**获取用户输入的数据*/
  getInputValue: function (e) {
    var name = e.target.dataset.name;
    var val = e.detail.value;
    var that = this;

    switch (name) {
      case "userName":
        that.setData({
          userName: val
        });
        break;

      case "userPhone":
        that.setData({
          userPhone: val
        });
        break;

      case "userIdCard":
        that.setData({
          userIdCard: val
        });
        break;
    }
  },
  /**点击取消之后*/
  cancel: function() {
    wx.navigateBack({
      data: 1
    });
  },
  /**确认签到*/
  sureSign: function() {
    var userId = this.data.userId;
    var id = this.data.id;
    var url = app.serverUrl + "/match/sign/confirm?userId=" + userId + "&id=" + id;
    wx.showLoading({
      mask: true
    })
    wx.request({
      url: url,
      success: function(res) {
        wx.hideLoading();
        if (res.data.success) {
          wx.showLoading({
            title: "签到成功！",
            mask: true
          })
          setTimeout(function() {
            wx.hideLoading();
            wx.switchTab({
              url: '../match/match'
            })
          }, 2000);
        } else {
          app.showErrorMsg("网络繁忙");
        }

      },
      fail: function() {
        wx.hideLoading();
        app.showErrorMsg("网络繁忙");
      }
    })
  },
  //显示搜索页面
  toSelect: function() {
    this.setData({
      canSgin: false,
      select: false,
      search: true
    });
  },
  search: function(e) {
    var name = e.detail.value.name;
    var phone = e.detail.value.phone;

    if(name == "" || name ==null){
      wx.showToast({
        title: '请输入您的姓名！',
        icon:"none"
      })
      return;
    }

    if (phone == "" || phone == null) {
      wx.showToast({
        title: '请输入您的手机号',
        icon: "none"
      })
      return;
    }

    var users = new Array();

    for (var i = 0; i < this.data.aUsers.length; i++) {
      if (this.data.aUsers[i].partnerName.indexOf(name) != -1 || this.data.aUsers[i].partnerPhone.indexOf(phone) != -1) {
        users.push(this.data.aUsers[i]);
      }
    }

    var firstId = "";
    if(users.length>0){
      firstId = users[0].userId;
    }
    this.setData({
      users: users,
      showSeeAllBtn: true,
      showData: true,
      selectUserId:firstId,
      height: -1
    });

  },
  select: function(e) {
    var id = e.currentTarget.dataset.id;
    if (id == undefined || id == null || id == "") {
      id = e.target.dataset.id;
    }

    this.setData({
      selectUserId: id
    });
  },
  showAll: function() {
    var users = this.data.aUsers;
    var selectUserId ="";
    if(users.length>0){
      selectUserId = users[0].userId;
    }
    this.setData({
      users: users,
      showSeeAllBtn: false,
      showData: true,
      selectUserId: selectUserId,
      height:-1
    });
  },
  bSign: function() {
    if (this.data.selectUserId == "" || this.data.selectUserId == null) {
      app.showErrorMsg("请选择搭档！");
      return;
    }

    var userId = this.data.userId;
    var id = this.data.id;
    var aId = this.data.selectUserId;
    var url = app.serverUrl + "match/bSign/userId/" + userId + "/aId/" + aId + "/id/" + id;

    wx.showLoading({
      title: '签到中...',
      mask: true
    })

    wx.request({
      url: url,
      success: function(res) {
        wx.hideLoading();
        if (res.data.success) {
          wx.showLoading({
            title: "签到成功！",
            mask: true
          })
          setTimeout(function() {
            wx.hideLoading();
            wx.switchTab({
              url: '../match/match'
            })
          }, 2000);
        } else {
          app.showErrorMsg("网络繁忙");
        }
      },
      fail: function() {
        wx.hideLoading();
        app.showErrorMsg("网络繁忙");
      }
    })
  },
  toApply: function() {
    var that = this;

    var animation = wx.createAnimation({
      duration: 200,
      timingFunction: 'linear'
    })
    that.animation = animation
    animation.translateY(550).step()
    that.setData({
      animationData: animation.export(),
      addActive: true
    })

    setTimeout(function() {
      animation.translateY(0).step()
      that.setData({
        animationData: animation.export()
      })
    }, 200);
  },
  doApply: function() {
    wx.hideToast();
    //第一步检查报名信息
    if (this.data.userName == "" || this.data.userName == undefined || this.data.userName == null) {
      app.showErrorMsg("请输入姓名");
      return;
    }

    if (this.data.userPhone == "" || this.data.userPhone == undefined || this.data.userPhone == null) {
      app.showErrorMsg("请输入手机号");
      return;
    }

    if (!this.data.userPhone.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|19[0-9]|11[0-9]|12[0-9]|14[57]|16[0-9])[0-9]{8}$/)) {
      app.showErrorMsg("手机格式有误!");
      return;
    }

    if (this.data.userIdCard == "" || this.data.userIdCard == undefined || this.data.userIdCard == null || this.data.userIdCard.length != 18) {
      app.showErrorMsg("身份证号有误！");
      return;
    }

    var that = this;
    var userInfo = app.globalData.loginUserInfo;
    var applyId = this.data.id;
    var userId = userInfo.id;
    var openId = userInfo.openId;
    var url = app.serverUrl + "match/sign/apply/";
    var userName = this.data.userName;
    var userPhone = this.data.userPhone;
    var userHead = this.data.avatarUrl;
    var userIdCard = this.data.userIdCard;

    wx.showLoading({
      title: '提交报名中...',
      mask: true
    });

    wx.request({
      url: url,
      method: "POST",
      data: {
        userId: userId,
        openId: openId,
        matchId: applyId,
        userName: userName,
        userPhone: userPhone,
        userHead: userHead,
        userIdCard: userIdCard
      },
      success: function(res) {
        wx.hideLoading();
        if (res.data.success) {
          var data = res.data;
          if (data.resData.hasPay) {
            app.showErrorMsg("您已经支付！");
            return;
          }
          if (data.resData.needPay) {
            var unifiedorder = data.resData.unifiedorder;
            var payData = data.resData.payment;
            if (unifiedorder) {
              wx.requestPayment({
                timeStamp: payData.timeStamp,
                nonceStr: payData.nonceStr,
                package: "prepay_id=" + payData.package,
                signType: payData.signType,
                paySign: payData.paySign,
                success: function(payRes) {
                  //支付成功，提示正在审核
                  wx.showToast({
                    title: '报名成功，等待审核！',
                    icon:"none"
                  })

                  setTimeout(function() {
                    wx.switchTab({
                      url: '../match/match',
                    })
                  }, 1500);
                },
                fail: function(res) {
                  if (res.errMsg == "requestPayment:fail cancel") {
                    wx.showToast({
                      title: '您已取消支付'
                    })
                  } else {
                    wx.showToast({
                      title: '支付异常：' + res.errMsg
                    })
                  }
                }
              })
            } else {
              //下单失败
              app.showErrorMsg("报名失败，请重试！");
            }
          } else {
            //不需要支付，直接跳转成功,提示支付成功，并且告诉找管理人员通过审核
            wx.showToast({
              title: '报名成功，等待审核！',
              icon: "none"
            })

            setTimeout(function () {
              wx.switchTab({
                url: '../match/match',
              })
            }, 1500);
          }

        } else {
          app.showErrorMsg("网络繁忙");
        }

      },
      fail: function() {
        wx.hideLoading();
        app.showErrorMsg("网络繁忙");
      }
    })

  },
  /**隐藏弹出层*/
  hideMask: function () {
    var that = this;
    that.setData({
      addActive: false
    });
  }
})