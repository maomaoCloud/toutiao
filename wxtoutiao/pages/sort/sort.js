Page({

  /**
   * 页面的初始数据  1  5  9
   */
  data: {
    hidden: true,
    flag: false,
    x: 0,
    y: 0,
    data: [{
        index: 1,
        name: "张晓明"
      },
      {
        index: 2,
        name: "刘卓"
      },
      {
        index: 3,
        name: "谢康"
      },
      {
        index: 4,
        name: "毛尖"
      },
      {
        index: 5,
        name: "赵晓芳"
      },
      {
        index: 6,
        name: "丁晓娟"
      },
      {
        index: 7,
        name: "你猜"
      },
      {
        index: 8,
        name: "哈哈哈"
      },
      {
        index: 5,
        name: "赵晓芳"
      },
      {
        index: 6,
        name: "丁晓娟"
      },
      {
        index: 7,
        name: "你猜"
      },
      {
        index: 8,
        name: "哈哈哈"
      },
      {
        index: 5,
        name: "赵晓芳"
      },
      {
        index: 6,
        name: "丁晓娟"
      },
      {
        index: 7,
        name: "你猜"
      },
      {
        index: 8,
        name: "哈哈哈"
      },
      {
        index: 5,
        name: "赵晓芳"
      },
      {
        index: 6,
        name: "丁晓娟"
      },
      {
        index: 7,
        name: "你猜"
      },
      {
        index: 8,
        name: "哈哈哈"
      },
      {
        index: 5,
        name: "赵晓芳"
      },
      {
        index: 6,
        name: "丁晓娟"
      },
      {
        index: 7,
        name: "你猜"
      },
      {
        index: 8,
        name: "哈哈哈"
      },
      {
        index: 5,
        name: "赵晓芳"
      },
      {
        index: 6,
        name: "丁晓娟"
      },
      {
        index: 7,
        name: "你猜"
      },
      {
        index: 8,
        name: "哈哈哈"
      },
      {
        index: 5,
        name: "赵晓芳"
      },
      {
        index: 6,
        name: "丁晓娟"
      },
      {
        index: 7,
        name: "你猜"
      },
      {
        index: 8,
        name: "哈哈哈"
      },
      {
        index: 5,
        name: "赵晓芳"
      },
      {
        index: 6,
        name: "丁晓娟"
      },
      {
        index: 7,
        name: "你猜"
      },
      {
        index: 8,
        name: "哈哈哈"
      },
      {
        index: 5,
        name: "赵晓芳"
      },
      {
        index: 6,
        name: "丁晓娟"
      },
      {
        index: 7,
        name: "你猜"
      },
      {
        index: 8,
        name: "哈哈哈"
      }
      
    ],
    disabled: true,
    elements: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

    var query = wx.createSelectorQuery();
    var nodesRef = query.selectAll(".item");
    nodesRef.fields({
      dataset: true,
      rect: true

    }, (result) => {
      this.setData({
        elements: result
      })
    }).exec()
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
  }
})