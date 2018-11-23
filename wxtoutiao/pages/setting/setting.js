const app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    array: ["临近编排", "首尾编排", "拦腰编排"],
    rule: {
      matchChampionImg: null,
      matchSecondImg: null,
      matchThirdImg: null,
      matchLogo: null,
    },
    hasImg1: false,
    hasImg2: false,
    hasImg3: false,
    hasImg4: false,
    idx: 1,
    firstPx: "临近编排",
    checkRuleDraw: 1
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    wx.showLoading({
      title: '加载中...'
    })
    var matchId = options.matchId;
    //加载设置信息
    var url = app.serverUrl + "match/manager/setting/" + matchId;
    var that = this;
    wx.request({
      url: url,
      success: function(res) {
        wx.hideLoading();
        var data = res.data.resData;
        var hasImg1 = data.matchLogo != "" && data.matchLogo != null;
        var hasImg2 = data.matchChampionImg != "" && data.matchChampionImg != null;
        var hasImg3 = data.matchSecondImg != "" && data.matchSecondImg != null;
        var hasImg4 = data.matchThirdImg != "" && data.matchThirdImg != null;
        var idx = data.ruleSeat - 1;
        if(idx == -1) idx = 0;
      
        var firstPx = that.data.array[idx];
        var checkRuleDraw = 1;
        if (data.ruleDraw == 1) checkRuleDraw = 0;

        that.setData({
          hasImg1: hasImg1,
          hasImg2: hasImg2,
          hasImg3: hasImg3,
          hasImg4: hasImg4,
          idx: idx,
          firstPx: firstPx,
          checkRuleDraw: checkRuleDraw,
          rule: res.data.resData
        });
      }
    })

  },
  uploadImg: function(e) {
    var name = e.currentTarget.dataset.name;
    var that = this;
    app.getLoginUserInfo(function(loginUserInfo) {
      var userId = loginUserInfo.id;

      wx.chooseImage({
        success: function(res) {
          var tempFilePaths = res.tempFilePaths;
          var userId = app.globalData.loginUserInfo.id;
          var url = app.serverUrl + "tools/upload";
          wx.showLoading({
            title: '图片上传中...'
          });
          wx.uploadFile({
            url: url,
            filePath: tempFilePaths[0],
            name: 'file',
            formData: {
              'userId': userId
            },
            success: function(res) {
              var dataStr = res.data;
              var data = JSON.parse(dataStr);
              if (data.success) {
                var url = data.resData.fileUrl;
                var rule = that.data.rule;

                switch (name) {
                  case "matchChampionImg":
                    rule.matchChampionImg = url;
                    that.setData({
                      hasImg2: true
                    });
                    break;
                  case "matchSecondImg":
                    rule.matchSecondImg = url;
                    that.setData({
                      hasImg3: true
                    });
                    break;
                  case "matchThirdImg":
                    rule.matchThirdImg = url;
                    that.setData({
                      hasImg4: true
                    });
                    break;
                  case "matchLogo":
                    rule.matchLogo = url;
                    that.setData({
                      hasImg1: true
                    });
                    break;
                }

                that.setData({
                  rule: rule
                });
              }
            },
            complete: function() {
              wx.hideLoading();
            }
          })
        }
      })
    });
  },
  /**选择首轮编排方式*/
  bindPickerChange: function(e) {
    var idx = parseInt(e.detail.value);
    var firstPx = this.data.array[idx];
    this.setData({
      idx: idx,
      firstPx: firstPx
    });
  },
  /***提交保存**/
  formSubmit: function(e) {
    var form = e.detail.value;
    console.log(form);

    var rule = this.data.rule;

    if (form.matchName == "") {
      app.showErrorMsg("请填写比赛名称");
      return;
    }
    rule.matchName = form.matchName;

    if (form.matchChampion == "") {
      app.showErrorMsg("请填写一等奖");
      return;
    }
    rule.matchChampion = form.matchChampion;

    if (form.matchSecond == "") {
      app.showErrorMsg("请填写二等奖");
      return;
    }
    rule.matchSecond = form.matchSecond;

    if (form.matchThird == "") {
      app.showErrorMsg("请填写三等奖");
      return;
    }
    rule.matchThird = form.matchThird;

    if (form.ruleTurn == "") {
      app.showErrorMsg("请填写总轮次");
      return;
    }
    rule.ruleTurn = parseInt(form.ruleTurn);

    if (form.rulePair == "") {
      app.showErrorMsg("请填写每轮副数");
      return;
    }
    rule.rulePair = parseInt(form.rulePair);

    if (form.ruleTime == "") {
      app.showErrorMsg("请填写每轮时间");
      return;
    }
    rule.ruleTime = parseInt(form.ruleTime);


    if (form.ruleWin == "") {
      app.showErrorMsg("请填写胜积分");
      return;
    }
    rule.ruleWin = parseInt(form.ruleWin);

    if (form.ruleFail == "") {
      app.showErrorMsg("请填写负积分");
      return;
    }
    rule.ruleFail = parseInt(form.ruleFail);

    if (this.data.checkRuleDraw == 1) {
      if (form.ruleDraw == "") {
        app.showErrorMsg("请填写平积分");
        return;
      }
      rule.ruleDraw = parseInt(form.ruleDraw);
    } else {
      rule.ruleDraw = 1;
    }

    rule.ruleSeat = this.data.idx + 1;
    console.log(rule);

    var url = app.serverUrl + "match/manager/setting/save";
    var that = this;
    wx.showLoading({
      title: '保存中...',
      mask: true
    })
    wx.request({
      url: url,
      data: JSON.stringify(rule),
      method: "POST",
      dataType: "JSON",
      header: {
        contentType: 'application/json;charset=UTF-8'
      },
      success: function(res) {
        wx.hideLoading();
        var data = JSON.parse(res.data);
        if (data.success) {
          wx.showToast({
            title: '保存成功！',
            icon: 'none',
            duration: 2000,
            mask: true
          });

          setTimeout(function() {
            wx.navigateTo({
              url: '../matchManager/matchManager'
            })
          }, 2000);
        } else {
          that.showErrorMsg("提交失败！");
        }
      }
    })
  },
  /**设置是否可平*/
  setCanDraw: function(e) {
    var checkRuleDraw = e.detail.value ? 1 : 0;
    this.setData({
      checkRuleDraw: checkRuleDraw
    });
  }
})