// pages/search/search.js
// 1、输入框绑定 值改变事件 input事件
//  1 获取输入框的值
//  2 合法性判断
//  3 检验通过 把输入框的值发送到后端
//  4 返回的Json数据打印到页面上
// 2、防抖
import {request} from "../../request/index.js"

Page({
  data: {
    // 获取到的树洞集合
    treeholeList:[],
    thumbFlagList:[],
    thumbUrl1: "../../icons/like.png",
    thumbUrl: "../../icons/like_clicked.png",
  },
  TimeId: -1,
  //输入框的值
  handleInput(e){
    const {value} = e.detail;

    if(!value.trim()){
      return;
    }

    //定时器
    clearTimeout(this.TimeId);
    this.TimeId = setTimeout(()=>{
      this.getSearchTreehole(value);
    },1000);
  },

  async getSearchTreehole(word){
    const res = await request({url:"http://121.199.40.49:8443/search/getSearchTreehole",data:{word},method: "GET"});
    if(res.data.code == 50001 || res.data.code == 10001){
      return;
    }
    this.setData({
      treeholeList:res.data.data
    })
    this.judgeThumb();
  },
  async judgeThumb(){
    var temp = [];
    var tstyle = []
    for (var index in this.data.treeholeList){
      const res = await request({url:"http://121.199.40.49:8443/publish/judgethumb",data:{id:wx.getStorageSync('uid'),tid:this.data.treeholeList[index].tid},method: "GET"});
      if (res.data.code == 10002 || res.data.code == 10004 ||  res.data.code == 40001 || res.data.code == 80001){
       console.log(res.data.code);
       return;
      }

      temp[index] = res.data.data;
    }
    this.setData({
      thumbFlagList: temp,
    })
  },
})