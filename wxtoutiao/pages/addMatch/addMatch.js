var WxSearch = require('../../wxSearchView/wxSearchView.js');
var amapFile = require('../../libs/amap-wx.js');
const app = getApp();

var markersData = {
  latitude: '', //纬度
  longitude: '', //经度
  key: "a3787a876a9376e2c531ef1fac5248af" //申请的高德地图key
};

Page({
  /**
   * 页面的初始数据
   */
  data: {
    region: ['江苏省', '苏州市', '虎丘区'],
    startDate: null,
    startTime: "15:00",
    endDate: null,
    endTime: "18:00",
    theme: null,
    hasImg1: false,
    img1Url: null,
    hasImg2: false,
    img2Url: null,
    phone: "",
    name: ""
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.loadLocationInfo();
    this.initInfo();

    var that = this;
    app.getLoginUserInfo(function(loginUserInfo) {
      var phone = loginUserInfo.phone;
      if (phone == null) {
        phone = "";
      }
      var name = loginUserInfo.trueName;
      if (name == null) {
        name = "";
      }
      var wxQrImg = loginUserInfo.wxQrImg;
      var hasImg2 = true;
      if (wxQrImg == null || wxQrImg == "") {
        hasImg2 = false;
      }
      that.setData({
        phone: phone,
        name: name,
        hasImg2: hasImg2,
        img2Url: wxQrImg
      });

    });

  },
  /**省市区联动*/
  bindRegionChange: function(e) {
    this.setData({
      region: e.detail.value
    })
  },
  /**时间联动*/
  bindTimeChange: function(e) {
    var target = e.target.dataset.type;
    var val = e.detail.value;
    if (target == "start") {
      this.setData({
        startTime: val
      });
    } else {
      this.setData({
        endTime: val
      });
    }
  },
  /**日期联动*/
  bindDateChange: function(e) {
    var target = e.target.dataset.type;
    var val = e.detail.value;
    if (target == "start") {
      this.setData({
        startDate: val
      });
    } else {
      this.setData({
        endDate: val
      });
    }
  },
  /**初始化页面数据*/
  initInfo: function() {
    var myDate = new Date();
    var year = myDate.getFullYear();
    var month = myDate.getMonth() + 1;
    var day = myDate.getDate();
    var startDate = year + "-" + month + "-" + day;

    month = month + 1;
    if (month > 12) {
      month = 1;
      year += 1;
    }
    var endDate = year + "-" + month + "-" + day;

    this.setData({
      startDate: startDate,
      endDate: endDate
    });

  },
  /**获取当前位置的经纬度*/
  loadLocationInfo: function() {
    var that = this;
    wx.getLocation({
      type: 'wgs84', //返回可以用于wx.openLocation的经纬度
      success: function(res) {
        var latitude = res.latitude //维度
        var longitude = res.longitude //经度
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
        console.log(data);
        var region = new Array();
        region[0] = data[0].regeocodeData.addressComponent.province;
        region[1] = data[0].regeocodeData.addressComponent.city;
        if (region[1] == null || region[1] == [] || region[1] == "") {
          region[1] = region[0];
        }
        region[2] = data[0].regeocodeData.addressComponent.district;
        that.setData({
          region: region
        });
      },
      fail: function(info) {}
    })
  },
  /**上传图片*/
  uploadImg: function() {
    var that = this;
    wx.chooseImage({
      success: function(res) {
        var tempFilePaths = res.tempFilePaths;
        app.getLoginUserInfo(function(loginUserInfo) {
          var userId = loginUserInfo.id;
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
                that.setData({
                  hasImg1: true,
                  img1Url: url
                });
              }
            },
            complete: function() {
              wx.hideLoading();
            }
          })
        });
      }
    })
  },
  uploadWxQr: function() {
    var that = this;
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
              that.setData({
                hasImg2: true,
                img2Url: url
              });
            }
          },
          complete: function() {
            wx.hideLoading();
          }
        })
      }
    })
  },
  /**提交表单*/
  formSubmit: function(e) {
    console.log(app.globalData);
    var form = e.detail.value;
    form.headImg = this.data.img1Url;
    form.wxHead = this.data.img2Url;
    form.startDate = this.data.startDate + " " + this.data.startTime + ":00";;
    form.endDate = this.data.endDate + " " + this.data.endTime + ":00";
    form.province = this.data.region[0];
    form.city = this.data.region[1];
    form.area = this.data.region[2];
    form.bannerImg = this.data.img1Url;
    form.userId = app.globalData.loginUserInfo.id;

    if (form.theme == null || form.theme == "") {
      app.showErrorMsg("请填写活动主题!");
      return;
    }

    if (form.address == null || form.address == "") {
      app.showErrorMsg("请填写详细地址!");
      return;
    }

    if (form.activeContent == "" || form.activeContent == null) {
      app.showErrorMsg("请输入活动介绍!");
      return;
    }

    if (form.rewardsContent == "" || form.rewardsContent == null) {
      app.showErrorMsg("请输入奖品介绍!");
      return;
    }

    if (form.contactName == "" || form.contactName == null) {
      app.showErrorMsg("请输入联系人!");
      return;
    }

    if (form.phone == "" || form.phone == null) {
      app.showErrorMsg("请输入手机号!");
      return;
    }

    if (!form.phone.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|19[0-9]|14[57])[0-9]{8}$/)) {
      app.showErrorMsg("手机格式有误!");
      return;
    }

    if (!this.data.hasImg2) {
      app.showErrorMsg("请上传微信二维码!");
      return;
    }

    if (form.fee == "" || form.fee == null) {
      form.fee = 0;
    }

    if (form.userLimit == "" || form.userLimit == null){
      form.userLimit = -1;
    }

    wx.showLoading({
      title: '提交中...'
    });

    var url = app.serverUrl + "match/add";
    var that = this;
    wx.request({
      url: url,
      data: JSON.stringify(form),
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
            title: '创建成功，审核通过后展示。',
            icon: 'none',
            duration: 2000
          });

          setTimeout(function() {
            wx.switchTab({
              url: '/pages/match/match'
            })
          }, 2000);
        } else {
          that.showErrorMsg("提交失败！");
        }
      }
    })

  }
})