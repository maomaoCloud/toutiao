var WxSearch = require('../../wxSearchView/wxSearchView.js');
var amapFile = require('../../libs/amap-wx.js');
var httpclient = require('../../utils/httpclient.js')
const app = getApp();

var markersData = {
  latitude: '', //纬度
  longitude: '', //经度
  key: "a3787a876a9376e2c531ef1fac5248af", //申请的高德地图key
  wxcode: ""
};

Page({
  /**
   * 页面的初始数据
   */
  data: {
    date: '2016-09-01',
    region: ['江苏省', '苏州市', '虎丘区'],
    addActive: false,
    showMask: false,
    showLogin: false,
    animationData: {},
    currentPage: 1,
    pageSize: 10,
    hasMore: true,
    matchData: [],
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
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    showTip: true,
    isAgree: false,
    groupName: ""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.loadMatchData(false);
  },
  onShow: function() {
    this.setData({
      addActive: false,
      showMask: false
    });

    this.checkLogin();
    if (app.globalData.isReloadMatch != null && app.globalData.isReloadMatch) {
      var matchId = app.globalData.isReloadMatchId;
      for (var i = 0; i < this.data.matchData.length; i++) {
        if (this.data.matchData[i].id == matchId) {
          this.data.matchData[i].applyCount++;
          this.data.matchData[i].hasApply = true;
          break;
        }
      }

      app.globalData.isReloadMatch = false;
      var matchData = this.data.matchData;
      this.setData({
        matchData: matchData
      });
    }
  },
  /**上拉刷新*/
  onPullDownRefresh: function() {
    this.setData({
      currentPage: 1,
      hasMore: true
    });
    this.loadMatchData(true);
    wx.stopPullDownRefresh();
  },
  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {
    this.loadMatchData(false);
  },
  /**点击首页搜索框*/
  wxSearchHolder: function() {
    wx.navigateTo({
      url: '/pages/search/search'
    })
  },
  showApplyTip: function() {
    this.setData({
      showTip: false
    });
  },
  /**是否同意*/
  isAgree: function(e) {
    this.setData({
      isAgree: e.detail.value
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
        userIdCard: userIdCard,
        isAgree: false
      });

      //获取当前要申请的比赛的信息
      var price = parseInt(e.target.dataset.price);
      var id = e.target.dataset.id;
      var totalPrice = price;
      var applyMatchData = null;

      for (var i = 0; i < that.data.matchData.length; i++) {
        if (that.data.matchData[i].id == id) {
          applyMatchData = that.data.matchData[i];
          break;
        }
      }

      that.setData({
        applyPrice: price,
        applyId: id,
        applyMatchData: applyMatchData
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
  /**省市区联动*/
  bindRegionChange: function(e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      region: e.detail.value
    })
  },
  /**获取当前位置的经纬度*/
  loadLocationInfo: function() {
    var that = this;
    wx.getLocation({
      type: 'wgs84', //返回可以用于wx.openLocation的经纬度
      success: function(res) {
        var latitude = res.latitude //维度
        var longitude = res.longitude //经度
        console.log(res);
        that.loadCity(latitude, longitude);
      },
      fail: function(info) {
        console.log(info);
      }
    })
  },

  //把当前位置的经纬度传给高德地图，调用高德API获取当前地理位置，天气情况等信息
  loadCity: function(latitude, longitude) {
    var that = this;
    var myAmapFun = new amapFile.AMapWX({
      key: markersData.key
    });
    myAmapFun.getRegeo({
      location: '' + longitude + ',' + latitude + '', //location的格式为'经度,纬度'
      success: function(data) {
        var region = new Array();
        region[0] = data[0].regeocodeData.addressComponent.province;
        region[1] = data[0].regeocodeData.addressComponent.city;
        region[2] = data[0].regeocodeData.addressComponent.district;
        that.setData({
          region: region
        });
      },
      fail: function(info) {}
    })
  },
  /**扫一扫事件*/
  scan: function() {
    var that = this;
    var show;
    wx.scanCode({
      success: (res) => {
        try {
          JSON.parse(res.result);
        } catch (err) {
          app.showErrorMsg("无效二维码！");
          return;
        }
        var data = JSON.parse(res.result);
        var action = data.action;
        var id = data.id;

        if (data.action == "login") {
          //跳转登录页面
          wx.showLoading({})
          app.getLoginUserInfo(function(loginUserInfo) {
            var userId = loginUserInfo.id;
            var url = app.serverUrl + "wx/login/scan?id=" + id + "&userId=" + userId;
            wx.request({
              url: url,
              success: function(res) {
                wx.hideLoading();
                console.log(res);
                if (res.data.success) {
                  if (res.data.resData.isOk) {
                    wx.navigateTo({
                      url: '../login/login?userId=' + userId + "&id=" + id,
                    })
                  } else {
                    app.showErrorMsg(res.data.resData.msg);
                  }
                } else {
                  app.showErrorMsg("网络繁忙！");
                }
              },
              fail: function() {
                app.showErrorMsg("网络繁忙！");
              }
            })
          });
        } else if (data.action == "sign") {
          app.getLoginUserInfo(function(loginUserInfo) {
            wx.navigateTo({
              url: '../sign/sign?userId=' + loginUserInfo.id + '&id=' + data.id,
            })
          });
        } else {
          app.showErrorMsg("无效二维码");

        }
      },
      fail: (res) => {
        wx.showToast({
          title: '扫描失败',
          icon: 'none',
          duration: 2000
        })
      },
      complete: (res) => {}
    });
  },
  /**添加比赛 */
  addMatch: function() {
    wx.navigateTo({
      url: '../addMatch/addMatch'
    })
  },
  /**加载比赛信息*/
  loadMatchData: function(isReload) {
    if (!this.data.hasMore) {
      return;
    }

    var that = this;
    app.getLoginUserInfo(function(loginUserInfo) {
      var userId = loginUserInfo.id;
      var url = app.serverUrl + "match/" + (that.data.currentPage) + "/" + (that.data.pageSize) + "/" + userId;
      console.log(url);
      if (!that.data.hasMore) {
        return;
      }
      wx.showLoading({
        title: '加载中',
        icon: "loading"
      });
      wx.request({
        url: url,
        method: "POST",
        success: function(res) {
          var data = res.data;
          if (data.success) {
            var hasMore = data.resData.hasMore;
            var matchData = isReload ? data.resData.data : that.data.matchData.concat(data.resData.data);

            console.log(matchData);
            that.setData({
              hasMore: hasMore,
              matchData: matchData,
              currentPage: that.data.currentPage + 1
            });
          } else {
            app.showErrorMsg("网络繁忙！");
          }
          wx.hideLoading();
        },
        complete: function() {}
      });
    });
  },
  /**显示新闻详情*/
  showMatchDetail: function(e) {
    var id = e.target.dataset.id;
    wx.navigateTo({
      url: '../matchDetail/matchDetail?id=' + id
    })
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

      case "groupName":
        that.setData({
          groupName: val
        });
        break;
    }
  },
  /**立即报名*/
  applyDo: function() {
    wx.hideToast();
    //第一步检查报名信息
    if (this.data.applyMatchData.isNeedGroupName && (this.data.groupName == "" || this.data.groupName == undefined || this.data.groupName == null)) {
      app.showErrorMsg("请输入团队名称");
      return;
    }

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

    if (this.data.applyMatchData.isNeedIdCard && (this.data.userIdCard == "" || this.data.userIdCard == undefined || this.data.userIdCard == null || this.data.userIdCard.length != 18)) {
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

      if (this.data.applyMatchData.isNeedIdCard && (this.data.partnerIdCard == "" || this.data.partnerIdCard == undefined || this.data.partnerIdCard == null || this.data.partnerIdCard.length != 18)) {
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
    var groupName = this.data.groupName;

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
        userIdCard: userIdCard,
        groupName: groupName
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
            that.applySuccess(applyId);
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

    for (var i = 0; i < this.data.matchData.length; i++) {
      if (this.data.matchData[i].id == matchId) {
        this.data.matchData[i].applyCount++;
        this.data.matchData[i].hasApply = true;
        break;
      }
    }
    var matchData = this.data.matchData;
    this.setData({
      addActive: false,
      showMask: false,
      matchData: matchData
    });
  },
  /**跳转到比赛管理界面*/
  matchManager: function() {
    wx.navigateTo({
      url: '../matchManager/matchManager',
    })
  },
  /**我的报名*/
  myApply: function() {
    //加载用户信息
    wx.showLoading({});
    if (app.globalData.myOrder == null) {
      app.getLoginUserInfo(function(loginUserInfo) {
        var url = app.serverUrl + "user/centerInfo/" + loginUserInfo.id;
        wx.request({
          url: url,
          method: "POST",
          success: function(res) {
            if (res.data.success) {
              var myOrder = res.data.resData.myOrder;
              var myPublish = res.data.resData.myPublish;

              app.globalData.myPublish = myPublish;
              app.globalData.myOrder = myOrder;
              wx.hideLoading();
              wx.navigateTo({
                url: '../myApply/myApply'
              })
            } else {
              app.showErrorMsg("网络繁忙！");
            }
          }
        })
      });
    } else {
      wx.hideLoading();
      wx.navigateTo({
        url: '../myApply/myApply'
      })
    }
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
            } else {
              that.setData({
                showMask: false,
                showLogin: false,
              });
            }
          }
        })
      });
    }, 1000);

  }
})