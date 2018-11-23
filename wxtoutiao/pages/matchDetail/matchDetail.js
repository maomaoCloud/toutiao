var WxParse = require('../../wxParse/wxParse.js');
const app = getApp();

Page({
  /**
   * 页面的初始数据
   */
  data: {
    id: "",
    loadCompleted: false,
    tabIndex: 1,
    addActive: false,
    showMask: false,
    showLogin: false,
    animationData: {},
    userHead: "../../resource/head.png",
    userName: "",
    userPhone: "",
    hasPartner: false,
    hasPartnerHead: false,
    partnerHead: "",
    partnerName: "",
    partnerPhone: "",
    userIdCard: "",
    partnerIdCard: "",
    applyPrice: 0,
    applyId: "",
    applyCount: 1,
    matchInfo: {
      headImg: "https://modao.cc/uploads3/images/2344/23443797/raw_1533194816.png",
      theme: "这里是活动的主题",
      startDateTime: "2018-10-02 15:00",
      endDateTime: "2018-11-02 18:00",
      address: "江苏省昆山市张浦镇花苑路688号",
      activeContent: "哈哈哈哈哈哈哈哈哈哈哈",
      rewardsContent: "奖品介绍奖品介绍奖品介绍奖品介绍奖品介绍",
      contactName: "谢康",
      contactPhone: "18012673347",
      wxHead: "https://api.kikistudio.cn/static/JxdfG5H/Lewud4AA.jpg",
      fee: 10,
      applyList: []
    },
    showTip: true,
    isAgree: false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.checkLogin();
    var id = options.id;
    this.setData({
      id: id,
      isAgree: false
    });

    this.loadMatchDetail(id);
  },
  onShareAppMessage: function() {
    var that = this;
    return {
      title: that.data.matchInfo.theme,
      path: "/pages/matchDetail/matchDetail?id=" + that.data.id
    };
  },
  /**记载比赛详细信息*/
  loadMatchDetail(id) {

    var that = this;

    wx.showLoading({
      title: '加载中...'
    });

    app.getLoginUserInfo(function(loginUserInfo) {
      var url = app.serverUrl + "/match/detail/" + id + "/" + loginUserInfo.id;
      wx.request({
        url: url,
        method: "GET",
        success: function(res) {
          wx.hideLoading();
          console.log(res.data);
          if (res.data.success) {
            //activeContent rewardsContent
            WxParse.wxParse('activeContent', 'html', res.data.resData.data.activeContent, that, 0);
            WxParse.wxParse('rewardsContent', 'html', res.data.resData.data.rewardsContent, that, 0);

            that.setData({
              loadCompleted: true,
              matchInfo: res.data.resData.data
            });
          } else {
            app.showErrorMsg("加载失败...");
          }
        }
      })
    });

  },
  /**返回首页*/
  gotoIndex: function() {
    wx.switchTab({
      url: '../match/match'
    })
  },
  /**切换tab*/
  switchTab: function(e) {
    var index = parseInt(e.target.dataset.index);
    this.setData({
      tabIndex: index
    });
  },

  showApplyTip: function() {
    this.setData({
      showTip: false
    });
  },
  confirm: function() {
    this.setData({
      showTip: true
    });
  },
  /**选择首页添加活动事件*/
  apply: function(e) {
    var that = this;

    wx.showLoading({});
    //加载用户信息
    var that = this;
    app.getLoginUserInfo(function(loginUserInfo) {
      wx.hideLoading();
      var phone = loginUserInfo.phone;
      var trueName = loginUserInfo.trueName;
      var userHead = loginUserInfo.avatarUrl;
      var userIdCard = loginUserInfo.userIdCard;

      if (userHead == null || userHead == undefined || userHead == "") {
        userHead = "../../resource/head.png";
      }
      that.setData({
        userName: trueName,
        userPhone: phone,
        userHead: userHead,
        userIdCard: userIdCard
      });

      //获取当前要申请的比赛的信息
      var price = parseInt(e.target.dataset.price);
      var id = e.target.dataset.id;
      var totalPrice = price;

      that.setData({
        applyPrice: price,
        applyId: id
      });

      var animation = wx.createAnimation({
        duration: 200,
        timingFunction: 'linear'
      })
      that.animation = animation
      animation.translateY(550).step()
      that.setData({
        animationData: animation.export(),
        addActive: true,
        showMask: true
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
      addActive: false,
      showMask: false,
      showLogin: false
    });
  },
  /**选择是否有搭档*/
  hasPartner: function(e) {
    var val = e.detail.value;
    var hasPartnerVal = val;
    var applyCount = val ? 2 : 1;

    this.setData({
      hasPartner: hasPartnerVal,
      applyCount: applyCount
    });

  },
  /**是否同意*/
  isAgree: function(e) {
    this.setData({
      isAgree: e.detail.value
    });
  },
  /**获取用户输入的数据*/
  getInputValue: function(e) {
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

      case "partnerName":
        that.setData({
          partnerName: val
        });
        break;

      case "partnerPhone":
        that.setData({
          partnerPhone: val
        });
        break;

      case "userIdCard":
        that.setData({
          userIdCard: val
        });
        break;

      case "partnerIdCard":
        that.setData({
          partnerIdCard: val
        });
        break;
    }
  },
  /**立即报名*/
  applyDo: function() {
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

    if (this.data.hasPartner) {
      if (this.data.partnerName == "" || this.data.partnerName == undefined || this.data.partnerName == null) {
        app.showErrorMsg("请输入搭档姓名");
        return;
      }

      if (this.data.partnerPhone == "" || this.data.partnerPhone == undefined || this.data.partnerPhone == null) {
        app.showErrorMsg("请输入搭档手机号");
        return;
      }

      if (!this.data.partnerPhone.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|19[0-9]|11[0-9]|12[0-9]|14[57]|16[0-9])[0-9]{8}$/)) {
        app.showErrorMsg("手机格式有误!");
        return;
      }

      if (this.data.partnerIdCard == "" || this.data.partnerIdCard == undefined || this.data.partnerIdCard == null || this.data.partnerIdCard.length != 18) {
        app.showErrorMsg("身份证号有误！");
        return;
      }
    }

    if (!this.data.isAgree) {
      app.showErrorMsg("请同意报名须知");
      return;
    }

    var that = this;
    var userInfo = app.globalData.loginUserInfo;
    var applyId = this.data.applyId;
    var userId = userInfo.id;
    var openId = userInfo.openId;
    var applyCount = this.data.applyCount;
    var url = app.serverUrl + "match/apply";
    var userName = this.data.userName;
    var userPhone = this.data.userPhone;
    var partnerName = this.data.partnerName;
    var partnerPhone = this.data.partnerPhone;
    var hasPartner = this.data.hasPartner;
    var userHead = this.data.userHead;
    var partnerHead = this.data.partnerHead;
    var userIdCard = this.data.userIdCard;
    var partnerIdCard = this.data.partnerIdCard;

    wx.showLoading({
      title: '提交报名中...'
    });
    wx.request({
      url: url,
      method: "POST",
      data: {
        userId: userId,
        openId: openId,
        applyCount: applyCount,
        matchId: applyId,
        userName: userName,
        userPhone: userPhone,
        partnerName: partnerName,
        partnerPhone: partnerPhone,
        userHead: userHead,
        partnerHead: partnerHead,
        hasPartner: hasPartner,
        partnerIdCard: partnerIdCard,
        userIdCard: userIdCard
      },
      success: function(res) {
        wx.hideLoading();
        var data = res.data;
        if (data.success) {
          if (data.resData.hasLimit) {
            app.showErrorMsg("报名人数已满！");
            return;
          }

          if (data.resData.hasError) {
            app.showErrorMsg(data.resData.msg);
            return;
          }

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
                  that.applySuccess(applyId);
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
            //不需要支付，直接跳转成功
            that.applySuccess();
          }
        } else {
          app.showErrorMsg("报名失败，请重试！");
        }
      }
    })

  },
  /**报名成功，跳转到用户中心*/
  applySuccess: function(matchId) {
    //跳转到用户中心
    wx.showToast({
      title: '报名成功！'
    });

    app.globalData.isReloadMatchId = matchId;
    app.globalData.isReloadMatch = true;

    wx.switchTab({
      url: '../match/match'
    })
  },
  getUserInfoDo: function(e) {
    var that = this;
    app.getUserInfoDo(e, function() {
      that.setData({
        showMask: false,
        showLogin: false,
      });
    });
  },
  checkLogin: function() {
    var that = this;
    setTimeout(function() {
      app.getLoginUserInfo(function(loginUserInfo) {
        wx.getSetting({
          success: function(res) {
            if (!res.authSetting['scope.userInfo']) {
              that.setData({
                showMask: true,
                showLogin: true,
              });
            }
          }
        })
      });
    }, 1000);

  }
})