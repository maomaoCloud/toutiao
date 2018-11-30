//app.js
App({
  onLaunch: function() {
    this.getLoginUserInfo(function(res){
        console.log(res);
    });
  },
  getLoginUserInfo: function(fn) {
    var that = this;
    wx.getSetting({
      success: res => {
        if (!res.authSetting['scope.userInfo']) {
          that.reLogin(fn);
          return;
        }
      }
    })

    wx.checkSession({
      success: function() {
        //session_key 未过期，并且在本生命周期一直有效
        //查看本地的id是否过期
        wx.getStorage({
          key: 'loginUserInfo',
          success: function(res) {
            if (res.data == null) {
              that.reLogin(fn);
            } else {
              that.globalData.loginUserInfo = res.data;
              fn(res.data);
              return;
            }
          },fail:function(){
            that.reLogin(fn);
          }
        })
      },
      fail: function() {
        that.reLogin(fn);
      }
    })
  },
  globalData: {
    userInfo: null,
    loginUserInfo: null,
    sessionKey: null
  },
  //serverUrl: "https://match.tiaotiaopoker.com/",
  serverUrl: "http://127.0.0.1:8081/",
  /**显示错误提示*/
  showErrorMsg: function(msg) {
    wx.showToast({
      title: msg,
      image: "../../resource/error.png"
    })
  },
  showErrorMsg2: function (msg, duration) {
    wx.showToast({
      title: msg,
      image: "../../resource/error.png",
      duration: duration
    })
  },
  /**登录*/
  reLogin: function(fn) {
    var that = this;
    // 登录
    wx.login({
      success: res => {
        var url = that.serverUrl + "wx/sessionKey"
        wx.request({
          url: url,
          data: {
            code: res.code
          },
          success: function(res) {
            console.log(res);
            var data = res.data;
            if (data.success) {
              var userInfo = data.resData.userInfo;
              var sessionKey = data.resData.sessionKey;
              that.globalData.loginUserInfo = userInfo;
              that.globalData.sessionKey = sessionKey;

              wx.setStorage({
                key: 'loginUserInfo',
                data: userInfo
              })

              fn(userInfo);
            }
          },fail:function(){
            that.showErrorMsg("网络繁忙！");
          }
        })
      },fail:function(){
        that.showErrorMsg("登陆失败！");
      }
    });

    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        } else {
          console.log("还没有授权登陆....");
        }
      }
    })
  },
  getUserInfoDo: function(e,fn) {
    var that = this;
    that.getLoginUserInfo(function(loginUseInfo){
    
      var encryptedData = e.detail.encryptedData
      var iv = e.detail.iv
      var sessionKey = that.globalData.sessionKey

      var url = that.serverUrl + "wx/userInfo/update";
      var userId = loginUseInfo.id;

      that.globalData.userInfo = e.detail.userInfo

      wx.request({
        url: url,
        method: "POST",
        data: {
          encryptedData: encryptedData,
          sessionKey: sessionKey,
          iv: iv,
          userId: userId
        },
        success: function (res) {
          var data = res.data;
          if (data.success) {
            var userInfo = data.resData;
            that.globalData.loginUserInfo = userInfo;
          }
          fn();
        }
      })

    });
  }
})