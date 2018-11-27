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
        elements: [],
        settingInfo: {}
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        //加载比赛信息
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

          that.setData({
            settingInfo: settingInfo,
            matchId: matchId
          });


          url = app.serverUrl + "match/manager/seat/firstTurn/" + matchId;
          wx.request({
            url: url,
            success: function (_res) {
              wx.hideLoading();
              console.log(_res);

              var _data = _res.data.resData;
              var index = 0;
              for (var i = 0; i < _data.length; i++) {
                _data[i].index = ++index;
              }

              that.setData({
                data: _data
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

                console.log(beginIndex);
                console.log(endIndex);

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
            console.log(y);
            this.setData({
                x: x - 45,
                y: y - 20
            })
        }
    },
    save: function() {
        var resData = new Object();
        resData.matchId = this.data.matchId;
        resData.turn = 1;
        resData.userIds = [];
        resData.names = [];
        resData.heads = [];

        for (var i = 0; i < this.data.data.length; i++) {
            resData.userIds.push(this.data.data[i].userId);
            resData.names.push(this.data.data[i].name);
            resData.heads.push(this.data.data[i].head);
        }

        var url = app.serverUrl + "match/manager/seat/save";
        wx.showLoading({
            title: '保存中...',
            mask: true
        })
        var that = this;
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