<template>
<!-- 首页 -->
  <div id="home">

      <div class="nav">
        <van-nav-bar   @click-right="goPublish()">
                <template #right>
                    <van-icon name="edit" size="18" />
                </template>
        </van-nav-bar>
	    </div>
      <div style="width:100%;height:10px;"></div>

    <div class="list" v-for="(post,pid) in list" :key="pid" >

      <div class="top">
        <div class="user" @click="goPerson(post.uid)">
          <div class="icon" >
            <img :src="post.users[0].icon" alt=""/>
            <!-- <img src="https://img0.baidu.com/it/u=4174801805,584386614&fm=26&fmt=auto" alt=""> -->
            </div>
          <div class="name">{{post.users[0].nickname}}</div>
        </div>
        <div class="time">{{post.time}}</div>
      </div>

      <div class="mid" @click="goDetail(post.pid)">
        <div class="title">{{post.title}}</div>
        <div class="content">{{post.content}}</div>
        <div class="image" v-if="post.image">
          <img
            :src="post.image"
          />
          
        </div>
      </div>


      <!-- <div class="low">
        <van-grid :border="false" :column-num="2">
        <van-grid-item>
          <van-badge :content="post.comments.length+post.replies.length" color="#1989fa">
          <van-icon name="comment-o" size="20" />
          </van-badge>
        </van-grid-item>
        <van-grid-item>
          <van-badge :content="post.goods.length" color="#1989fa">
          <van-icon name="good-job-o" size="20" />
          </van-badge>
        </van-grid-item>
      </van-grid>
      </div> -->


    </div>
  </div>
</template>

<script>

export default {
  name: "Home",
  data(){
    return{
      found:'',
      list:[],

      
    }
  },
  created(){
    this.getList()
  },
  methods:{
    // 去发表
    goPublish(){
      let cacheData=localStorage.getItem("uid")
      cacheData=cacheData ? JSON.parse(cacheData):{}


      if(Object.keys(cacheData)){
        console.log("已登录,用户id："+cacheData);
                this.$router.push({name:'Publish'})
      }else{
      this.$router.push({name:'Login'})
      }
    },

    // 获取帖子
    getList(){
      
      console.log("发起数据请求")

      this.axios({
                method:"get",
                // url:'/getPost',
                url:'/Home',
            }).then(res=>{
              this.list=res.data;
                console.log(this.list);
                
            }).catch(res=>{
                console.log(404)
            })
    },
    // 详情
    goDetail(pid){
      this.$router.push({name:'Detail',params:{pid}})
    },
    // 个人
    goPerson(uid){
      this.$router.push({name:'Personal',params:{uid}})
    }
  }
};
</script>
<style lang="less" scoped>
#home{
  width: 100%;
  height: 100%;
  background: rgb(240, 236, 236);
  margin-top: -20px;
  margin-bottom: 60px;
  

  .nav{
    width: 100%;
    position: fixed;
    z-index: 1000;
    top: 0;
  }

  .list{
    top: 100px;
    width: 94%;
    margin-left: 1%;
    border-radius: 10px;
    background: white;
    margin: 10px;

    .top{
      width: 100%;
      display: flex;
      justify-content: space-between;

      .user{
        display: flex;
        .icon{
          margin: 5px;
          margin-left: 10px;
          // background: black;
          // height: 30px;
          // width: 30px;
          // border-radius: 35%;
          img{
                width: 30px;
                height: 30px;
                object-fit: cover;
                border-radius: 35%;
          }
        }
        .name{
          margin: 5px;
          font-size: 14px;
          font-weight: 530;
        }
      }
      .time{
        margin: 5px;
        color: rgb(173, 169, 169);
      }
    }

    .mid{
      width: 100%;
      text-align: left;

      .title{
        margin: 10px;
        font-size: 16px;
        font-weight: 550;
      }

      .content{
        margin: 10px;
        font-size: 14px;
        font-weight: 500;
      }
      .image{
        img{
          width: 300px;
          height: 300px;
          margin: 25px;
          object-fit: cover;
          border-radius: 10%;
        }
        
      }
      
    }

    .low{
      width: 90%;
      margin: 10px;

    }
  }
}

</style>
